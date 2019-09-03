package com.apeman.library.ga;

import com.apeman.library.holder.AutoWindViewHolder;
import com.apeman.library.holder.GaViewHolder;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Rango on 2019-09-02 wangqiang@smzdm.com
 */
public class GaInfo implements Iterator<GaInfo> {
    private GaInfo parent;
    private int eventViewId;
    private int adapterPosition;
    private GaViewHolder holder;

    public int getEventViewId() {
        return eventViewId;
    }

    public GaInfo(AutoWindViewHolder h, GaInfo parent) {
        this(-1, h, parent);
    }

    public GaInfo(int eventViewId, AutoWindViewHolder h, GaInfo parent) {
        this.eventViewId = eventViewId;
        this.holder = h;
        this.parent = parent;
    }

    public int getAdapterPosition() {
        return adapterPosition;
    }

    public void setAdapterPosition(int adapterPosition) {
        this.adapterPosition = adapterPosition;
    }

    public GaViewHolder getViewHolder() {
        return holder;
    }

    public GaInfo getParent() {
        return parent;
    }

    public String getGaData() {
        List<String> gaChains = getGaChain(this);
        String result = "";
        for (String f : gaChains) {
            result += f + "#";
        }
        return result;
    }

    private List<String> getGaChain(GaInfo e) {
        List<String> gas = new LinkedList<>();
        gas.add(e.getViewHolder().getFrom() +" AdapterPosition:"+e.getAdapterPosition());
        if (e.hasNext()) {
            gas.addAll(getGaChain(e.next()));
        }
        return gas;
    }

    @Override
    public boolean hasNext() {
        return parent != null;
    }

    @Override
    public GaInfo next() {
        return parent;
    }
}
