package zhangyanran201800919.bwie.com.yue.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import zhangyanran201800919.bwie.com.yue.R;
import zhangyanran201800919.bwie.com.yue.bean.NewBean;

/**
 * Created by 匹诺曹 on 2018/9/19.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    Context context;
    List<NewBean.ResultBean.NearbyCinemaListBean> list;

    public MyAdapter(Context context, List<NewBean.ResultBean.NearbyCinemaListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater from = LayoutInflater.from(context);
        View view = from.inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.address.setText(list.get(position).getAddress());
        holder.name.setText(list.get(position).getName());
        holder.jl.setText(list.get(position).getDistance());
        Glide.with(context).load(list.get(position).getLogo()).into(holder.iocn);
    }


    @Override
    public int getItemCount(){
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView iocn;
        private TextView name,address,jl;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            address = itemView.findViewById(R.id.address);
            iocn = itemView.findViewById(R.id.iocn);
            jl = itemView.findViewById(R.id.jl);
        }
    }
}
