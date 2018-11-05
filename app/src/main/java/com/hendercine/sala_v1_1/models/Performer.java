/*
 * Created by James Henderson on 2018
 * Copyright (c) Hendercine Productions and James Henderson 2018.
 * All rights reserved.
 *
 * Last modified 11/5/18 11:25 AM
 */

package com.hendercine.sala_v1_1.models;

import org.parceler.Parcel;
/**
 * SundayAssemblyLosAngeles-1.1 created by hendercine on 11/5/18.
 */

@Parcel
public class Performer {

    // Fields must be public for Parceler
    @SuppressWarnings("WeakerAccess")
    public String mPerformerName;
    @SuppressWarnings("WeakerAccess")
    public String mPerformerBio;
    @SuppressWarnings("WeakerAccess")
    public String mPerformerPicUrl;

    public Performer() {
        // Empty constructor required by Parceler
    }

    public String getPerformerName() {
        return mPerformerName;
    }

    public void setPerformerName(String performerName) {
        mPerformerName = performerName;
    }

    public String getPerformerBio() {
        return mPerformerBio;
    }

    public void setPerformerBio(String performerBio) {
        mPerformerBio = performerBio;
    }

    public String getPerformerPicUrl() {
        return mPerformerPicUrl;
    }

    public void setPerformerPicUrl(String performerPicUrl) {
        mPerformerPicUrl = performerPicUrl;
    }
}
