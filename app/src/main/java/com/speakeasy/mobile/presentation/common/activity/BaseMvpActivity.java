package com.speakeasy.mobile.presentation.common.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.speakeasy.mobile.presentation.common.presenter.MvpPresenter;
import com.speakeasy.mobile.presentation.common.view.MvpView;

public abstract class BaseMvpActivity<V extends MvpView, P extends MvpPresenter<V>> extends AppCompatActivity {

	protected P presenter;

	protected P getMvpPresenter() {
		return presenter;
	}

	protected abstract P createMvpPresenter();

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		presenter = createMvpPresenter();

		// restore state if needed
		if (savedInstanceState != null && presenter != null) {
			presenter.restoreState(savedInstanceState);
		}

	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		if (presenter != null) {
			presenter.saveState(outState);
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		presenter.detachView();
	}
}


