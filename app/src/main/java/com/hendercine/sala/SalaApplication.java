/*
 * Created by James Henderson on 2018
 * Copyright (c) Hendercine Productions and James Henderson 2018.
 * All rights reserved.
 *
 * Last modified 7/16/18 4:34 PM
 */

package com.hendercine.sala;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * SundayAssemblyLosAngeles-1.1 created by hendercine on 7/16/18.
 */
public class SalaApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
    }
}
