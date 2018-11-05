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
public class Event {

    // Fields must be public for Parceler
    @SuppressWarnings("WeakerAccess")
    public String mEventDate;
    @SuppressWarnings("WeakerAccess")
    public String mEventVenue;
    @SuppressWarnings("WeakerAccess")
    public String mEventPhotoUrl;

    public Event() {
        // Neccessary empty constructor for Parceler
    }

    public String getEventDate() {
        return mEventDate;
    }

    public void setEventDate(String eventDate) {
        mEventDate = eventDate;
    }

    public String getEventVenue() {
        return mEventVenue;
    }

    public void setEventVenue(String eventVenue) {
        mEventVenue = eventVenue;
    }

    public String getEventPhotoUrl() {
        return mEventPhotoUrl;
    }

    public void setEventPhotoUrl(String eventPhotoUrl) {
        mEventPhotoUrl = eventPhotoUrl;
    }
}
