/*
 * Created by James Henderson on 2018
 * Copyright (c) Hendercine Productions and James Henderson 2018.
 * All rights reserved.
 *
 * Last modified 9/21/18 3:55 PM
 */

package com.hendercine.sala_v1_1.models;

import com.google.firebase.database.IgnoreExtraProperties;
/**
 * SundayAssemblyLosAngeles-1.1 created by artemis on 9/21/18.
 */

@IgnoreExtraProperties
public class User {

    public String username;
    public String email;
    public String userId;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String username, String email, String userId) {
        this.username = username;
        this.email = email;
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
