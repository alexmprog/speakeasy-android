package com.speakeasy.mobile.presentation;

import android.app.Application;
import android.support.annotation.NonNull;

import com.speakeasy.mobile.data.di.RepositoryModule;
import com.speakeasy.mobile.domain.di.UseCaseModule;
import com.speakeasy.mobile.presentation.common.di.ApplicationComponent;
import com.speakeasy.mobile.presentation.common.di.ApplicationModule;
import com.speakeasy.mobile.presentation.common.di.DaggerApplicationComponent;


/**
 * Created by Alexandr Golovach on 18.02.17.
 */

public class BaseApp extends Application implements Injector {

	private static BaseApp sInstance;

	public static BaseApp get() {
		return sInstance;
	}

	private ApplicationComponent applicationComponent;

	@Override
	public void onCreate() {
		super.onCreate();

		sInstance = this;

		// init object graph
		initializeObjectGraph();
	}

	protected void initializeObjectGraph() {
		applicationComponent = DaggerApplicationComponent.builder()
				.applicationModule(new ApplicationModule(this))
				.repositoryModule(new RepositoryModule())
				.useCaseModule(new UseCaseModule())
				.build();
	}

	@NonNull
	@Override
	public ApplicationComponent getApplicationComponent() {
		return applicationComponent;
	}
}

