/*
 * Created by James Henderson on 2018
 * Copyright (c) Hendercine Productions and James Henderson 2018.
 * All rights reserved.
 *
 * Last modified 9/23/18 12:15 PM
 */

package com.hendercine.sala_v1_1.models;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import org.parceler.Parcel;

import java.util.HashMap;
import java.util.Map;
/**
 * SundayAssemblyLosAngeles-1.1 created by artemis on 9/23/18.
 */

@SuppressWarnings("WeakerAccess")
@IgnoreExtraProperties
@Parcel
public class Assembly {

    // Fields must be public for Parceler
    public String mAssemblyDate;
    public String mAssemblyTheme;
    public String mAssemblyDescription;
    public String mAssemblyPhotoUrl;

    public Assembly() {
        // Neccessary empty constructor for Parceler
    }

    public Assembly(String assemblyDate, String assemblyTheme, String assemblyDescription, String assemblyPhotoUrl) {
        this.mAssemblyDate = assemblyDate;
        this.mAssemblyTheme = assemblyTheme;
        this.mAssemblyDescription = assemblyDescription;
        this.mAssemblyPhotoUrl = assemblyPhotoUrl;
    }

    public void setAssemblyDate(String assemblyDate) {
        mAssemblyDate = assemblyDate;
    }

    public String getAssemblyDate() {
        return mAssemblyDate;
    }

    public void setAssemblyTheme(String assemblyTheme) {
        mAssemblyTheme = assemblyTheme;
    }

    public String getAssemblyTheme() {
        return mAssemblyTheme;
    }

    public void setAssemblyDescription(String assemblyDescription) {
        mAssemblyDescription = assemblyDescription;
    }

    public String getAssemblyDescription() {
        return mAssemblyDescription;
    }

    public void setAssemblyPhotoUrl(String assemblyPhotoUrl) {
        mAssemblyPhotoUrl = assemblyPhotoUrl;
    }

    public String getAssemblyPhotoUrl() {
        return mAssemblyPhotoUrl;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("assembly_date", mAssemblyDate);
        result.put("assembly_theme", mAssemblyTheme);
        result.put("assembly_description", mAssemblyDescription);
        result.put("assembly_photo_url", mAssemblyPhotoUrl);

        return result;
    }
}
