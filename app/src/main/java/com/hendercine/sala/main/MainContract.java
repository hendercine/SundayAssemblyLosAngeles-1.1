/*
 * Created by James Henderson on 2018
 * Copyright (c) Hendercine Productions and James Henderson 2018.
 * All rights reserved.
 *
 * Last modified 7/12/18 4:40 PM
 */

package com.hendercine.sala.main;

import com.hendercine.sala.base.BaseMvpPresenter;
import com.hendercine.sala.base.BaseView;

/**
 * SundayAssemblyLosAngeles-1.1 created by hendercine on 7/12/18.
 */

public interface MainContract {

    // User actions. Presenter will implement
    interface Presenter extends BaseMvpPresenter<View> {
        void loadHelloText();
    }

    // Action callbacks. Activity/Fragment will implement
    interface View extends BaseView {
        void onTextLoaded(String text);
    }

}