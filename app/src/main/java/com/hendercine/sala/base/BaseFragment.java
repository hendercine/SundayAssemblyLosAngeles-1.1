/*
 * Created by James Henderson on 2018
 * Copyright (c) Hendercine Productions and James Henderson 2018.
 * All rights reserved.
 *
 * Last modified 7/16/18 5:03 PM
 */

package com.hendercine.sala.base;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.hendercine.sala.di.component.DaggerFragmentComponent;
import com.hendercine.sala.di.component.FragmentComponent;
import com.hendercine.sala.di.module.FragmentModule;
import com.hendercine.sala.model.RError;

import java.util.Objects;

import javax.inject.Inject;

/**
 * SundayAssemblyLosAngeles-1.1 created by hendercine on 7/16/18.
 */
public abstract class BaseFragment<T extends BaseMvpPresenter> extends Fragment implements BaseView {

    @Inject
    T mPresenter;

    FragmentComponent mFragmentComponent;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragmentComponent = DaggerFragmentComponent.builder()
                .fragmentModule(new FragmentModule())
                .build();
        injectDependencies();
    }

    @Override
    public void onFail(RError error) {
        if (isAdded()) {
            showToast(error.getMessage());
        }
    }

    public FragmentComponent getFragmentComponent() {
        return mFragmentComponent;
    }

    public T getPresenter() {
        return mPresenter;
    }

    @LayoutRes
    protected abstract int getContentResource();

    protected abstract void init(@Nullable Bundle state);

    protected abstract void injectDependencies();

    public void showSnackBar(int resId) {
        Snackbar.make(
                Objects.requireNonNull(getActivity()).findViewById(android.R.id.content),
                resId,
                Snackbar.LENGTH_SHORT
        ).show();
    }

    public void showToast(String msg) {
        Toast.makeText(Objects.requireNonNull(getActivity()).getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public void showToast(int resId) {
        Toast.makeText(Objects.requireNonNull(getActivity()).getApplicationContext(), resId, Toast.LENGTH_SHORT).show();
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = null;
        if (connectivityManager != null) {
            activeNetwork = connectivityManager.getActiveNetworkInfo();
        }
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

}
