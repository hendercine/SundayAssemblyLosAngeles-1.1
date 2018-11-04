/*
 * Created by James Henderson on 2018
 * Copyright (c) Hendercine Productions and James Henderson 2018.
 * All rights reserved.
 *
 * Last modified 11/3/18 9:10 PM
 */

package com.hendercine.sala_v1_1.models;

import org.parceler.Parcel;
/**
 * SundayAssemblyLosAngeles-1.1 created by James Henderson on 11/3/18.
 */

@Parcel
public class BetterEvent {

    // Fields must be public for Parceler
    @SuppressWarnings("WeakerAccess")
    public String mBetterEventDate;
    @SuppressWarnings("WeakerAccess")
    public String mBetterEventVenue;
    @SuppressWarnings("WeakerAccess")
    public String mBetterEventPhotoUrl;

    public BetterEvent() {
        // Neccessary empty constructor for Parceler
    }

    public String getBetterEventDate() {
        return mBetterEventDate;
    }

    public void setBetterEventDate(String betterEventDate) {
        mBetterEventDate = betterEventDate;
    }

    public String getBetterEventVenue() {
        return mBetterEventVenue;
    }

    public void setBetterEventVenue(String betterEventVenue) {
        mBetterEventVenue = betterEventVenue;
    }

    public String getBetterEventPhotoUrl() {
        return mBetterEventPhotoUrl;
    }

    public void setBetterEventPhotoUrl(String betterEventPhotoUrl) {
        mBetterEventPhotoUrl = betterEventPhotoUrl;
    }
}
