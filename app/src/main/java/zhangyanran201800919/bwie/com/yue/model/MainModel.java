package zhangyanran201800919.bwie.com.yue.model;

import zhangyanran201800919.bwie.com.yue.bean.NewBean;

/**
 * Created by 匹诺曹 on 2018/9/19.
 */

public interface MainModel {
    void onSuccess(NewBean newBean);
    void onError(int code);
}
