/*
 * Created by James Henderson on 2018
 * Copyright (c) Hendercine Productions and James Henderson 2018.
 * All rights reserved.
 *
 * Last modified 9/23/18 12:26 PM
 */

package com.hendercine.sala_v1_1.data;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

/**
 * SundayAssemblyLosAngeles-1.1 created by artemis on 9/23/18.
 */
public class SiteServiceReceiver extends ResultReceiver {

    private Listener mListener;

    /**
     * Create a new ResultReceive to receive results.  Your
     * {@link #onReceiveResult} method will be called from the thread running
     * <var>handler</var> if given, or from an arbitrary thread if null.
     *
     * @param handler is received from the service.
     */
    public SiteServiceReceiver(Handler handler) {
        super(handler);
    }

    public void setListener(Listener listener) {
        this.mListener = listener;
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        if (mListener != null) {
            mListener.onReceiveResult(resultCode, resultData);
        }
    }

    public interface Listener {

        void onReceiveResult(int resultCode, Bundle resultData);
    }

}
