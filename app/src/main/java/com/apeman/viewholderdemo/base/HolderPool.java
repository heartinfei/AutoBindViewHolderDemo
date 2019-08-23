package com.apeman.viewholderdemo.base;

import android.view.ViewGroup;

import com.apeman.viewholderdemo.holders.MyViewHolder1;
import com.apeman.viewholderdemo.holders.MyViewHolder2;
import com.apeman.viewholderdemo.holders.MyViewHolder3;

/**
 * 该类由注解框架自动生成
 *
 * @author Rango on 2019-08-23 wangqiang@smzdm.com
 */
public class HolderPool {

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
