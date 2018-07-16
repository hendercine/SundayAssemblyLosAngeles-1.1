/*
 * Created by James Henderson on 2018
 * Copyright (c) Hendercine Productions and James Henderson 2018.
 * All rights reserved.
 *
 * Last modified 7/12/18 4:14 PM
 */

package com.hendercine.sala.base;

/**
 * SundayAssemblyLosAngeles-1.1 created by hendercine on 7/12/18.
 */

/**
 * Each presenter must implement this interface
 *
 * @param <V> View for the presenter
 */
public interface BaseMvpPresenter<V extends BaseView> {

    /**
     * Called when view attached to presenter
     *
     * @param view
     */
    void attach(V view);

    /**
     * Called when view is detached from presenter
     */
    void detach();

    /**
     * @return true if view is attached to presenter
     */
    boolean isAttached();
}
