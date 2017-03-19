package com.itant.messmessage;

import android.app.Application;

import org.xutils.x;

/**
 * Created by Jason on 2016/8/16.
 */
public class MessApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        x.Ext.init(this);
    }
}
