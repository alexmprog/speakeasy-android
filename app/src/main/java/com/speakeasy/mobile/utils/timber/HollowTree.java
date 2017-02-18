package com.speakeasy.mobile.utils.timber;

import android.util.Log;

import timber.log.Timber;

public class HollowTree extends Timber.Tree {

	@Override
	protected void log(int priority, String tag, String message, Throwable t) {
		if (priority == Log.VERBOSE) {
			return;
		}

		if (t != null) {
			switch (priority) {
				case Log.ERROR:
					Log.println(priority, tag, message);
					break;
				case Log.WARN:
					Log.println(priority, tag, message);
					break;
				case Log.INFO:
					Log.println(priority, tag, message);
					break;
				case Log.ASSERT:
					Log.println(priority, tag, message);
					break;
				default:
					Log.e("Timber", "Unknown logging priority");
			}
		}
	}
}
