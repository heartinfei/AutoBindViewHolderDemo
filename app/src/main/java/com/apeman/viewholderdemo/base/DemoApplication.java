package com.apeman.viewholderdemo.base;

import android.app.Application;

import com.apeman.viewholderdemo.BuildConfig;

import io.github.heartinfei.slogger.S;
import io.github.heartinfei.slogger.SConfiguration;
import io.github.heartinfei.slogger.plan.DebugPlan;

/**
 * @author Rango on 2019-09-03 wangqiang@smzdm.com
 */
public class DemoApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        S.init(new SConfiguration()
                .setPrintTrackInfo(false)
                .setTrackFilter(BuildConfig.APPLICATION_ID))
                .addPlans(new DebugPlan());
    }
}
