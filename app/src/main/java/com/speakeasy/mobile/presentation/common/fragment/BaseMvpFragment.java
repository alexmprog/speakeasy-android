package com.speakeasy.mobile.presentation.common.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.speakeasy.mobile.presentation.common.presenter.MvpPresenter;
import com.speakeasy.mobile.presentation.common.view.MvpView;

/**
 * Created by Alexandr Golovach on 24.08.16.
 */

public abstract class BaseMvpFragment<V extends MvpView, P extends MvpPresenter<V>> extends Fragment {

	protected P presenter;

	protected P getMvpPresenter() {
		return presenter;
	}

	protected abstract P createMvpPresenter();

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		presenter = createMvpPresenter();

		// restore state if needed
		if (savedInstanceState != null && presenter != null) {
			presenter.restoreState(savedInstanceState);
		}
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		if (presenter != null) {
			presenter.saveState(outState);
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (presenter != null) {
			presenter.detachView();
		}
	}
}
