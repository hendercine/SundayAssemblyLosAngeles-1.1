/*
 * Created by James Henderson on 2018
 * Copyright (c) Hendercine Productions and James Henderson 2018.
 * All rights reserved.
 *
 * Last modified 10/24/18 4:58 PM
 */

package com.hendercine.sala_v1_1.ui.speaker;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hendercine.sala_v1_1.R;
import com.hendercine.sala_v1_1.models.Performer;
import com.hendercine.sala_v1_1.ui.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * SundayAssemblyLosAngeles-1.1 created by hendercine on 10/24/18.
 */
public class SpeakerBioFragment extends BaseFragment {

    private Performer mSpeakerData;
    private String mSpeakerNameText;
    private String mSpeakerBioText;
    private String mSpeakerPhotoUrl;

    @BindView(R.id.speaker_name_title)
    TextView mSpeakerNameTV;
    @BindView(R.id.speaker_bio)
    TextView mSpeakerBioTV;
    @BindView(R.id.speaker_photo)
    ImageView mSpeakerPhotoIV;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(getFragmentLayout(), container, false);
        mUnbinder = ButterKnife.bind(this, rootView);

        if (mSpeakerData != null) {
            mSpeakerNameText = mSpeakerData.getPerformerName();
            mSpeakerBioText = mSpeakerData.getPerformerBio();
            mSpeakerPhotoUrl = mSpeakerData.getPerformerPicUrl();
        } else {
            mSpeakerNameText = String.valueOf(R.string.speaker_name_placeholder);
            mSpeakerBioText = String.valueOf(R.string.mission_statement);
            mSpeakerPhotoUrl = String.valueOf(R.drawable.baseline_account_circle_48);
        }

        mSpeakerNameTV.setText(mSpeakerNameText);
        mSpeakerBioTV.setText(mSpeakerBioText);
        Glide.with(this)
                .load(mSpeakerPhotoUrl)
                .into(mSpeakerPhotoIV);

        return rootView;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_speaker_bio;
    }
}
