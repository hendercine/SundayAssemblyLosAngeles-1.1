/*
 * Created by James Henderson on 2018
 * Copyright (c) Hendercine Productions and James Henderson 2018.
 * All rights reserved.
 *
 * Last modified 9/23/18 12:09 PM
 */

package com.hendercine.sala_v1_1.data;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.annotation.Nullable;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hendercine.sala_v1_1.models.Assembly;
import com.hendercine.sala_v1_1.ui.base.BaseActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.parceler.Parcels;

import java.util.ArrayList;

import timber.log.Timber;

/**
 * SundayAssemblyLosAngeles-1.1 created by artemis on 9/23/18.
 */
public class SalaSiteIntentService extends IntentService {

    // Base strings to run through Jsoup
    private static final String ASSEMBLIES_URL = "http://www.sundayassemblyla.org";
    private static final String LI_ELEMENT = "li";
    private static final String ASSEMBLIES = "assemblies";
    private static final String EVENT_DETAILS = "event-details";

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseRef;

    private Assembly mAssembly;
    private ArrayList<Assembly> mAssemblyArrayList;
    private ArrayList<Assembly> titleArray;
    private ArrayList<Assembly> themeArray;
    private ArrayList<Assembly> descArray;
    private ArrayList<Assembly> picsArray;

    public SalaSiteIntentService() {
        super(SalaSiteIntentService.class.getSimpleName());
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        if (intent != null) {
            final ResultReceiver rec = intent.getParcelableExtra("rec");
            mFirebaseDatabase = FirebaseDatabase.getInstance();
            mDatabaseRef = mFirebaseDatabase.getReference().child(ASSEMBLIES);

            // Check for network
            if (!BaseActivity.isNetworkAvailable(this))
                Timber.d("Get html");

            try {
                String assemblyEventName;
//                String assemblyThemeLine;
                String assemblyDescription;
                String assemblyPhotoUrl;

                Document doc = Jsoup.connect(ASSEMBLIES_URL).get();
                Elements assemblies = doc.getElementsByClass("event-list-item");
//                Elements titles = assemblies.
//                Elements themes = assemblies.select("strong");
//                Elements descriptions = assemblies.select("span");
//                Elements photoSources = assemblies.getElementsByClass
//                        ("event-wrap").select("img");

                mAssemblyArrayList = new ArrayList<>();

                for (Element assembly : assemblies) {
                    mAssembly = new Assembly();
                    Element eventName = assembly.getElementsByClass
                            ("event-name").first();
//                    Element themeLine = ;
                    Element descriptionText = assembly.getElementsByClass
                            (EVENT_DETAILS).first();
                    Element eventPhotoUrl = descriptionText.select("img")
                            .first();
                    assemblyDescription = descriptionText.toString();
                    mAssembly.setAssemblyDate(eventName.text());
                    mAssembly.setAssemblyDescription(assemblyDescription);
                    mAssembly.setAssemblyPhotoUrl(eventPhotoUrl.attr("src"));
                    mAssemblyArrayList.add(mAssembly);
                }
//                titleArray = new ArrayList<>();
//                themeArray = new ArrayList<>();
//                descArray = new ArrayList<>();
//                picsArray = new ArrayList<>();
//
//                for (Element title : titles) {
//                    mAssembly = new Assembly();
//                    assemblyEventName = title.text();
//                    mAssembly.setAssemblyDate(assemblyEventName);
//                    titleArray.add(mAssembly);
//                }
//
//                for (Element theme : themes) {
//                    mAssembly = new Assembly();
//                    assemblyThemeLine = theme.text();
//                    mAssembly.setAssemblyTheme(assemblyThemeLine);
//                    themeArray.add(mAssembly);
//                }
//
//                for (Element description : descriptions) {
//                    mAssembly = new Assembly();
//                    assemblyDescription = description.text();
//                    mAssembly.setAssemblyDescription(assemblyDescription);
//                    descArray.add(mAssembly);
//                }
//
//                for (Element photoSource : photoSources) {
//                    mAssembly = new Assembly();
//                    assemblyPhotoUrl = photoSource.attr("abs:src");
//                    mAssembly.setAssemblyPhotoUrl(assemblyPhotoUrl);
//                    picsArray.add(mAssembly);
//                }
//
//                Map<String, Object> assemblyMaps = new HashMap<>();
//                assemblyMaps.put("assembly_date", titleArray);
//                assemblyMaps.put("assembly_description", descArray);
//                assemblyMaps.put("assembly_photo_url", picsArray);
//                assemblyMaps.put("assembly_theme", themeArray);
//
//                mAssemblyArrayList.addAll(titleArray);
//                mDatabaseRef.updateChildren(assemblyMaps);
////                    mAssemblyArrayList.addAll(themeArray);
////                    mAssemblyArrayList.addAll(descArray);*-+
//
////                    mAssemblyArrayList.addAll(picsArray);
//
//                Timber.i(
//                        "Is there a title string here in svc: '%s'",
//                        titleArray.get(0).getAssemblyDate()
//                );
//                Timber.i(
//                        "Is there a title string here in svc: '%s'",
//                        mAssemblyArrayList.get(1).getAssemblyDate()
//                );
//                Timber.i(
//                        "Is there a title string here in svc: '%s'",
//                        mAssemblyArrayList.get(2).getAssemblyDate()
//                );
//                Timber.i(
//                        "Is there a title string here in svc: '%s'",
//                        mAssemblyArrayList.get(3).getAssemblyDate()
//                );
//                Timber.i(
//                        "Is there a title string here in svc: '%s'",
//                        mAssemblyArrayList.get(4).getAssemblyDate()
//                );
//                Timber.i(
//                        "Is there a theme string here in svc: '%s'",
//                        themeArray.get(0).getAssemblyTheme()
//                );
//                Timber.i(
//                        "Is there a description string here in svc: '%s'",
//                        descArray.get(0).getAssemblyDescription()
//                );
//                Timber.i(
//                        "Is there a string photo url here in svc: '%s'",
//                        picsArray.get(0).getAssemblyPhotoUrl()
//                );

                Bundle args = new Bundle();
                args.putParcelable(ASSEMBLIES, Parcels.wrap(mAssemblyArrayList));
                rec.send(0, args);

            } catch (Exception e) {
                Timber.e(e, "Something went wrong in the background");
                e.printStackTrace();
            }
        }
    }
}
