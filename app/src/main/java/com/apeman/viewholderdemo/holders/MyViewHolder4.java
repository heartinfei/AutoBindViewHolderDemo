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
import com.apeman.library.holder.impls.JsonAutoWindViewHolder;
import com.apeman.library.protocl.GaCallback;
import com.apeman.viewholderdemo.HolderPool;
import com.apeman.viewholderdemo.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

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
        myAdapter = new MyAdapter("二级From");
        myAdapter.regGaCallback(childGaInfo -> deliverGaEvent(new GaInfo(this, childGaInfo)));
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

    }

    /**
     * @author Rango on 2019-09-03 wangqiang@smzdm.com
     */
    static class MyAdapter extends RecyclerView.Adapter<AutoWindViewHolder> implements GaCallback {
        private List<JSONObject> dataSource = new LinkedList<>();
        private GaCallback nextGaCallback = null;
        private final String from;

        public MyAdapter(String from) {
            this.from = from;
        }

        public void regGaCallback(GaCallback gaCallback) {
            this.nextGaCallback = gaCallback;
        }

        @NonNull
        @Override
        public AutoWindViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return HolderPool.getViewHolderByType(viewType, parent)
                    .withGaCallback(this)
                    .withFrom(from);
        }

        @Override
        public void handleGaEvent(GaInfo gaInfo) {
            if (nextGaCallback != null) {
                nextGaCallback.handleGaEvent(gaInfo);
            }
        }

        public void setData(List<JSONObject> ds) {
            dataSource.clear();
            if (ds != null) {
                dataSource.addAll(ds);
            }
            notifyDataSetChanged();
        }

        @Override
        public void onBindViewHolder(@NonNull AutoWindViewHolder holder, int position) {
            JSONObject data = dataSource.get(position);
            holder.bindData(data);
        }

        @Override
        public int getItemViewType(int position) {
            JSONObject data = dataSource.get(position);
            int type = 0;
            try {
                type = data.getInt("cellType");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return type;
        }

        @Override
        public int getItemCount() {
            return dataSource.size();
        }
    }
}
