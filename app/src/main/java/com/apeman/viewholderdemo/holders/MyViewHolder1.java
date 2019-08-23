package com.apeman.viewholderdemo.holders;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.apeman.viewholderdemo.R;
import com.apeman.viewholderdemo.annotations.AutoClick;
import com.apeman.viewholderdemo.annotations.AutoWind;
import com.apeman.viewholderdemo.annotations.HolderType;
import com.apeman.viewholderdemo.base.AutoWindViewHolder;

import org.json.JSONObject;

/**
 * @author Rango on 2019-08-23 wangqiang@smzdm.com
 */
@HolderType(cellType = 1)
public class MyViewHolder1 extends AutoWindViewHolder {

    @AutoClick(viewId = R.id.textView)
    @AutoWind(viewId = R.id.textView, fieldName = "title")
    TextView tv;

    public MyViewHolder1(@NonNull ViewGroup parenetView) {
        super(parenetView, R.layout.view_holder_1_layout);
    }

    @Override
    public void onViewClicked(View v, JSONObject data) {
        Log.i("MyViewHolder1",data.toString());
    }
}