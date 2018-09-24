/*
 * Created by James Henderson on 2018
 * Copyright (c) Hendercine Productions and James Henderson 2018.
 * All rights reserved.
 *
 * Last modified 9/23/18 2:44 PM
 */

package com.hendercine.sala_v1_1.ui.aboutsala;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hendercine.sala_v1_1.R;
import com.hendercine.sala_v1_1.ui.base.BaseFragment;
/**
 * SundayAssemblyLosAngeles-1.1 created by artemis on 9/23/18.
 */
public class AboutSalaFragment extends BaseFragment {

    public AboutSalaFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_about_sala;
    }
}
