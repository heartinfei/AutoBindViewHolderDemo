package com.apeman.library.holder;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.apeman.library.R;
import com.apeman.library.annotations.HolderType;
import com.apeman.library.ga.GaInfo;
import com.apeman.library.protocl.AutoWindInterface;
import com.apeman.library.protocl.GaCallback;
import com.apeman.library.protocl.OnElementClickListener;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Rango on 2019-09-02 wangqiang@smzdm.com
 */
public abstract class AutoWindViewHolder<T> extends RecyclerView.ViewHolder implements
        AutoWindInterface<T>, View.OnClickListener {
    //缓存View提高效率
    private SparseArray<View> viewCache = new SparseArray<>();
    //注册的额外监听
    private List<OnElementClickListener<T>> regElementListeners = new LinkedList<>();
    //用于存储HolderData
    protected final int TAG_KEY = R.id.viewExtra;
    //GA统计
    protected GaCallback gaCallback = null;

    //卡片类型，通过注解生成
    protected final int cellType;
    //用于GA统计
    protected String from = null;

    public AutoWindViewHolder(@NonNull ViewGroup parentView, @LayoutRes int layout) {
        super(LayoutInflater.from(parentView.getContext())
                .inflate(layout, parentView, false));
        itemView.setOnClickListener(this);
        HolderType holderType = getClass().getAnnotation(HolderType.class);
        if (holderType != null) {
            cellType = holderType.cellType();
        } else {
            cellType = -1;
        }
    }

    @Override
    public void bindData(T data) {
        itemView.setTag(TAG_KEY, data);
        autoBindData(data);
    }

    protected abstract void autoBindData(T data);

    public AutoWindViewHolder withFrom(String from) {
        this.from = from;
        return this;
    }

    public String getFrom() {
        return from;
    }

    public AutoWindViewHolder withGaCallback(GaCallback callback) {
        this.gaCallback = callback;
        return this;
    }

    @Override
    public final void onClick(View view) {
        T data = getTagData(view);
        onViewPreClick(view, data);
        for (OnElementClickListener<T> l : regElementListeners) {
            l.onViewPreClick(view, data);
        }

        onViewClicked(view, data);
        for (OnElementClickListener<T> l : regElementListeners) {
            l.onViewClicked(view, data);
        }

        if (gaCallback != null) {
            gaCallback.handleGaEvent(new GaInfo(this, null));
        }
    }

    @Override
    public T getHolderData() {
        return getTagData(itemView);
    }

    @SuppressWarnings("all")
    private T getTagData(View v) {
        return (T) v.getTag(TAG_KEY);
    }

    @Override
    public int getHolderType() {
        return cellType;
    }

    protected void windViewWithClickListener(int viewId, T data) {
        View targetView = getViewById(viewId);
        if (targetView == null) {
            return;
        }
        targetView.setTag(TAG_KEY, data);
        targetView.setOnClickListener(this);
    }

    protected View getViewById(int viewId) {
        View v = viewCache.get(viewId);
        if (v == null) {
            v = itemView.findViewById(viewId);
            viewCache.append(viewId, v);
        }
        return v;
    }

    @Override
    public void regElementClickListener(OnElementClickListener<T> l) {
        regElementListeners.add(l);
    }

    @Override
    public void onViewPreClick(View v, T data) {

    }
}
