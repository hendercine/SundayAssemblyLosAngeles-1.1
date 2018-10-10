/*
 * Created by James Henderson on 2018
 * Copyright (c) Hendercine Productions and James Henderson 2018.
 * All rights reserved.
 *
 * Last modified 9/23/18 12:44 PM
 */

package com.hendercine.sala_v1_1.ui.assemblies;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hendercine.sala_v1_1.R;
import com.hendercine.sala_v1_1.data.SalaSiteIntentService;
import com.hendercine.sala_v1_1.data.SiteServiceReceiver;
import com.hendercine.sala_v1_1.models.Assembly;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * SundayAssemblyLosAngeles-1.1 created by artemis on 9/23/18.
 */
public class AssembliesFragment extends Fragment implements SiteServiceReceiver.Listener {

    // Base strings to run through Jsoup
    private static final String ASSEMBLIES_URL = "http://www.sundayassemblyla.org";
    private static final String CLASS_NAME = "event-wrap";
    private static final String LI_ELEMENT = "li";
    private static final String ASSEMBLIES = "assemblies";
    private static final String REC = "rec";
    private static final String POSITION_STATE_KEY = "scroll_position";

    private ArrayList<Assembly> mAssembliesList;
    private ArrayList<Assembly> mAssemblyArrayList;
    private Assembly mAssembly;
    private int mScrollPosition;

    private AssembliesRVAdapter mAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private Unbinder mUnbinder;

    @BindView(R.id.assemblies_recycler_view)
    RecyclerView mAssembliesRV;

    public AssembliesFragment() {
        // Required empty public constructor
    }

    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_assemblies, container, false);
        mUnbinder = ButterKnife.bind(this, rootView);
        mLinearLayoutManager = new LinearLayoutManager(getContext());

        if (savedInstanceState == null) {
            // Start the service call
            Objects.requireNonNull(getActivity()).startService(createAssemblyIntentCall());

            mAdapter = new AssembliesRVAdapter(mAssembliesList);

        }

        if (mAssembliesRV != null) {
            mAssembliesRV.setLayoutManager(mLinearLayoutManager);
            mAssembliesRV.setAdapter(mAdapter);
        }

        return rootView;
    }

//    @Override
//    protected int getFragmentLayout() {
//        return R.layout.fragment_assemblies;
//    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mScrollPosition = mLinearLayoutManager.findFirstCompletelyVisibleItemPosition();
        outState.putInt(POSITION_STATE_KEY, mScrollPosition);
        outState.putParcelable(ASSEMBLIES, Parcels.wrap(mAssembliesList));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public void onReceiveResult(int resultCode, Bundle resultData) {
        ArrayList<Assembly> assemblies = Parcels.unwrap(resultData.getParcelable(ASSEMBLIES));

        mAssembliesList = new ArrayList<>();
        for (int i = 0; i < assemblies.size(); i++) {
            mAssembly = new Assembly();
            mAssembly.setAssemblyDate(assemblies.get(i).getAssemblyDate());
            mAssembly.setAssemblyTheme(assemblies.get(i).getAssemblyTheme());
            mAssembly.setAssemblyDescription(assemblies.get(i).getAssemblyDescription());
            mAssembly.setAssemblyPhotoUrl(assemblies.get(i).getAssemblyPhotoUrl());

            mAssembliesList.add(mAssembly);
        }

        if (mAdapter != null) {
            mAdapter.setAssembliesList(mAssembliesList);
        }

    }

    private Intent createAssemblyIntentCall() {
        Intent intent = new Intent(getContext(), SalaSiteIntentService.class);
        SiteServiceReceiver receiver = new SiteServiceReceiver(new Handler());
        receiver.setListener(this);
        intent.putExtra(REC, receiver);
        intent.putExtra(ASSEMBLIES, Parcels.wrap(mAssembliesList));

        return intent;
    }
}
