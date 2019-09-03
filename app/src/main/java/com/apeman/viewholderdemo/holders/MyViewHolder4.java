package com.apeman.viewholderdemo.holders;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.apeman.library.annotations.AutoWind;
import com.apeman.library.annotations.HolderType;
import com.apeman.library.ga.GaInfo;
import com.apeman.library.holder.AutoWindViewHolder;
import com.apeman.library.holder.GaViewHolder;
import com.apeman.library.holder.impls.JsonAutoWindViewHolder;
import com.apeman.viewholderdemo.R;
import com.apeman.viewholderdemo.base.AutoWindJsonAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

import io.github.heartinfei.slogger.S;

/**
 * @author Rango on 2019-08-23 wangqiang@smzdm.com
 */
@HolderType(cellType = 4)
public class MyViewHolder4 extends JsonAutoWindViewHolder {

    @AutoWind(viewId = R.id.rlv, payloadKey = "list")
    private RecyclerView rlv;

    private MyAdapter myAdapter;

    public MyViewHolder4(@NonNull ViewGroup parenetView) {
        super(parenetView, R.layout.view_holder_4_layout);
        myAdapter = new MyAdapter("二级From Holder4");
        //订阅GaEvent
        myAdapter.subscribeGaEvent(childGaInfo -> deliverGaEvent(new GaInfo(this, childGaInfo)));
        rlv.setAdapter(myAdapter);

        RecyclerView.LayoutManager lm = new LinearLayoutManager(
                parenetView.getContext(),
                RecyclerView.HORIZONTAL,
                false);

        rlv.setLayoutManager(lm);
    }

    @Override
    protected boolean manualBindData(JSONObject data) {
        List<JSONObject> jsonObjectList = new LinkedList<>();
        try {
            JSONArray jsonArray = data.getJSONArray("list");
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObjectList.add(jsonArray.getJSONObject(i));
            }
            myAdapter.setData(jsonObjectList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public void onViewClicked(View v, JSONObject data) {
        S.i(data.toString());
        //获取子卡片相对于当前ViewHolder的位置
    }

    /**
     * @author Rango on 2019-09-03 wangqiang@smzdm.com
     */
    static class MyAdapter extends AutoWindJsonAdapter {
        public MyAdapter(String from) {
            super(from);
        }

        @Override
        protected void onGaEvent(GaInfo gaInfo) {
            //可以处理点击事件统计
            GaViewHolder holder = gaInfo.getViewHolder();
            S.i(gaInfo.getGaData());
        }
    }
}
