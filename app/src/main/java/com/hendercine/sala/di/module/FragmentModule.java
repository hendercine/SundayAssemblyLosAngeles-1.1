/*
 * Created by James Henderson on 2018
 * Copyright (c) Hendercine Productions and James Henderson 2018.
 * All rights reserved.
 *
 * Last modified 7/17/18 12:48 PM
 */

package com.hendercine.sala.di.module;

import com.hendercine.sala.assemblies.AssembliesContract;
import com.hendercine.sala.assemblies.AssembliesPresenter;
import com.hendercine.sala.session.SessionData;

import dagger.Module;
import dagger.Provides;
/**
 * SundayAssemblyLosAngeles-1.1 created by hendercine on 7/17/18.
 */

@Module
public class FragmentModule {

    @Provides
    AssembliesContract.Presenter providesRssPresenter(SessionData sessionData) {
        return new AssembliesPresenter(sessionData);
    }

}
