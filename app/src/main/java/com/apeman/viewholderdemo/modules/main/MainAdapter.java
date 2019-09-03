package com.apeman.viewholderdemo.modules.main;

import com.apeman.library.ga.GaInfo;
import com.apeman.viewholderdemo.base.AutoWindJsonAdapter;

import io.github.heartinfei.slogger.S;

/**
 * @author Rango on 2019-09-03 wangqiang@smzdm.com
 */
public class MainAdapter extends AutoWindJsonAdapter {
    public MainAdapter(String from) {
        super(from);
    }

    @Override
    protected void onGaEvent(GaInfo gaInfo) {
        S.i(gaInfo.getGaData());
    }
}
