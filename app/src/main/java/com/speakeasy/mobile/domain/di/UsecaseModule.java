package com.speakeasy.mobile.domain.di;

import android.support.annotation.NonNull;

import com.speakeasy.mobile.data.repository.connection.ConnectionRepository;
import com.speakeasy.mobile.data.repository.session.SessionRepository;
import com.speakeasy.mobile.domain.usecases.AuthenticateUseCase;
import com.speakeasy.mobile.domain.usecases.MessagesUseCase;
import com.speakeasy.mobile.domain.usecases.UseCaseExecutor;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Alexandr Golovach on 18.02.17.
 */

@Module
public class UseCaseModule {

	@Provides
	@Singleton
	@Named(UseCaseExecutor.SUBSCRIBE_SCHEDULER)
	public Scheduler providesSubscribeScheduler() {
		return Schedulers.io();
	}

	@Provides
	@Singleton
	@Named(UseCaseExecutor.OBSERVE_SCHEDULER)
	public Scheduler providesObserveScheduler() {
		return AndroidSchedulers.mainThread();
	}

	@Provides
	@Singleton
	public UseCaseExecutor providesUseCaseExecutor(@NonNull @Named(UseCaseExecutor.SUBSCRIBE_SCHEDULER) Scheduler subscribeScheduler,
												   @NonNull @Named(UseCaseExecutor.OBSERVE_SCHEDULER) Scheduler observerScheduler) {
		return new UseCaseExecutor(subscribeScheduler, observerScheduler);
	}

	@Provides
	public AuthenticateUseCase providesAuthenticateUseCase(@NonNull ConnectionRepository connectionRepository, @NonNull SessionRepository sessionRepository) {
		return new AuthenticateUseCase(connectionRepository, sessionRepository);
	}

	@Provides
	public MessagesUseCase providesMessagesUseCase(@NonNull ConnectionRepository connectionRepository) {
		return new MessagesUseCase(connectionRepository);
	}
}
