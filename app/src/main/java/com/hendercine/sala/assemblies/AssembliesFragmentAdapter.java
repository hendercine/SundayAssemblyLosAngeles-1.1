/*
 * Created by James Henderson on 2018
 * Copyright (c) Hendercine Productions and James Henderson 2018.
 * All rights reserved.
 *
 * Last modified 7/17/18 12:57 PM
 */

package com.hendercine.sala.assemblies;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * SundayAssemblyLosAngeles-1.1 created by hendercine on 7/17/18.
 */
public class AssembliesFragmentAdapter extends FragmentPagerAdapter {

    private final List<AssembliesFragment> mAssembliesFragments;
    private final List<String> mTitles;

    public AssembliesFragmentAdapter(FragmentManager fm, List<AssembliesFragment> assembliesFragments, List<String> titles) {
        super(fm);
        mAssembliesFragments = assembliesFragments;
        mTitles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return mAssembliesFragments.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }

    @Override
    public int getCount() {
        return mAssembliesFragments.size();
    }
}
