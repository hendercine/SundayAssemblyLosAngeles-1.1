package com.hendercine.sala.chrome;

import android.support.customtabs.CustomTabsClient;
/**
 * SundayAssemblyLosAngeles-1.1 created by artemis on 7/13/18.
 */
interface ServiceConnectionCallback {

    /**
     * Called when the service is connected.
     * @param client a CustomTabsClient
     */
    void onServiceConnected(CustomTabsClient client);

    /**
     * Called when the service is disconnected.
     */
    void onServiceDisconnected();
}
