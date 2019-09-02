package com.apeman.library.protocl;

import com.apeman.library.ga.GaInfo;

/**
 * @author Rango on 2019-09-02 wangqiang@smzdm.com
 */
public interface GaCallback {
    /**
     * 处理GA统计
     *
     * @param gaInfo
     */
    void handleGaEvent(GaInfo gaInfo);
}
