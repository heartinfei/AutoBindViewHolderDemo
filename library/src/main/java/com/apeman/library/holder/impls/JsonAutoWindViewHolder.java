package com.apeman.library.holder.impls;


import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

import com.apeman.library.annotations.AutoClick;
import com.apeman.library.annotations.AutoWind;
import com.apeman.library.holder.AutoWindViewHolder;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * 自动绑定JSON数据到ViewHolder
 *
 * @author Rango on 2019-08-23 wangqiang@smzdm.com
 */
public abstract class JsonAutoWindViewHolder extends AutoWindViewHolder<JSONObject> {
    public JsonAutoWindViewHolder(@NonNull ViewGroup parentView, @LayoutRes int layout) {
        super(parentView, layout);
    }

    @Override
    protected void autoBindData(JSONObject data) {
        for (Field f : this.getClass().getDeclaredFields()) {
            handleField(f, data);
        }
    }

    private void handleField(Field f, JSONObject data) {
        for (Annotation a : f.getAnnotations()) {
            if (a instanceof AutoWind) {
                handleAutoWindAnnotation(a, data);
            } else if (a instanceof AutoClick) {
                handleAutoClickAnnotation(a, data);
            }
        }
    }

    @SuppressWarnings("all")
    private void handleAutoWindAnnotation(@NonNull Annotation a, JSONObject data) {
        Class autoWindCls = a.getClass();
        try {
            int viewId = (int) autoWindCls.getDeclaredMethod("viewId").invoke(a);
            String fieldName = (String) autoWindCls.getDeclaredMethod("fieldName").invoke(a);
            windViewWithData(viewId, fieldName, data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("all")
    private void handleAutoClickAnnotation(Annotation a, JSONObject data) {
        Class autoWindCls = a.getClass();
        try {
            int viewId = (int) autoWindCls.getDeclaredMethod("viewId").invoke(a);
            windViewWithClickListener(viewId, data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void windViewWithData(int viewId, String fileName, JSONObject data) {
        View targetView = getViewById(viewId);
        String value = getValue(fileName, data);
        if (targetView instanceof TextView) {
            ((TextView) targetView).setText(value);
        } else if (targetView instanceof ImageView) {
            Glide.with(targetView)
                    .load(value)
                    .into((ImageView) targetView);
        }
    }

    private String getValue(String fileName, JSONObject data) {
        String value;
        try {
            value = data.getString(fileName);
        } catch (JSONException e) {
            e.printStackTrace();
            value = "#Know#";
        }
        return value;
    }
}
