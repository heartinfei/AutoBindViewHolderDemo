package com.apeman.viewholderdemo.holders;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import com.apeman.library.annotations.AutoWind;
import com.apeman.library.annotations.HolderType;
import com.apeman.library.holder.impls.JsonAutoWindViewHolder;
import com.apeman.viewholderdemo.R;

import org.json.JSONObject;

import io.github.heartinfei.slogger.S;

/**
 * {
 * "key1":"value1",
 * "key2":"value2",
 * "key3":"value3"
 * }
 *
 * @author Rango on 2019-08-23 wangqiang@smzdm.com
 */
@HolderType(cellType = 3)
public class UserViewHolder3 extends JsonAutoWindViewHolder {
    CardView c;
    @AutoWind(viewId = R.id.textView,
            payloadKey = "name")
    TextView tv;

    @AutoWind(viewId = R.id.iv,
            payloadKey = "url",
            clickable = true)
    ImageView iv;

    public UserViewHolder3(@NonNull ViewGroup parenetView) {
        super(parenetView, R.layout.view_holder_3_layout);
    }

    @Override
    public void onViewClicked(View v, JSONObject data) {
        S.i("ViewId:" + v.getId() + "," + data.toString());
    }
}
