/*
 * Created by James Henderson on 2018
 * Copyright (c) Hendercine Productions and James Henderson 2018.
 * All rights reserved.
 *
 * Last modified 7/17/18 11:51 AM
 */

package com.hendercine.sala.model;

/**
 * SundayAssemblyLosAngeles-1.1 created by hendercine on 7/17/18.
 */
public class RError {

    private final String mMessage;

    public RError(String message) {
        mMessage = message;
    }

    public String getMessage() {
        return mMessage;
    }
}
