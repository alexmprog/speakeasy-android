package com.speakeasy.mobile.presentation;

import android.support.annotation.NonNull;

import com.speakeasy.mobile.presentation.common.di.ApplicationComponent;

/**
 * Created by Alexandr Golovach on 18.02.17.
 */

public interface Injector {

	@NonNull
	ApplicationComponent getApplicationComponent();
}