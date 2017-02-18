package com.speakeasy.mobile.utils.timber;

import timber.log.Timber;

public class DebugTree extends Timber.DebugTree {

	@Override
	protected void log(int priority, String tag, String message, Throwable t) {
		super.log(priority, tag, message, t);
	}
}
