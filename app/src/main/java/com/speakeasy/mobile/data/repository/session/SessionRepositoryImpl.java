package com.speakeasy.mobile.data.repository.session;

import android.support.annotation.NonNull;

import com.speakeasy.mobile.data.model.Channel;
import com.speakeasy.mobile.data.model.User;

import rx.Observable;

/**
 * Created by Alexandr Golovach on 18.02.17.
 */

public class SessionRepositoryImpl implements SessionRepository {

	private User currentUser;

	private Channel currentChannel;

	@Override
	public Observable<User> getCurrentUser() {
		return Observable.just(currentUser);
	}

	@Override
	public Observable<Void> setCurrentUser(@NonNull User user) {
		this.currentUser = user;
		return Observable.just(null);
	}

	@Override
	public Observable<Channel> getCurrentChannel() {
		return Observable.just(currentChannel);
	}

	@Override
	public Observable<Void> setCurrentChannel(@NonNull Channel channel) {
		this.currentChannel = channel;
		return Observable.just(null);
	}
}
