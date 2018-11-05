/*
 * Created by James Henderson on 2018
 * Copyright (c) Hendercine Productions and James Henderson 2018.
 * All rights reserved.
 *
 * Last modified 11/3/18 9:00 PM
 */

package com.hendercine.sala_v1_1.ui.livebetter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hendercine.sala_v1_1.R;
import com.hendercine.sala_v1_1.models.Event;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
/**
 * SundayAssemblyLosAngeles-1.1 created by James Henderson on 11/3/18.
 */
public class LiveBetterRVAdapter extends RecyclerView
                                                 .Adapter<LiveBetterRVAdapter.LiveBetterVH>{

    private ArrayList<Event> mEvents;

    public LiveBetterRVAdapter(ArrayList<Event> events) {
        mEvents = events;
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
        Context context = holder.mLiveBetterCV.getContext();
        Event event = mEvents.get(position);
        holder.mLiveBetterDateTV.setText("event Date Placeholder text");
        holder.mLiveBetterVenueTV.setText("event Venue Placeholder text");
        Glide.with(context)
                .load(R.drawable.sala_logo_grass)
                .into(holder.mLiveBetterIV);

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class LiveBetterVH extends RecyclerView.ViewHolder {

        @BindView(R.id.live_better_card_view)
        CardView mLiveBetterCV;
        @BindView(R.id.live_better_item_image)
        ImageView mLiveBetterIV;
        @BindView(R.id.live_better_date_title)
        TextView mLiveBetterDateTV;
        @BindView(R.id.live_better_venue)
        TextView mLiveBetterVenueTV;

        LiveBetterVH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
