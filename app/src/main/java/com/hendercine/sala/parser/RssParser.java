/*
 * Created by James Henderson on 2018
 * Copyright (c) Hendercine Productions and James Henderson 2018.
 * All rights reserved.
 *
 * Last modified 7/23/18 5:42 PM
 */

package com.hendercine.sala.parser;

import com.hendercine.sala.model.RssItem;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

/**
 * SundayAssemblyLosAngeles-1.1 created by hendercine on 7/23/18.
 */
public class RssParser extends DefaultHandler {

    private String mElementValue = null;
    private boolean mElementOn = false;
    private RssItem mRssItem;

    private String mTempTitle = "";
    private String mTempLink;
    private String mTempImage;
    private String mTempPubdate;
    private String mTempDescription;

    private boolean mParsingTitle = false;
    private boolean mParsingDesc = false;
    private boolean mParsingLink = false;

    private final ArrayList<RssItem> mItems;

    public RssParser() {
        super();
        mItems = new ArrayList<>();
    }

    public ArrayList<RssItem> getItems() {
        return mItems;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        mElementOn = true;
        if (localName.equals("item")) {
            mRssItem = new RssItem();
        } else if (localName.equalsIgnoreCase("title") && !qName.contains
                ("media")) {
            mParsingTitle = true;
            mTempTitle = "";
        } else if (localName.equalsIgnoreCase("description")) {
            mParsingDesc = true;
            mTempDescription = "";
        } else if (localName.equalsIgnoreCase("link") && !qName.equals
                ("atom:link")) {
            mParsingLink = true;
            mTempLink = "";
        }
        if (attributes != null) {
            String url = attributes.getValue("url");
            if (url != null && !url.isEmpty()) {
                mTempImage = url;
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        String buff = new String(ch, start, length);
        if (mElementOn) {
            if (buff.length() > 2) {
                mElementValue = buff;
                mElementOn = false;
            }
        }
        if (mParsingTitle) {
            mTempTitle = mTempTitle + buff;
        }
        if (mParsingDesc) {
            mTempDescription = mTempDescription + buff;
        }
        if (mParsingLink) {
            mTempLink = mTempLink + buff;
        }
    }


    private String getImageSourceFromDescription(String description) {
        if (description.contains("<img") && description.contains("src")) {
            String[] parts = description.split("src=\"");
            if (parts.length == 2 && parts[1].length() > 0) {
                String src = parts[1].substring(0, parts[1].indexOf("\""));
                String[] srcParts = src.split("http");
                if (srcParts.length > 2) {
                    src = "http" + srcParts[2];
                }
                return src;
            }
        }
        return null;
    }
}
