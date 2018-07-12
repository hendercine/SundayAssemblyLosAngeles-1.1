/*
 * Created by James Henderson on 2018
 * Copyright (c) Hendercine Productions and James Henderson 2018.
 * All rights reserved.
 *
 * Last modified 7/12/18 4:40 PM
 */

package com.hendercine.sala;

/**
 * SundayAssemblyLosAngeles-1.1 created by hendercine on 7/12/18.
 */
public interface MainContract {

    interface Presenter extends BaseMvpPresenter<MainContract.View> {

        void loadHelloText();

    }


    interface View extends BaseView {

        void onTextLoaded(String text);

    }
}
