/*
 * Created by James Henderson on 2018
 * Copyright (c) Hendercine Productions and James Henderson 2018.
 * All rights reserved.
 *
 * Last modified 7/23/18 5:11 PM
 */

package com.hendercine.sala.di.component;

import com.hendercine.sala.SalaApplication;
import com.hendercine.sala.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;
/**
 * SundayAssemblyLosAngeles-1.1 created by hendercine on 7/23/18.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(SalaApplication salaApplication);
}
