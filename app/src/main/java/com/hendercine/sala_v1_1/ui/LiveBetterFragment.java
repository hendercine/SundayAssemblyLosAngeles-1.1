/*
 * Created by James Henderson on 2018
 * Copyright (c) Hendercine Productions and James Henderson 2018.
 * All rights reserved.
 *
 * Last modified 10/30/18 11:26 AM
 */

package com.hendercine.sala_v1_1.ui;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hendercine.sala_v1_1.R;
import com.hendercine.sala_v1_1.ui.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class LiveBetterFragment extends BaseFragment {


    public LiveBetterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_live_better, container, false);
    }

}
