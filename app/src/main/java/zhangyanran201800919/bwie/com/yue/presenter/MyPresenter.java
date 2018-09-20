package zhangyanran201800919.bwie.com.yue.presenter;

import zhangyanran201800919.bwie.com.yue.bean.NewBean;
import zhangyanran201800919.bwie.com.yue.model.MainModel;
import zhangyanran201800919.bwie.com.yue.model.MyModel;
import zhangyanran201800919.bwie.com.yue.view.MainView;

/**
 * Created by 匹诺曹 on 2018/9/19.
 */

public class MyPresenter implements MainPresenter {

    private MainView mainView;
    private MyModel myModel;

    public MyPresenter(MainView mainView) {
        this.mainView = mainView;
        myModel = new MyModel();
    }

    public void getData() {
        myModel.getData(new MainModel() {
            @Override
            public void onSuccess(NewBean newBean) {
                mainView.onSuccess(newBean);
            }

            @Override
            public void onError(int code) {
                mainView.onError(1);
            }
        });
    }


    @Override
    public void desc() {

    }


}
