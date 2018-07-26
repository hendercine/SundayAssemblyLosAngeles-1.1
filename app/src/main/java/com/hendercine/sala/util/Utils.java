/*
 * Created by James Henderson on 2018
 * Copyright (c) Hendercine Productions and James Henderson 2018.
 * All rights reserved.
 *
 * Last modified 7/26/18 4:03 PM
 */

package com.hendercine.sala.util;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
/**
 * SundayAssemblyLosAngeles-1.1 created by hendercine on 7/26/18.
 */
class Utils {

    public static String readFromAssets(Context context, String fileName) {
        String ret = null;
        AssetManager assetManager = context.getAssets();
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(assetManager.open(fileName))
            );
            String mLine;
            StringBuilder builder = new StringBuilder();
            while ((mLine = reader.readLine()) != null) {
                builder.append(mLine);
            }
            ret = builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public static Date parseDate(String dateString) {
        String pattern = "EEE, dd, MMM yyyy HH:mm:ss zzz";
        SimpleDateFormat parseFormatter = new SimpleDateFormat(
                pattern,
                Locale.ENGLISH
        );
        try {
            return parseFormatter.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
