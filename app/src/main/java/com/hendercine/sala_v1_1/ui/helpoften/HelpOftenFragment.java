/*
 * Created by James Henderson on 2018
 * Copyright (c) Hendercine Productions and James Henderson 2018.
 * All rights reserved.
 *
 * Last modified 11/5/18 11:55 AM
 */

package com.hendercine.sala_v1_1.ui.helpoften;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hendercine.sala_v1_1.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HelpOftenFragment extends Fragment {


    public HelpOftenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_help_often, container, false);
    }

}
