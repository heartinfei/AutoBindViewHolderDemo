package com.apeman.viewholderdemo.protocl;

import android.view.View;

/**
 * @author Rango on 2019-08-23 wangqiang@smzdm.com
 */
public interface OnElementClickListener<T> {
    /**
     * View点击之前
     *
     * @param v
     * @param data
     */
    void onViewPreClick(View v, T data);


    /**
     * View点击之后
     *
     * @param v
     * @param data
     */
    void onViewClicked(View v, T data);
}
