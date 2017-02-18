package com.speakeasy.mobile.domain.usecases;

import android.support.annotation.NonNull;

import com.speakeasy.mobile.data.model.Channel;
import com.speakeasy.mobile.data.model.User;
import com.speakeasy.mobile.data.repository.connection.ConnectionRepository;
import com.speakeasy.mobile.data.repository.session.SessionRepository;

import rx.Observable;

/**
 * Created by Alexandr Golovach on 18.02.17.
 */

public class AuthenticateUseCase {

	@NonNull
	private ConnectionRepository connectionRepository;

	@NonNull
	private SessionRepository sessionRepository;

	public AuthenticateUseCase(@NonNull ConnectionRepository connectionRepository, @NonNull SessionRepository sessionRepository) {
		this.connectionRepository = connectionRepository;
		this.sessionRepository = sessionRepository;
	}

	public Observable<Channel> authenticate(@NonNull User user) {
		return connectionRepository.authenticate(user).filter(channel -> {
			sessionRepository.setCurrentUser(user).toBlocking().first();
			sessionRepository.setCurrentChannel(channel).toBlocking().first();
			return true;
		});
	}

	public Observable<User> getCurrentUser() {
		return sessionRepository.getCurrentUser();
	}

	public Observable<Channel> getCurrentChannel() {
		return sessionRepository.getCurrentChannel();
	}

}
