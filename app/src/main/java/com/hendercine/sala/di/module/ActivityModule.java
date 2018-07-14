package com.hendercine.sala.di.module;

import android.content.Context;

import com.hendercine.sala.chrome.ChromeTabsWrapper;
import com.hendercine.sala.main.MainContract;
import com.hendercine.sala.main.MainPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by James Henderson on 7/13/18.
 */

@Module
public class ActivityModule {

    private Context mContext;

    public ActivityModule(Context context) {
        this.mContext = context;
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
