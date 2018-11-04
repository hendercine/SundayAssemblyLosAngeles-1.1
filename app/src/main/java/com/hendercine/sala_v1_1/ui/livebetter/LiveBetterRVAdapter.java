/*
 * Created by James Henderson on 2018
 * Copyright (c) Hendercine Productions and James Henderson 2018.
 * All rights reserved.
 *
 * Last modified 11/3/18 9:00 PM
 */

package com.hendercine.sala_v1_1.ui.livebetter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hendercine.sala_v1_1.R;
import com.hendercine.sala_v1_1.models.BetterEvent;

import java.util.ArrayList;

import butterknife.ButterKnife;
/**
 * SundayAssemblyLosAngeles-1.1 created by James Henderson on 11/3/18.
 */
public class LiveBetterRVAdapter extends RecyclerView
                                                 .Adapter<LiveBetterRVAdapter.LiveBetterVH>{

    private ArrayList<BetterEvent> mBetterEvents;

    public LiveBetterRVAdapter(ArrayList<BetterEvent> betterEvents) {
        mBetterEvents = betterEvents;
    }

    @NonNull
    @Override
    public LiveBetterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.fragment_live_better, parent, false);
        return new LiveBetterVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LiveBetterVH holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class LiveBetterVH extends RecyclerView.ViewHolder {

        public LiveBetterVH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
