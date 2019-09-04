package com.apeman.viewholderdemo.holders;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.apeman.library.annotations.AutoWind;
import com.apeman.library.annotations.HolderType;
import com.apeman.library.holder.impls.JsonAutoWindViewHolder;
import com.apeman.viewholderdemo.R;

import org.json.JSONObject;

import io.github.heartinfei.slogger.S;

/**
 * @author Rango on 2019-08-23 wangqiang@smzdm.com
 */
@HolderType(cellType = 1)
public class ArticleViewHolder1 extends JsonAutoWindViewHolder {

    @AutoWind(viewId = R.id.textView,
            payloadKey = "title",
            clickable = true)
    TextView tv;

    public ArticleViewHolder1(@NonNull ViewGroup parenetView) {
        super(parenetView, R.layout.view_holder_1_layout);
    }

    @Override
    public void onViewClicked(View v, JSONObject data) {
        S.i("ViewId:" + v.getId() + "," + data.toString());
    }
}
