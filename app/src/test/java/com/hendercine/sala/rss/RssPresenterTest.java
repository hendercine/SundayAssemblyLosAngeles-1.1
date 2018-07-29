/*
 * Created by James Henderson on 2018
 * Copyright (c) Hendercine Productions and James Henderson 2018.
 * All rights reserved.
 *
 * Last modified 7/26/18 5:51 PM
 */

package com.hendercine.sala.rss;

import com.hendercine.sala.model.Feed;
import com.hendercine.sala.model.RError;
import com.hendercine.sala.model.Assembly;
import com.hendercine.sala.session.SessionData;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.verify;
/**
 * SundayAssemblyLosAngeles-1.1 created by hendercine on 7/26/18.
 */
public class RssPresenterTest {

    public static final String MOCK_URL = "MOCK_URL";
    private List<Assembly> MOCK_RSS_ITEMS = new ArrayList<>();
    private Feed mFeed = new Feed();
    private RssPresenter mRssPresenter;

    @Mock
    private RssContract.View mView;
    @Captor
    private ArgumentCaptor<List<Assembly>> mCaptorRssItems;
    @Captor
    private ArgumentCaptor<RError> mCaptorError;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        generateMockRssItems();

        SessionData sessionData = new SessionData();
        mRssPresenter = new RssPresenter(sessionData);
        mRssPresenter.getSessionData().addContent(MOCK_URL, MOCK_RSS_ITEMS);
        mRssPresenter.attach(mView);
    }

    private void generateMockRssItems() {
        Assembly assembly = new Assembly();
        assembly.setAssemblyTheme("http://");
        assembly.setAssemblyDate("title");
        assembly.setDescription("description");

        MOCK_RSS_ITEMS.add(assembly);
        mFeed.setUrl(MOCK_URL);
    }

    @Test
    public void testLoadRssItems() {
        mRssPresenter.onSuccess(MOCK_RSS_ITEMS, MOCK_URL);
        verify(mView).onRssItemsLoaded(mCaptorRssItems.capture());
        verify(mView).hideLoading();

        assertTrue(mCaptorRssItems.getValue().equals(MOCK_RSS_ITEMS));
    }

    @Test
    public void testLoadRssItemsFromCache() {
        mRssPresenter.loadRssItems(mFeed, true);
        verify(mView).onRssItemsLoaded(MOCK_RSS_ITEMS);
    }

    @Test
    public void testLoadFail() {
        mRssPresenter.onFail(MOCK_URL);
        verify(mView).hideLoading();
        verify(mView).onFail(mCaptorError.capture());

        assertTrue(mCaptorError.getValue().getMessage().equals(RError
                .ERROR_FETCH));
    }
}