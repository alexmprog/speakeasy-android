package com.speakeasy.mobile.data.repository.session;

import android.support.annotation.NonNull;

import com.speakeasy.mobile.data.model.Channel;
import com.speakeasy.mobile.data.model.User;

import rx.Observable;

/**
 * Created by Alexandr Golovach on 18.02.17.
 */

public interface SessionRepository {

	Observable<User> getCurrentUser();

	Observable<Void> setCurrentUser(@NonNull User user);

	Observable<Channel> getCurrentChannel();

	Observable<Void> setCurrentChannel(@NonNull Channel channel);
}
