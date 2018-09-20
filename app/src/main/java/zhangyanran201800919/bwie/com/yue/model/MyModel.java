package zhangyanran201800919.bwie.com.yue.model;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import zhangyanran201800919.bwie.com.yue.bean.NewBean;
import zhangyanran201800919.bwie.com.yue.okhttputils.OkHttp3Utils;

/**
 * Created by 匹诺曹 on 2018/9/19.
 */

public class MyModel {
    private String api = "http://172.17.8.100/movieApi/cinema/v1/findRecommendCinemas?longitude=116.30551391385724&latitude=40.04571807462411&page=1&count=10 ";

    public void getData(final MainModel mainModel){
        OkHttp3Utils.doget(api, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mainModel.onError(1);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                NewBean newBean = new Gson().fromJson(string, NewBean.class);
                mainModel.onSuccess(newBean);
            }
        });
    }

}
