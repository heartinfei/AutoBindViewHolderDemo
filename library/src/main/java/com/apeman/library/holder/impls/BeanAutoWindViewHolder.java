package com.apeman.library.holder.impls;

import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.apeman.library.bean.BaseBean;
import com.apeman.library.holder.AutoWindViewHolder;

/**
 * 自动绑定Bean属性到ViewHolder
 *
 * @author Rango on 2019-09-02 wangqiang@smzdm.com
 */
public abstract class BeanAutoWindViewHolder extends AutoWindViewHolder<BaseBean> {
    public BeanAutoWindViewHolder(@NonNull ViewGroup parentView, int layout) {
        super(parentView, layout);
    }

    @Override
    protected void autoBindData(BaseBean data) {
        //TODO：未实现
    }
}
