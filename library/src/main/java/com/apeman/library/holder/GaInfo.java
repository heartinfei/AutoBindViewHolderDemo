package com.apeman.library.holder;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Rango on 2019-09-02 wangqiang@smzdm.com
 */
public class GaInfo implements Iterator<GaInfo> {
    private AutoWindViewHolder holder;
    private GaInfo parent = null;

    public GaInfo(AutoWindViewHolder h) {
        this.holder = h;
    }

    public GaInfo(AutoWindViewHolder h, GaInfo parent) {
        this.holder = h;
        this.parent = parent;
    }

    public AutoWindViewHolder getViewHolder() {
        return holder;
    }

    public GaInfo getParent() {
        return parent;
    }

    public String getGaData() {
        List<String> gaChains = getGaChain(this);
        String result = "";
        for (String f : gaChains) {
            result += f + "_";
        }
        return result;
    }

    private List<String> getGaChain(GaInfo e) {
        List<String> gas = new LinkedList<>();
        gas.add(e.getViewHolder().toString());
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
