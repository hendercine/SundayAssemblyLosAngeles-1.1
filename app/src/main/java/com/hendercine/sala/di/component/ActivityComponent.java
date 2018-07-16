package com.hendercine.sala.di.component;

import com.hendercine.sala.di.module.ActivityModule;
import com.hendercine.sala.main.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by James Henderson on 7/13/18. MVP
 * structure code sourced from https://medium.com/android-bits
 * /android-app-from-scratch-part-1-model-view-presenter-b5f629f2d9a1
 */

@Singleton
@Component(modules = {ActivityModule.class})
public interface ActivityComponent {

    void inject(MainActivity obj);

}