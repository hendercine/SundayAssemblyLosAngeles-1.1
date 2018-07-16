/*
 * Created by James Henderson on 2018
 * Copyright (c) Hendercine Productions and James Henderson 2018.
 * All rights reserved.
 *
 * Last modified 7/12/18 4:42 PM
 */

package com.hendercine.sala.main;

import com.hendercine.sala.base.BasePresenter;

import java.util.Random;

import javax.inject.Inject;
/**
 * SundayAssemblyLosAngeles-1.1 created by hendercine on 7/12/18. MVP
 * structure code sourced from https://medium.com/android-bits
 * /android-app-from-scratch-part-1-model-view-presenter-b5f629f2d9a1
 */
public class MainPresenter extends BasePresenter<MainContract.View>
        implements MainContract.Presenter {

    private String[] helloTexts = {"BONJOUR", "HOLA", "HALLO", "MERHABA",
            "HELLO", "CIAO", "KONNICHIWA"};

    @Inject
    public MainPresenter() {
    }

    @Override
    public void loadHelloText() {
        Random random = new Random();
        String hello = helloTexts[random.nextInt(helloTexts.length)];
        getView().onTextLoaded(hello);

    }
}
