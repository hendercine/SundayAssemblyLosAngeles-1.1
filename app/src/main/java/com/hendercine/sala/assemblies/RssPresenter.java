/*
 * Created by James Henderson on 2018
 * Copyright (c) Hendercine Productions and James Henderson 2018.
 * All rights reserved.
 *
 * Last modified 7/17/18 11:23 AM
 */

package com.hendercine.sala.assemblies;

import com.hendercine.sala.base.BasePresenter;
import com.hendercine.sala.model.Feed;
import com.hendercine.sala.model.RError;
import com.hendercine.sala.model.Assembly;
import com.hendercine.sala.parser.OnRssParserListener;
import com.hendercine.sala.parser.RssReader;
import com.hendercine.sala.session.SessionData;

import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * SundayAssemblyLosAngeles-1.1 created by hendercine on 7/17/18. Code source:
 * https://medium.com/android-bits
 * /android-app-from-scratch-part-3-implementing-app-logic-2b62ae65dcc4
 */
public class RssPresenter extends BasePresenter<AssembliesContract.View> implements
                                                                         AssembliesContract.Presenter, OnRssParserListener {

    private SessionData mSessionData;

    @Inject
    public RssPresenter(SessionData sessionData) {
        mSessionData = sessionData;
    }

    @Override
    public void loadAssemblies(Feed feed, boolean fromCache) {
        if (mSessionData.hasUrl(feed.getUrl()) && fromCache) {
            Timber.v("Read from cache: %s", feed.getUrl());

            getView().onRssItemsLoaded(mSessionData.getContent(feed.getUrl()));
        } else {
            RssReader request = new RssReader(this, feed.getUrl());
            request.execute();
            getView().showLoading();
        }
    }

    @Override
    public void browseRssUrl(Assembly assembly) {
        if (isAttached()) {
            getView().onBrowse(assembly);
        }
    }

    @Override
    public void onSuccess(List<Assembly> assemblyList, String rssUrl) {
        mSessionData.addContent(rssUrl, assemblyList);
        if (isAttached()) {
            getView().onRssItemsLoaded(assemblyList);
            getView().hideLoading();
        }
    }

    @Override
    public void onFail(String rssUrl) {
        if (isAttached()) {
            getView().hideLoading();
            getView().onFail(new RError("Failed to fetch RSS!"));
        }
    }

    public SessionData getSessionData() {
        return mSessionData;
    }
}
