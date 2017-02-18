package com.speakeasy.mobile.presentation.common.di;

import android.app.Application;
import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Alexandr Golovach on 18.02.17.
 */

@Module
public class ApplicationModule {

	protected final Application application;

	public ApplicationModule(Application application) {
		this.application = application;
	}

	@Provides
	public Application providesApplication() {
		return application;
	}

	@Provides
	public Context providesContext() {
		return application;
	}
}
