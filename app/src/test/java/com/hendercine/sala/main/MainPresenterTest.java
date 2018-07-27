/*
 * Created by James Henderson on 2018
 * Copyright (c) Hendercine Productions and James Henderson 2018.
 * All rights reserved.
 *
 * Last modified 7/26/18 5:32 PM
 */

package com.hendercine.sala.main;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
/**
 * SundayAssemblyLosAngeles-1.1 created by hendercine on 7/26/18.
 */
public class MainPresenterTest {

    @Mock
    private MainContract.View mView;

    private MainPresenter mMainPresenter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mMainPresenter = new MainPresenter();
        mMainPresenter.attach(mView);
    }

    @Test
    public void testLoadRssItems() {
        mMainPresenter.loadRssFragments();
        verify(mView).onLoadRssFragments();
    }

}