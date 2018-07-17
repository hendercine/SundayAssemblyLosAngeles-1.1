/*
 * Created by James Henderson on 2018
 * Copyright (c) Hendercine Productions and James Henderson 2018.
 * All rights reserved.
 *
 * Last modified 7/17/18 11:23 AM
 */

package com.hendercine.sala.rss;

import com.hendercine.sala.base.BasePresenter;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * SundayAssemblyLosAngeles-1.1 created by hendercine on 7/17/18. Code source:
 * https://medium.com/android-bits
 * /android-app-from-scratch-part-3-implementing-app-logic-2b62ae65dcc4
 */
public class RssPresenter extends BasePresenter<RssContract.View> implements
        RssContract.Presenter, OnRssParserListener {
}
