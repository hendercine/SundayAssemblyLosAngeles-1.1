/*
 * Created by James Henderson on 2018
 * Copyright (c) Hendercine Productions and James Henderson 2018.
 * All rights reserved.
 *
 * Last modified 7/17/18 11:14 AM
 */

package com.hendercine.sala.assemblies;

import com.hendercine.sala.base.AsyncCallbackView;
import com.hendercine.sala.base.BaseMvpPresenter;
import com.hendercine.sala.base.BaseView;
import com.hendercine.sala.model.Feed;
import com.hendercine.sala.model.Assembly;

import java.util.List;
/**
 * SundayAssemblyLosAngeles-1.1 created by hendercine on 7/17/18. Code source:
 * https://medium.com/android-bits
 * /android-app-from-scratch-part-3-implementing-app-logic-2b62ae65dcc4
 */
public interface AssembliesContract {

    // User actions. Presenter will implement
    interface Presenter extends BaseMvpPresenter<AssembliesContract.View> {
        void loadAssemblies(Feed feed, boolean fromCache);
        void browseAssemblyUrl(Assembly assembly);
    }

    // Action callbacks. Activity/Fragment will implement
    interface View extends BaseView, AsyncCallbackView {

        void onAssemblyItemsLoaded(List<Assembly> assemblies);

        void onBrowse(Assembly assembly);

    }

}