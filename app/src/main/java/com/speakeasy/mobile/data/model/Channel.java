package com.speakeasy.mobile.data.model;

import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by Alexandr Golovach on 18.02.17.
 */

public class Channel {

	private String channelId;

	private List<User> userList;

	public Channel(@NonNull String channelId, @NonNull List<User> userList) {
		this.channelId = channelId;
		this.userList = userList;
	}

	public String getChannelId() {
		return channelId;
	}

	public List<User> getUserList() {
		return userList;
	}
}
