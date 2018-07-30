/*
 * Created by James Henderson on 2018
 * Copyright (c) Hendercine Productions and James Henderson 2018.
 * All rights reserved.
 *
 * Last modified 7/23/18 5:39 PM
 */

package com.hendercine.sala.parser;

import com.hendercine.sala.model.Assembly;

import java.util.List;
/**
 * SundayAssemblyLosAngeles-1.1 created by hendercine on 7/23/18.
 */
public interface OnAssembliesParserListener {

    void onSuccess(List<Assembly> assemblyList, String assemblyUrl);

    void onFail(String assemblyUrl);

}
