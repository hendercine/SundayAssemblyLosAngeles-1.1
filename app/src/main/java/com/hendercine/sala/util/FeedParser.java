/*
 * Created by James Henderson on 2018
 * Copyright (c) Hendercine Productions and James Henderson 2018.
 * All rights reserved.
 *
 * Last modified 7/26/18 3:59 PM
 */

package com.hendercine.sala.util;

import android.content.Context;

import com.google.gson.Gson;
import com.hendercine.sala.model.Feed;

import java.util.Arrays;
import java.util.List;
/**
 * SundayAssemblyLosAngeles-1.1 created by hendercine on 7/26/18.
 */
public class FeedParser {

    private static final String RSS_FILE = "rss.json";

    public FeedParser() {
    }

    public List<Feed> parseFeeds(Context context) {
        String jsonString = Utils.readFromAssets(context, RSS_FILE);
        Gson gson = new Gson();
        Feed[] feeds = gson.fromJson(jsonString, Feed[].class);
        return Arrays.asList(feeds);
    }
}
