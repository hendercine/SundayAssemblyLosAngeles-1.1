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
public class Assembly {

    private String mAssemblyDate;
    private String mAssemblyTheme;
    private String mAssemblyDescription;
    private String mAssemblyPhotoUrl;

    private int mFeedId;
    private String mFeedTitle;
    private String mDescription;

    public String getAssemblyDate() {
        return mAssemblyDate;
    }

    public void setAssemblyDate(String assemblyDate) {
        mAssemblyDate = assemblyDate;
    }

    public String getAssemblyTheme() {
        return mAssemblyTheme;
    }

    public void setAssemblyTheme(String assemblyTheme) {
        mAssemblyTheme = assemblyTheme;
    }

    public String getAssemblyDescription() {
        return mAssemblyDescription;
    }

    public void setAssemblyDescription(String assemblyDescription) {
        mAssemblyDescription = assemblyDescription;
    }

    public String getAssemblyPhotoUrl() {
        return mAssemblyPhotoUrl;
    }

    public void setAssemblyPhotoUrl(String assemblyPhotoUrl) {
        mAssemblyPhotoUrl = assemblyPhotoUrl;
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
