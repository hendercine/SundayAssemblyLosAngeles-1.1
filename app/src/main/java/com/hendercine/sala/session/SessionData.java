/*
 * Created by James Henderson on 2018
 * Copyright (c) Hendercine Productions and James Henderson 2018.
 * All rights reserved.
 *
 * Last modified 7/17/18 11:41 AM
 */

package com.hendercine.sala.session;

import com.hendercine.sala.model.Assembly;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
/**
 * SundayAssemblyLosAngeles-1.1 created by hendercine on 7/17/18.
 */
public class SessionData {

    @Inject
    public SessionData() {
    }

    private final HashMap<String, List<Assembly>> mContentMap = new HashMap<>();

    public boolean hasUrl(String url) {
        return mContentMap.containsKey(url);
    }

    public void addContent(String url, List<Assembly> items) {
        mContentMap.put(url, items);
    }

    public List<Assembly> getContent(String url) {
        return mContentMap.get(url);
    }
}
