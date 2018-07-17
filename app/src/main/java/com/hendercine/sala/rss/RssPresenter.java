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

    private SessionData mSessionData;

    @Inject
    public RssPresenter(SessionData sessionData) {
        mSessionData = sessionData;
    }

    @Override
    public void loadRssItems(Feed feed, boolean fromCache) {
        if (mSessionData.hasUrl(feed.getUrl()) && fromCache) {
            Timber.v("Read from cache: " + feed.getUrl());

            getView().onRssItemsLoaded(mSessionData.getContent(feed.getUrl()));
        } else {
            RssReader request = new RssReader(this, feed.getUrl());
            request.execute();
            getView().showLoading();
        }
    }

    @Override
    public void browseRssUrl(RssItem rssItem) {
        if (isAttached()) {
            getView().onBrowse(rssItem);
        }
    }

    @Override
    public void onSuccess(List<RssItem> rssItemList, String rssUrl) {
        mSessionData.addContent(rssUrl, rssItemList);
        if (isAttached()) {
            getView().onRssItemsLoaded(rssItemList);
            getView().hideLoading();
        }
    }

    @Override
    public void onFail(String rssUrl) {
        if (isAttached()) {
            getView().hideLoading();
            getView().onFail(new RError("Failed to RSS!"));
        }
    }
}
