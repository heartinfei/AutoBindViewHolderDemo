package com.apeman.viewholderdemo;

import android.view.ViewGroup;

import com.apeman.library.holder.AutoWindViewHolder;
import com.apeman.viewholderdemo.holders.MyViewHolder1;
import com.apeman.viewholderdemo.holders.MyViewHolder2;
import com.apeman.viewholderdemo.holders.MyViewHolder3;

/**
 * @author Rango on 2019-08-23 wangqiang@smzdm.com
 */
public class HolderPool {
    //TODO：该类由注解框架自动生成的方式 || 手动注册的方式
    public static AutoWindViewHolder getViewHolderByType(int cellType, ViewGroup parentView) {
        switch (cellType) {
            case 0:
                return new MyViewHolder1(parentView);
            case 1:
                return new MyViewHolder2(parentView);
            default:
                return new MyViewHolder3(parentView);
        }
    }
}
