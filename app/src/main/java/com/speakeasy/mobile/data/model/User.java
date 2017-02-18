package com.speakeasy.mobile.data.model;

import android.support.annotation.NonNull;

/**
 * Created by Alexandr Golovach on 18.02.17.
 */

public class User {

	private String userId;

	private String name;

	public User(@NonNull String userId, @NonNull String name) {
		this.userId = userId;
		this.name = name;
	}

	public String getUserId() {
		return userId;
	}

	public String getName() {
		return name;
	}
}
