package com.apeman.viewholderdemo.base;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.apeman.library.ga.GaInfo;
import com.apeman.library.holder.AutoWindViewHolder;
import com.apeman.library.protocl.GaCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Rango on 2019-08-23 wangqiang@smzdm.com
 */
public abstract class AutoWindJsonAdapter extends RecyclerView.Adapter<AutoWindViewHolder> implements GaCallback {
    private List<JSONObject> dataSource = new LinkedList<>();
    private final String from;
    public AutoWindJsonAdapter(String from) {
        this.from = from;
    }

    private GaCallback nextGaCallback = null;

    public void regGaCallback(GaCallback gaCallback) {
        this.nextGaCallback = gaCallback;
    }

    @NonNull
    @Override
    public AutoWindViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return HolderPool.getViewHolderByType(viewType, parent)
                .withGaCallback(this)
                .withFrom(from);
    }

    @Override
    public void handleGaEvent(GaInfo gaInfo) {
        //GA统计,可以自己统计 &| 统计数据传递下去
        onGaEvent(gaInfo);
        if (nextGaCallback != null) {
            nextGaCallback.handleGaEvent(gaInfo);
        }
    }

    protected abstract void onGaEvent(GaInfo gaInfo);

    public void setData(List<JSONObject> ds) {
        dataSource.clear();
        if (ds != null) {
            dataSource.addAll(ds);
        }
        notifyDataSetChanged();
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
