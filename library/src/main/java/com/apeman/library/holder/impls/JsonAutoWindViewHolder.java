package com.apeman.library.holder.impls;


import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

import com.apeman.library.annotations.AutoWind;
import com.apeman.library.holder.AutoWindViewHolder;
import com.bumptech.glide.Glide;

import org.json.JSONObject;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Map;

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
    protected void initViews() {
        for (Field f : getClass().getDeclaredFields()) {
            Annotation a = f.getAnnotation(AutoWind.class);
            if (a != null) {
                handleAutoWindAnnotation(f, a);
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void handleAutoWindAnnotation(Field f, @NonNull Annotation a) {
        Class autoWindCls = a.getClass();
        try {
            int viewId = (int) autoWindCls.getDeclaredMethod("viewId").invoke(a);
            View targetView = getViewById(viewId);
            f.setAccessible(true);
            f.set(this, targetView);
            String fieldName = (String) autoWindCls.getDeclaredMethod("payloadKey").invoke(a);
            viewForWind.put(fieldName, targetView);
            if ((boolean) autoWindCls.getDeclaredMethod("clickable").invoke(a)) {
                windViewWithClickListener(viewId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 默认处理了 TextView & ImageView，其他特殊情况请走 interceptViewDataBind
     *
     * @param data
     */
    @Override
    protected void autoBindData(JSONObject data) {
        for (Map.Entry<String, View> entry : viewForWind.entrySet()) {
            try {
                bindPayload(entry, data);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void bindPayload(Map.Entry<String, View> viewEntry, JSONObject data) throws Exception {
        View targetView = viewEntry.getValue();
        String key = viewEntry.getKey();
        if (interceptViewDataBind(targetView, key, data)) {
            return;
        }
        String payload = data.getString(key);
        if (targetView instanceof TextView) {
            ((TextView) targetView).setText(payload);
        } else if (targetView instanceof ImageView) {
            Glide.with(targetView)
                    .load(payload)
                    .into((ImageView) targetView);
        }
    }
}
