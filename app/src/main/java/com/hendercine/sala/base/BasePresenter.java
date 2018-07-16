/*
 * Created by James Henderson on 2018
 * Copyright (c) Hendercine Productions and James Henderson 2018.
 * All rights reserved.
 *
 * Last modified 7/12/18 4:17 PM
 */

package com.hendercine.sala.base;

/**
 * SundayAssemblyLosAngeles-1.1 created by hendercine on 7/12/18.
 */
public class BasePresenter<V extends BaseView> implements BaseMvpPresenter<V> {

    private V mView;

    @Override
    public void attach(V view) {
        mView = view;
    }

    @Override
    public void detach() {
        mView = null;
    }

    @Override
    public boolean isAttached() {
        return mView != null;
    }

    public V getView() {
        return mView;
    }
}