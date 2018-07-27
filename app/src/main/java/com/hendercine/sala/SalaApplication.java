/*
 * Created by James Henderson on 2018
 * Copyright (c) Hendercine Productions and James Henderson 2018.
 * All rights reserved.
 *
 * Last modified 7/16/18 4:34 PM
 */

package com.hendercine.sala;

import android.app.Application;

import com.hendercine.sala.di.component.ApplicationComponent;
import com.hendercine.sala.di.component.DaggerApplicationComponent;
import com.hendercine.sala.di.module.ApplicationModule;
import com.hendercine.sala.session.SessionData;
import com.squareup.leakcanary.LeakCanary;

import javax.inject.Inject;

/**
 * SundayAssemblyLosAngeles-1.1 created by hendercine on 7/16/18.
 */
public class SalaApplication extends Application {

    // TODO LIST MASTER:
    // LINKS:
    // Version 1.0 link:
    // https://github .com/hendercine/SundayAssemblyLosAngeles
    // Best Practices link:
    // https://github.com/futurice/android-best-practices
    // MVP Blog link:
    // https://medium.com/android-bits/android-app-from-scratch-part-1-model-view-presenter-b5f629f2d9a1

    // BRANCHES:
    // master
    // mvp

    // This is the initial to do list for v-1.1. It is not required to add
    // further todos here and can be added directly to the file where the
    // task is required but if the task pertains to pulling code from
    // Version 1.0, then the todos should be added here.
    // The sections of this list are not in chronological order with the
    // exception of the Code styling section which should be left open until
    // all the basic elements from the Version 1.0 repo are copied to this
    // project.
    //
    // Find or add important links and new branches above this block
    // of code.

    // Styles:
    // TODO: Copy colors.xml from Version 1.0
    // TODO: Copy styles.xml from Version 1.0
    // TODO: Copy strings.xml from Version 1.0
    // TODO: Build dimens.xml according to best-practices

    // Docs:
    // TODO: Copy README.md from Version 1.0
    // TODO: Update README.md with new goals, installation and collab instructions

    // UI:
    // TODO: Copy or recreate xml layout files from Version 1.0

    // Architecture (Must be completed in order):
    // TODO: Complete building the MVP structure based on the blog listed above
    // (on mvp branch)
    // TODO: Refactor "Rss" to become the SALA Main/"Home" activity
    // TODO: Merge mvp into master
    // TODO: Repeat MVP structure for each app activity/View

    // Code styling
    // TODO: Rename (if necessary) colors in colors.xml to match best-practices
    // TODO: Modify styles.xml to conform to best practices
    // TODO: Modify strings.xml to conform to best practices
    // TODO: Modify xml layout files to conform to best practices

    private ApplicationComponent mApplicationComponent;

    @Inject
    SessionData mSessionData;

    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);

        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        mApplicationComponent.inject(this);
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    public SessionData getSessionData() {
        return mSessionData;
    }
}
