/*
 * Created by James Henderson on 2018
 * Copyright (c) Hendercine Productions and James Henderson 2018.
 * All rights reserved.
 *
 * Last modified 10/30/18 1:58 PM
 */

package com.hendercine.sala_v1_1.ui.livebetter;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hendercine.sala_v1_1.R;
import com.hendercine.sala_v1_1.ui.base.BaseFragment;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class LiveBetterFragment extends BaseFragment {

    private LinearLayoutManager mLinearLayoutManager;
//    private LiveBetterRVAdapter mAdapter;
    private Unbinder mUnbinder;

    @BindView(R.id.live_better_recycler_view)
    RecyclerView mBetterEventsRV;

    public LiveBetterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mLinearLayoutManager = new LinearLayoutManager(getContext());


        return inflater.inflate(getFragmentLayout(), container, false);
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_live_better;
    }

}
