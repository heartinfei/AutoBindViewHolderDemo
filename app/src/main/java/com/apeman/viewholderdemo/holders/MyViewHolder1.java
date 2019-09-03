package com.apeman.viewholderdemo.holders;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.apeman.library.annotations.AutoWind;
import com.apeman.library.annotations.HolderType;
import com.apeman.library.holder.impls.JsonAutoWindViewHolder;
import com.apeman.viewholderdemo.R;

import org.json.JSONObject;

/**
 * @author Rango on 2019-08-23 wangqiang@smzdm.com
 */
@HolderType(cellType = 1)
public class MyViewHolder1 extends JsonAutoWindViewHolder {

    @AutoWind(viewId = R.id.textView,
            payloadKey = "title",
            clickable = true)
    TextView tv;

    public MyViewHolder1(@NonNull ViewGroup parenetView) {
        super(parenetView, R.layout.view_holder_1_layout);
    }

    @Override
    protected boolean interceptViewDataBind(View targetView, String dataKey, JSONObject data) {
        return super.interceptViewDataBind(targetView, dataKey, data);
    }

    @Override
    protected boolean manualBindData(JSONObject data) {
        return super.manualBindData(data);
    }

    @Override
    public void onViewClicked(View v, JSONObject data) {
        Log.i("MyViewHolder1", data.toString());
    }
}
