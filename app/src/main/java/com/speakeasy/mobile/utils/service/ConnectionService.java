package com.speakeasy.mobile.utils.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.speakeasy.mobile.data.model.Channel;
import com.speakeasy.mobile.data.model.User;
import com.speakeasy.mobile.domain.usecases.AuthenticateUseCase;
import com.speakeasy.mobile.domain.usecases.UseCaseExecutor;
import com.speakeasy.mobile.presentation.BaseApp;

import javax.inject.Inject;

import rx.functions.Action1;

/**
 * Created by Alexandr Golovach on 18.02.17.
 */

public class ConnectionService extends Service {

	@Inject
	AuthenticateUseCase authenticateUseCase;

	@Inject
	UseCaseExecutor useCaseExecutor;

	@Override
	public void onCreate() {
		super.onCreate();
		BaseApp.get().getApplicationComponent().inject(this);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		User user = new User("main_user", "Alex");
		authenticateUseCase
				.authenticate(user)
				.compose(useCaseExecutor.applySchedulers())
				.subscribe(channel -> {
					// do nothing here
				});
		return START_STICKY;
	}

	@Nullable
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
}
