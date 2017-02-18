package com.speakeasy.mobile.data.di;

import com.speakeasy.mobile.data.repository.connection.ConnectionRepository;
import com.speakeasy.mobile.data.repository.connection.ConnectionRepositoryImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Alexandr Golovach on 18.02.17.
 */

@Module
public class RepositoryModule {

	@Provides
	@Singleton
	public ConnectionRepository providesConnectionRepository() {
		return new ConnectionRepositoryImpl();
	}
}
