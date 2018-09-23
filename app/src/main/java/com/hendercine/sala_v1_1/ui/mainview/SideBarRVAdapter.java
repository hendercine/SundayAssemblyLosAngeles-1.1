/*
 * Created by James Henderson on 2018
 * Copyright (c) Hendercine Productions and James Henderson 2018.
 * All rights reserved.
 *
 * Last modified 9/23/18 1:47 PM
 */

package com.hendercine.sala_v1_1.ui.mainview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hendercine.sala_v1_1.R;

import butterknife.BindView;
import butterknife.ButterKnife;
/**
 * SundayAssemblyLosAngeles-1.1 created by hendercine on 9/23/18.
 */
public class SideBarRVAdapter extends RecyclerView.Adapter<SideBarRVAdapter.SideBarViewHolder> {

    private String[] mSideBarArray;
    private OnItemClickListener mClickListener;
    private int focusedItem = RecyclerView.NO_POSITION;

    public SideBarRVAdapter(String[] sideBarArray) {
        this.mSideBarArray = sideBarArray;
    }

    @NonNull
    @Override
    public SideBarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout
                .list_item_side_bar, parent, false);
        return new SideBarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SideBarViewHolder holder, int position) {
        holder.itemView.setSelected(focusedItem == position);
        holder.mSideBarListTextView.setText(mSideBarArray[position]);
        holder.mSideBarListTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.onItemClick(holder.getAdapterPosition());
            }
        });

    }

    @Override
    public int getItemCount() {
     return mSideBarArray.length;
    }

    public long getItemId(int position) {
        return position;
    }

    public class SideBarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.side_bar_item_text_view)
        TextView mSideBarListTextView;

        SideBarViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            notifyItemChanged(focusedItem);
            focusedItem = getLayoutPosition();
            notifyItemChanged(focusedItem);
        }
    }

    public void setClickListener(OnItemClickListener clickListener) {
        this.mClickListener = clickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);

    }
}
