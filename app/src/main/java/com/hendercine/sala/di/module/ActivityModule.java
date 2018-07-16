package com.hendercine.sala.di.module;

import android.content.Context;

import com.hendercine.sala.chrome.ChromeTabsWrapper;
import com.hendercine.sala.main.MainContract;
import com.hendercine.sala.main.MainPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by James Henderson on 7/13/18. MVP
 * structure code sourced from https://medium.com/android-bits
 * /android-app-from-scratch-part-1-model-view-presenter-b5f629f2d9a1
 */

@Module
public class ActivityModule {

    private Context mContext;

    public ActivityModule(Context context) {
        mContext = context;
    }

    @Provides
    @Singleton
    ChromeTabsWrapper providesChromeTabsWrapper() {
        return new ChromeTabsWrapper(mContext);
    }

    @Provides
    @Singleton
    MainContract.Presenter providesMainPresenter() {
        return new MainPresenter();
    }

}