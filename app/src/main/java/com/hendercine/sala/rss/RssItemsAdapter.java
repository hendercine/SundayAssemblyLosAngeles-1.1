/*
 * Created by James Henderson on 2018
 * Copyright (c) Hendercine Productions and James Henderson 2018.
 * All rights reserved.
 *
 * Last modified 7/26/18 2:26 PM
 */

package com.hendercine.sala.rss;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hendercine.sala.R;
import com.hendercine.sala.model.Assembly;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * SundayAssemblyLosAngeles-1.1 created by hendercine on 7/26/18.
 */
public class RssItemsAdapter extends RecyclerView.Adapter<RssItemsAdapter
        .ViewHolder> {

    private final Context mContext;
    private final List<Assembly> mItems = new ArrayList<>();
    private OnItemClickListener mListener;

    public RssItemsAdapter(Context context) {
        mContext = context;
    }

    public void setListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    public void setItems(List<Assembly> items) {
        mItems.clear();
        mItems.addAll(items);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    interface OnItemClickListener {

        void onItemSelected(Assembly assembly);

    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvTitle)
        TextView mTextTitle;
        @BindView(R.id.tvPubDate)
        TextView mTextPubDate;
        @BindView(R.id.ivThumb)
        ImageView mImageThumb;
        @BindView(R.id.llTextContainer)
        RelativeLayout llTextContainer;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
