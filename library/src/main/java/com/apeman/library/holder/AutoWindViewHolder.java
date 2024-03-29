package com.apeman.library.holder;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

import com.apeman.library.R;
import com.apeman.library.annotations.HolderType;
import com.apeman.library.ga.GaInfo;
import com.apeman.library.protocl.OnElementClickListener;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Rango on 2019-09-02 wangqiang@smzdm.com
 */
public abstract class AutoWindViewHolder<T> extends GaViewHolder<T> implements View.OnClickListener {
    //缓存待绑定数据的View
    protected Map<String, View> viewForWind = new LinkedHashMap<>();
    //当前卡片类型跟服务端保持一致，通过注解生成
    private final int cellType;
    //缓存View提高效率
    private SparseArray<View> viewCache = new SparseArray<>();
    //注册的额外监听
    private List<OnElementClickListener<T>> regElementListeners = new LinkedList<>();
    //用于存储HolderData
    private final int TAG_KEY = R.id.viewExtra;
    private int currentPosition = -1;

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
        initViews();
    }

    /**
     * 初始化View
     */
    protected abstract void initViews();

    @Override
    public void bindData(T data, int position) {
        this.currentPosition = position;
        itemView.setTag(TAG_KEY, data);
        if (manualBindData(data)) {
            return;
        }
        autoBindData(data);
    }

    /**
     * 手动绑定数据到ViewHolder
     *
     * @param data
     * @return true 不调用自动绑定{@link AutoWindViewHolder#autoBindData(Object)},default is false.
     */
    protected boolean manualBindData(T data) {
        return false;
    }

    /**
     * 自动绑定数据到ViewHolder
     *
     * @param data
     */
    protected abstract void autoBindData(T data);

    /**
     * 拦截某个View的数据绑定过程
     *
     * @param targetView
     * @param dataKey
     * @param data
     * @return true 拦截
     */
    protected boolean interceptViewDataBind(View targetView, String dataKey, T data) {
        return false;
    }

    @Override
    public T getHolderData() {
        return getTagData(itemView);
    }

    @SuppressWarnings("unchecked")
    private T getTagData(View v) {
        return (T) v.getTag(TAG_KEY);
    }

    @Override
    public int getHolderType() {
        return cellType;
    }

    /**
     * 设置点击监听
     *
     * @param viewId
     */
    protected void windViewWithClickListener(int viewId) {
        View targetView = getViewById(viewId);
        if (targetView == null) {
            return;
        }
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
    public final void onClick(View view) {
        T data = getTagData(itemView);
        onViewPreClick(view, data);
        for (OnElementClickListener<T> l : regElementListeners) {
            l.onViewPreClick(view, data);
        }

        onViewClicked(view, data);
        for (OnElementClickListener<T> l : regElementListeners) {
            l.onViewClicked(view, data);
        }

        //传递统计业务
        GaInfo gaInfo = new GaInfo(view.getId(), this, null);
        gaInfo.setAdapterPosition(currentPosition);
        dispatchGaEvent(gaInfo);
    }

    @Override
    public void onViewPreClick(View v, T data) {

    }

    public void addElementClickListener(OnElementClickListener<T> l) {
        regElementListeners.add(l);
    }
}
