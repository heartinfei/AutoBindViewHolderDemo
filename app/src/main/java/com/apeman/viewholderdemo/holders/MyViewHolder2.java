package com.apeman.viewholderdemo.holders;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

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
public class MyViewHolder2 extends AutoWindViewHolder {

    @AutoClick(viewId = R.id.iv)
    @AutoWind(viewId = R.id.iv, fieldName = "url")
    ImageView iv;

    public MyViewHolder2(@NonNull ViewGroup parenetView) {
        super(parenetView, R.layout.view_holder_2_layout);
    }

    @Override
    public void onViewClicked(View v, JSONObject data) {

    }
}