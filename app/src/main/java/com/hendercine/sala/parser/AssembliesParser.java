/*
 * Created by James Henderson on 2018
 * Copyright (c) Hendercine Productions and James Henderson 2018.
 * All rights reserved.
 *
 * Last modified 7/23/18 5:42 PM
 */

package com.hendercine.sala.parser;

import com.hendercine.sala.model.Assembly;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

import timber.log.Timber;

/**
 * SundayAssemblyLosAngeles-1.1 created by hendercine on 7/23/18.
 */
public class AssembliesParser extends DefaultHandler {

    private String mElementValue = null;
    private boolean mElementOn = false;
    private Assembly mAssembly;

    private String mTempTitle = "";
    private String mTempLink;
    private String mTempImage;
    private String mTempDate;
    private String mTempDescription;

    private boolean mParsingTitle = false;
    private boolean mParsingDesc = false;
    private boolean mParsingLink = false;

    private final ArrayList<Assembly> mItems;

    AssembliesParser() {
        super();
        mItems = new ArrayList<>();
    }

    public ArrayList<Assembly> getItems() {
        return mItems;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        mElementOn = true;
        if (localName.equals("item")) {
            mAssembly = new Assembly();
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
    public void endElement(String uri, String localName, String qName) throws SAXException {
        mElementOn = false;
        // Sets the values after retrieving them from the XML tags
        if (mAssembly != null) {
            if (localName.equalsIgnoreCase("item")) {
                mAssembly = new Assembly();
                mAssembly.setAssemblyDate(mTempTitle.trim());
                mAssembly.setAssemblyTheme(mTempLink);
                mAssembly.setAssemblyDescription(mTempDescription);
                mAssembly.setAssemblyPhotoUrl(mTempImage);
                mAssembly.setDescription(mTempDescription);
                if (mTempImage == null && mTempDescription != null &&
                        getImageSourceFromDescription(mTempDescription) != null) {
                    mAssembly.setAssemblyDescription(getImageSourceFromDescription(mTempDescription));
                }
                mItems.add(mAssembly);
                mTempLink = "";
                mTempImage = null;
                mTempDate = "";
                Timber.v("pended: %s", mTempTitle);
            } else if (localName.equalsIgnoreCase("Title") && !qName
                    .contains("media")) {
                mParsingTitle = false;
                mElementValue = "";
                mTempTitle = mTempTitle.replace("\n", "");
            } else if (localName.equalsIgnoreCase("link") && !mElementValue
                    .isEmpty()) {
                mParsingLink = false;
                mElementValue = "";
                mTempLink = mTempLink.replace("\n", "");
            } else if (localName.equalsIgnoreCase("image") || localName
                    .equalsIgnoreCase("url")) {
                if (mElementValue != null && !mElementValue.isEmpty()) {
                    mTempImage = mElementValue;
                }
            } else if (localName.equals("pubDate")) {
                mTempDate = mElementValue;
            } else if (localName.equals("description")) {
                mParsingDesc = false;
                mElementValue = "";
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
