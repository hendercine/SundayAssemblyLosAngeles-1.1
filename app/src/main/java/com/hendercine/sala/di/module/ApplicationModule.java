/*
 * Created by James Henderson on 2018
 * Copyright (c) Hendercine Productions and James Henderson 2018.
 * All rights reserved.
 *
 * Last modified 7/17/18 12:37 PM
 */

package com.hendercine.sala.di.module;

import com.hendercine.sala.SalaApplication;
import com.hendercine.sala.session.SessionData;

import javax.inject.Singleton;

import dagger.Provides;
/**
 * SundayAssemblyLosAngeles-1.1 created by hendercine on 7/17/18.
 */
public class ApplicationModule {

    private final SalaApplication mApplication;

    public ApplicationModule(SalaApplication application) {
        mApplication = application;
    }

    @Provides
    SalaApplication providesApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    SessionData providesSessionData() {
        return new SessionData();
    }
}
