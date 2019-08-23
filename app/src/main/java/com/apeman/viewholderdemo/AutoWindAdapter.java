package com.apeman.viewholderdemo;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.apeman.library.holder.AutoWindViewHolder;
import com.apeman.library.protocl.OnElementClickListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Rango on 2019-08-23 wangqiang@smzdm.com
 */
public class AutoWindAdapter extends RecyclerView.Adapter<AutoWindViewHolder> {
    private List<JSONObject> dataSource = new LinkedList<>();

    public void setData(List<JSONObject> ds) {
        dataSource.clear();
        if (ds != null) {
            dataSource.addAll(ds);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AutoWindViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AutoWindViewHolder autoWindViewHolder = HolderPool.getViewHolderByType(viewType, parent);
        autoWindViewHolder.regElementClickListener(new OnElementClickListener<JSONObject>() {
            @Override
            public void onViewPreClick(View v, JSONObject data) {
                //点击之前调用
                //TODO: 统计代码？自定义点击事件？
            }

            @Override
            public void onViewClicked(View v, JSONObject data) {
                //点击之后调用
                //TODO: 统计代码？
            }
        });
        return autoWindViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AutoWindViewHolder holder, int position) {
        JSONObject data = dataSource.get(position);
        holder.bindData(data);
    }

    @Override
    public int getItemViewType(int position) {
        JSONObject data = dataSource.get(position);
        int type = 0;
        try {
            type = data.getInt("cellType");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return type;
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }
}
