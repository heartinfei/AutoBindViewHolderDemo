package com.apeman.viewholderdemo.protocl;

/**
 * @author Rango on 2019-08-23 wangqiang@smzdm.com
 */
public interface AutoWindInterface<T> extends OnElementClickListener<T> {
    void regElementClickListener(OnElementClickListener<T> l);
    /**
     * 绑定数据
     *
     * @param data
     */
    void bindData(T data);
}
