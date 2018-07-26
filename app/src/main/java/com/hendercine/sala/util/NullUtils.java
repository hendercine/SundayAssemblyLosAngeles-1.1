/*
 * Created by James Henderson on 2018
 * Copyright (c) Hendercine Productions and James Henderson 2018.
 * All rights reserved.
 *
 * Last modified 7/26/18 4:11 PM
 */

package com.hendercine.sala.util;

import android.support.annotation.Nullable;
/**
 * SundayAssemblyLosAngeles-1.1 created by hendercine on 7/26/18.
 */
public class NullUtils {

    public static boolean notEmpty(@Nullable String string) {
        return string != null && string.length() > 0;
    }

}
