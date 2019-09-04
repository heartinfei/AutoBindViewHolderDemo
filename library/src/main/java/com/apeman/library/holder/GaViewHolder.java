package com.apeman.library.holder;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.apeman.library.ga.GaInfo;
import com.apeman.library.protocl.AutoWindInterface;
import com.apeman.library.protocl.GaCallback;

/**
 * @author Rango on 2019-09-03 wangqiang@smzdm.com
 */
public abstract class GaViewHolder<T> extends RecyclerView.ViewHolder implements
        AutoWindInterface<T> {
    //GA统计
    private GaCallback gaCallback = null;
    //用于GA统计
    private String from = null;

    public GaViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public GaViewHolder<T> withGaCallback(GaCallback callback) {
        this.gaCallback = callback;
        return this;
    }

    protected void dispatchGaEvent(GaInfo gaInfo) {
        if (gaCallback != null) {
            gaCallback.handleGaEvent(gaInfo);
        }
    }

    public GaViewHolder<T> withFrom(String from) {
        this.from = from;
        return this;
    }

    public String getFrom() {
        return from;
    }
}
