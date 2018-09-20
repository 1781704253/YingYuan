package zhangyanran201800919.bwie.com.yue.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import zhangyanran201800919.bwie.com.yue.R;
import zhangyanran201800919.bwie.com.yue.activity.ShowActivity;
import zhangyanran201800919.bwie.com.yue.adapter.MyAdapter;
import zhangyanran201800919.bwie.com.yue.bean.NewBean;
import zhangyanran201800919.bwie.com.yue.presenter.MyPresenter;

public class MainActivity extends AppCompatActivity implements MainView{

    private RecyclerView recycler_view;
    private MyPresenter presenter;
    private List<NewBean.ResultBean.NearbyCinemaListBean> data;
    private MyAdapter myAdapter;
    private TextView wei;
    private TextView haun,ha;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycler_view = findViewById(R.id.recycler_view);
        haun = findViewById(R.id.haun);
        ha = findViewById(R.id.ha);
        wei = findViewById(R.id.wei);
        presenter = new MyPresenter(this);
        presenter.getData();
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recycler_view.setLayoutManager(linearLayoutManager);

        wei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ShowActivity.class);
                startActivity(intent);
            }
        });
        haun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                haun.setVisibility(View.GONE);
                ha.setVisibility(View.VISIBLE);
                recycler_view.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                myAdapter.notifyDataSetChanged();
            }
        });
        ha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                haun.setVisibility(View.VISIBLE);
                ha.setVisibility(View.GONE);
                recycler_view.setLayoutManager(new GridLayoutManager(MainActivity.this,2));
                myAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onSuccess(NewBean newBean) {
        data = newBean.getResult().getNearbyCinemaList();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                myAdapter = new MyAdapter(MainActivity.this, data);
                recycler_view.setAdapter(myAdapter);
            }
        });
    }

    @Override
    public void onError(int code) {

    }
}
