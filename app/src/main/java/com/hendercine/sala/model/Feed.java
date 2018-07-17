/*
 * Created by James Henderson on 2018
 * Copyright (c) Hendercine Productions and James Henderson 2018.
 * All rights reserved.
 *
 * Last modified 7/17/18 11:51 AM
 */

package com.hendercine.sala.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * SundayAssemblyLosAngeles-1.1 created by hendercine on 7/17/18.
 */
public class Feed implements Serializable {

    @SerializedName("i")
    private Integer mFeedId;

    @SerializedName("n")
    private String mTitle;

    @SerializedName("l")
    private String mUrl;

    @SerializedName("e")
    private String mEncoding;

    public Integer getFeedId() {
        return mFeedId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getUrl() {
        return mUrl;
    }

    public String getEncoding() {
        return mEncoding;
    }
}
