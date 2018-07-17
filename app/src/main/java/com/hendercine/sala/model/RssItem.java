/*
 * Created by James Henderson on 2018
 * Copyright (c) Hendercine Productions and James Henderson 2018.
 * All rights reserved.
 *
 * Last modified 7/17/18 11:49 AM
 */

package com.hendercine.sala.model;

/**
 * SundayAssemblyLosAngeles-1.1 created by hendercine on 7/17/18.
 */
public class RssItem {

    private String mTitle;
    private String mUrl;
    private String mImageUrl;
    private String mPubdate;

    private int mFeedId;
    private String mFeedTitle;
    private String mDescription;

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }

    public String getPubdate() {
        return mPubdate;
    }

    public void setPubdate(String pubdate) {
        mPubdate = pubdate;
    }

    public int getFeedId() {
        return mFeedId;
    }

    public void setFeedId(int feedId) {
        mFeedId = feedId;
    }

    public String getFeedTitle() {
        return mFeedTitle;
    }

    public void setFeedTitle(String feedTitle) {
        mFeedTitle = feedTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }
}
