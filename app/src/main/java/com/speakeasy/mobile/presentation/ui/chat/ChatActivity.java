package com.speakeasy.mobile.presentation.ui.chat;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.speakeasy.mobile.presentation.common.activity.BaseMvpActivity;
import com.speakeasy.mobile.presentation.common.presenter.BaseMvpPresenter;
import com.speakeasy.mobile.presentation.common.view.BaseMvpView;

import speakeasy.benallfree.com.speakeasy.R;

/**
 * Created by Alexandr Golovach on 18.02.17.
 */

// we can use custom presenter for chat activity
public class ChatActivity extends BaseMvpActivity<BaseMvpView, BaseMvpPresenter> implements BaseMvpView {

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chat);
	}

	@Override
	protected BaseMvpPresenter createMvpPresenter() {
		return new BaseMvpPresenter();
	}
}
