package com.apeman.viewholderdemo.base;

import android.view.ViewGroup;

import com.apeman.library.holder.GaViewHolder;
import com.apeman.viewholderdemo.holders.MyViewHolder1;
import com.apeman.viewholderdemo.holders.MyViewHolder2;
import com.apeman.viewholderdemo.holders.MyViewHolder3;
import com.apeman.viewholderdemo.holders.MyViewHolder4;
import com.apeman.viewholderdemo.holders.SubViewHolder5;

import org.json.JSONObject;

/**
 * @author Rango on 2019-08-23 wangqiang@smzdm.com
 */
class JsonViewHolderPool {
    //TODO：该类由注解框架自动生成的方式 || 手动注册的方式
    static GaViewHolder<JSONObject> getViewHolderByType(int cellType, ViewGroup parentView) {
        switch (cellType) {
            case 1:
                return new MyViewHolder1(parentView);
            case 2:
                return new MyViewHolder2(parentView);
            case 3:
                return new MyViewHolder3(parentView);
            case 4:
                return new MyViewHolder4(parentView);
            default:
                return new SubViewHolder5(parentView);
        }
    }
}
