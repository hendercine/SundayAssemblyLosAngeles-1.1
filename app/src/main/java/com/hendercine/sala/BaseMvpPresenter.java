/*
 * Created by James Henderson on 2018
 * Copyright (c) Hendercine Productions and James Henderson 2018.
 * All rights reserved.
 *
 * Last modified 7/12/18 4:14 PM
 */

package com.hendercine.sala;

/**
 * SundayAssemblyLosAngeles-1.1 created by hendercine on 7/12/18.
 */
public interface BaseMvpPresenter<V extends BaseView> {

    void attach(V view);

    void detach();

    boolean isAttached();

}
