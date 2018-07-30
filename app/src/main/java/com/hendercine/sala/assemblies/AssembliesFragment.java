/*
 * Created by James Henderson on 2018
 * Copyright (c) Hendercine Productions and James Henderson 2018.
 * All rights reserved.
 *
 * Last modified 7/16/18 5:41 PM
 */

package com.hendercine.sala.assemblies;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.hendercine.sala.R;
import com.hendercine.sala.base.BaseFragment;
import com.hendercine.sala.model.Feed;
import com.hendercine.sala.model.RError;
import com.hendercine.sala.model.Assembly;

import java.util.List;

import butterknife.BindView;

/**
 * SundayAssemblyLosAngeles-1.1 created by hendercine on 7/16/18.
 */
public class AssembliesFragment extends BaseFragment<AssembliesPresenter> implements
                                                            AssembliesContract.View,
                                                            SwipeRefreshLayout.OnRefreshListener, AssembliesItemsAdapter.OnItemClickListener {

    private static final String KEY_FEED = "FEED";

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.swRefresh)
    SwipeRefreshLayout mSwRefresh;
    @BindView(R.id.tvNoItems)
    TextView mTvNoItems;

    private Feed mFeed;
    private AssembliesItemsAdapter mAdapter;
    private OnItemSelectListener mListener;

    public static AssembliesFragment newInstance(Feed feed) {
        AssembliesFragment assembliesFragment = new AssembliesFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_FEED, feed);
        assembliesFragment.setArguments(bundle);
        return assembliesFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mFeed = (Feed) getArguments().getSerializable(KEY_FEED);
        }
    }

    @Override
    protected int getContentResource() {
        return R.layout.fragment_rss;
    }

    @Override
    protected void init(@Nullable Bundle state) {
        mAdapter = new AssembliesItemsAdapter(getActivity());
        mAdapter.setListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);
        mSwRefresh.setOnRefreshListener(this);
        getPresenter().loadAssemblies(mFeed, true);

    }

    @Override
    protected void injectDependencies() {
        getFragmentComponent().inject(this);

    }

    @Override
    public void showLoading() {
        mSwRefresh.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        mSwRefresh.setRefreshing(false);
    }

    @Override
    public void onAssemblyItemsLoaded(List<Assembly> assemblies) {
        mAdapter.setItems(assemblies);
        mAdapter.notifyDataSetChanged();
        if (mRecyclerView.getVisibility() != View.VISIBLE) {
            mRecyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onBrowse(Assembly assembly) {
        mListener.onItemSelected(assembly);
    }

    @Override
    public void onFail(RError rError) {
        mRecyclerView.setVisibility(View.GONE);
        mTvNoItems.setVisibility(View.VISIBLE);
    }

    @Override
    public void onRefresh() {

        getPresenter().loadAssemblies(mFeed, false);
    }

    @Override
    public void onItemSelected(Assembly assembly) {
        getPresenter().browseAssemblyUrl(assembly);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnItemSelectListener) {
            mListener = (OnItemSelectListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnItemSelectListener {
        void onItemSelected(Assembly assembly);
    }
}