package com.apeman.library.protocl;

/**
 * @author Rango on 2019-08-23 wangqiang@smzdm.com
 */
public interface AutoWindInterface<T> extends OnElementClickListener<T> {

    /**
     * 返回Holder类型
     *
     * @return
     */
    int getHolderType();

    /**
     * 获取绑定的数据
     *
     * @return
     */
    T getHolderData();

    /**
     * 绑定数据
     *
     * @param data
     */
    void bindData(T data, int position);
}
