package zhangyanran201800919.bwie.com.yue.okhttputils;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by 匹诺曹 on 2018/9/19.
 */

public class OkHttp3Utils {
    private static OkHttpClient client = null;

    public OkHttp3Utils() {
    }
    //单列模式（懒汉）初始化okhttp
    public static OkHttpClient getInstance() {
        if (client == null) {
            synchronized (OkHttp3Utils.class) {
                if (client == null)
                    client = new OkHttpClient();
            }
        }
        return client;
    }
    /*
     * doget同步请求方法
     * url  请求链接
     * 通过callback获取结果
     * */
    public static void dogetex(String url){
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = getInstance().newCall(request);
        try {
            call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*
     * doget异步请求方法
     * url  请求链接
     * 通过callback获取结果
     * */
    public static void doget(String url, Callback callback){
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = getInstance().newCall(request);
        call.enqueue(callback);
    }
    /*
     * dopost 方法
     * url
     * map
     * */
    public static void dopost(String url, Map<String,String> map, Callback callback){
        FormBody.Builder  builder=new FormBody.Builder();
        for (String key : map.keySet()) {
            builder.add(key, map.get(key));
        }
        Request request = new Request.Builder()
                .url(url)
                .post(builder.build())
                .build();
        Call call = getInstance().newCall(request);
        call.enqueue(callback);
    }
    /*
     * dopost 方法
     * url
     * json
     * */
    public static void dopostjson(String url, String json, Callback callback){
        RequestBody body= RequestBody.create(MediaType.parse("application/json; charset=utf-8"),json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Call call = getInstance().newCall(request);
        call.enqueue(callback);
    }
    //拦截器
    class MyIntercepter implements Interceptor {
        //intercept 拦截
        @Override
        public Response intercept(Chain chain) throws IOException {
            //添加公共参数
//            post 取出原来所有的参数，将之加到新的请求体里面。然后让请求去执行
            Request request = chain.request();
            //获取请求方法
            String method = request.method();
            if (method.equals("GET")) {//---------------------------GET 拦截
                //取出url地址
                String url = request.url().toString();
                //拼接公共参数
                boolean contains = url.contains("?");
                if (contains) {
                    url = url + "&source=android";
                } else {
                    url = url + "?source=android";
                }

                Request request1 = request.newBuilder().url(url).build();

                Response response = chain.proceed(request1);

                return response;


            } else if (method.equals("POST")) {//---------------------POST 拦截
                RequestBody body = request.body();//请求体
                if (body instanceof FormBody) {
                    //创建新的请求体
                    FormBody.Builder newBuilder = new FormBody.Builder();
                    for (int i = 0; i < ((FormBody) body).size(); i++) {
                        String key = ((FormBody) body).name(i);
                        String value = ((FormBody) body).value(i);
                        newBuilder.add(key, value);
                    }
                    //添加公共参数
                    newBuilder.add("source", "android");
                    FormBody newBody = newBuilder.build();
                    //创建新的请求体
                    Request request1 = request.newBuilder().post(newBody).build();
                    //去请求
                    Response response = chain.proceed(request1);
                    return response;
                }
            }
            return null;
        }
    }
}

