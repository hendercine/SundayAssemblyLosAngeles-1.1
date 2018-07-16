package com.hendercine.sala.chrome;

import android.content.ComponentName;
import android.support.customtabs.CustomTabsClient;
import android.support.customtabs.CustomTabsServiceConnection;

import java.lang.ref.WeakReference;

/**
 * SundayAssemblyLosAngeles-1.1 created by artemis on 7/13/18.
 */
public class ServiceConnection extends CustomTabsServiceConnection {

    private WeakReference<ServiceConnectionCallback> mConnectionCallback;

    public ServiceConnection(ServiceConnectionCallback connectionCallback) {
        mConnectionCallback = new WeakReference<>(connectionCallback);
    }

    @Override
    public void onCustomTabsServiceConnected(ComponentName name, CustomTabsClient client) {
        ServiceConnectionCallback connectionCallback = mConnectionCallback.get();
        if (connectionCallback != null) {
            connectionCallback.onServiceConnected(client);
        }
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        ServiceConnectionCallback connectionCallback = mConnectionCallback.get();
        if (connectionCallback != null) {
            connectionCallback.onServiceDisconnected();
        }
    }
}
