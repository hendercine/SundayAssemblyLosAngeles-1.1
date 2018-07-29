/*
 * Created by James Henderson on 2018
 * Copyright (c) Hendercine Productions and James Henderson 2018.
 * All rights reserved.
 *
 * Last modified 7/17/18 1:06 PM
 */

package com.hendercine.sala.di.component;

import com.hendercine.sala.di.module.FragmentModule;
import com.hendercine.sala.assemblies.RssFragment;

import dagger.Component;
/**
 * SundayAssemblyLosAngeles-1.1 created by hendercine on 7/17/18.
 */

@Component(modules = {FragmentModule.class})
public interface FragmentComponent {

    void inject(RssFragment obj);

}
