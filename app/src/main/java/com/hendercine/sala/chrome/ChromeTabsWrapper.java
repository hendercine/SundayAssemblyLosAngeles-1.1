package com.hendercine.sala.chrome;

import android.content.Context;
import android.net.Uri;
import android.support.customtabs.CustomTabsClient;
import android.support.customtabs.CustomTabsIntent;
import android.support.customtabs.CustomTabsServiceConnection;
import android.support.customtabs.CustomTabsSession;
import android.support.v4.content.ContextCompat;

import com.hendercine.sala.R;
/**
 * SundayAssemblyLosAngeles-1.1 created by artemis on 7/13/18. MVP
 * structure code sourced from https://medium.com/android-bits
 * /android-app-from-scratch-part-1-model-view-presenter-b5f629f2d9a1
 */
public class ChromeTabsWrapper implements ServiceConnectionCallback {

    private static final String CUSTOM_TAB_PACKAGE_NAME = "com.android.chrome";

    private Context mContext;
    private CustomTabsSession mCustomTabsSession;
    private CustomTabsServiceConnection mConnection;
    private CustomTabsClient mClient;

    public ChromeTabsWrapper(Context context) {
        mContext = context;
    }

    public void openCustomTab(String url) {
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        builder.setToolbarColor(ContextCompat.getColor(mContext, R.color.colorPrimary));
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(mContext, Uri.parse(url));
    }

    public void bindCustomTabsService() {
        if (mClient != null) {
            return;
        }
        if (mConnection == null) {
            mConnection = new ServiceConnection(this);
        }
        boolean ok = CustomTabsClient.bindCustomTabsService(mContext,
                CUSTOM_TAB_PACKAGE_NAME, mConnection
        );
    }

    public void unbindCustomTabsService() {
        if (mConnection == null) {
            return;
        }
        mContext.unbindService(mConnection);
        mClient = null;
        mCustomTabsSession = null;
        mConnection = null;
    }

    @Override
    public void onServiceConnected(CustomTabsClient client) {
        mClient = client;
    }

    @Override
    public void onServiceDisconnected() {
        mClient = null;
    }
}
