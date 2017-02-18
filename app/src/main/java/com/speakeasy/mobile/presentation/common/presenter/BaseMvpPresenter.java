package com.speakeasy.mobile.presentation.common.presenter;

import android.os.Bundle;

import com.speakeasy.mobile.presentation.common.view.BaseMvpView;

/**
 * Created by Alexandr Golovach on 18.02.17.
 */

public class BaseMvpPresenter implements MvpPresenter<BaseMvpView> {
	@Override
	public void attachView(BaseMvpView mvpView) {
		// do nothing here
	}

	@Override
	public void detachView() {
		// do nothing here
	}

	@Override
	public void saveState(Bundle outBundle) {
		// do nothing here
	}

	@Override
	public void restoreState(Bundle savedInstanceState) {
		// do nothing here
	}
}
