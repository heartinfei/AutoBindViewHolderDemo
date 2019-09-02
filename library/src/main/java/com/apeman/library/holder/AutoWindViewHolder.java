package com.apeman.library.holder;


import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.apeman.library.R;
import com.apeman.library.annotations.AutoClick;
import com.apeman.library.annotations.AutoWind;
import com.apeman.library.annotations.HolderType;
import com.apeman.library.protocl.AutoWindInterface;
import com.apeman.library.protocl.GaCallback;
import com.apeman.library.protocl.OnElementClickListener;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Rango on 2019-08-23 wangqiang@smzdm.com
 */
public abstract class AutoWindViewHolder extends RecyclerView.ViewHolder implements
        AutoWindInterface<JSONObject>, View.OnClickListener {
    //用于存储HolderData
    private final int TAG_KEY = R.id.viewExtra;
    //GA统计
    private GaCallback gaCallback = null;
    //注册的额外监听
    private List<OnElementClickListener<JSONObject>> regElementListeners = new LinkedList<>();
    //缓存View提高效率
    private SparseArray<View> viewCache = new SparseArray<>();
    //卡片类型，通过注解生成
    private final int cellType;
    //用于GA统计
    private String from = null;

    public AutoWindViewHolder(@NonNull ViewGroup parentView, @LayoutRes int layout) {
        super(LayoutInflater.from(parentView.getContext())
                .inflate(layout, parentView, false));
        HolderType holderType = getClass().getAnnotation(HolderType.class);
        if (holderType != null) {
            cellType = holderType.cellType();
        } else {
            cellType = -1;
        }
        itemView.setOnClickListener(this);
    }

    public AutoWindViewHolder withFrom(String from) {
        this.from = from;
        return this;
    }

    public String getFrom() {
        return from;
    }

    public AutoWindViewHolder withGaCallback(GaCallback callback) {
        this.gaCallback = callback;
        return this;
    }

    @Override
    public int getHolderType() {
        return cellType;
    }

    @Override
    public JSONObject getHolderData() {
        return getTagData(itemView);
    }


    @Override
    public void bindData(JSONObject data) {
        itemView.setTag(TAG_KEY, data);
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

    private View getViewById(int viewId) {
        View v = viewCache.get(viewId);
        if (v == null) {
            v = itemView.findViewById(viewId);
            viewCache.append(viewId, v);
        }
        return v;
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

    protected String getValue(String fileName, JSONObject data) {
        String value;
        try {
            value = data.getString(fileName);
        } catch (JSONException e) {
            e.printStackTrace();
            value = "#Know#";
        }
        return value;
    }

    private void windViewWithClickListener(int viewId, JSONObject data) {
        View targetView = getViewById(viewId);
        if (targetView == null) {
            return;
        }
        targetView.setTag(TAG_KEY, data);
        targetView.setOnClickListener(this);
    }

    private JSONObject getTagData(View v) {
        return (JSONObject) v.getTag(TAG_KEY);
    }

    @Override
    public void regElementClickListener(OnElementClickListener<JSONObject> l) {
        regElementListeners.add(l);
    }

    @Override
    public final void onClick(View view) {
        JSONObject data = getTagData(view);
        onViewPreClick(view, data);
        for (OnElementClickListener<JSONObject> l : regElementListeners) {
            l.onViewPreClick(view, data);
        }

        onViewClicked(view, data);
        for (OnElementClickListener<JSONObject> l : regElementListeners) {
            l.onViewClicked(view, data);
        }

        if (gaCallback != null) {
            gaCallback.handleGaEvent(new GaInfo(this, null));
        }
    }

    @Override
    public void onViewPreClick(View v, JSONObject data) {

    }
}
