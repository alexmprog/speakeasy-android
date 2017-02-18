package com.speakeasy.mobile.presentation.common.di;

import com.speakeasy.mobile.data.di.RepositoryModule;
import com.speakeasy.mobile.domain.di.UseCaseModule;
import com.speakeasy.mobile.presentation.ui.chat.ChatPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Alexandr Golovach on 18.02.17.
 */

@Singleton
@Component(modules = {ApplicationModule.class, RepositoryModule.class, UseCaseModule.class})
public interface ApplicationComponent {

	// presenters
	void inject(ChatPresenter chatPresenter);

}

