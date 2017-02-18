package com.speakeasy.mobile.presentation.ui.chat;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.speakeasy.mobile.data.model.Message;
import com.speakeasy.mobile.presentation.common.fragment.BaseMvpFragment;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import speakeasy.benallfree.com.speakeasy.R;

/**
 * Created by Alexandr Golovach on 18.02.17.
 */

public class ChatFragment extends BaseMvpFragment<ChatView, ChatPresenter> implements ChatView {

	@Bind(R.id.toolbar)
	Toolbar toolbar;

	@Bind(R.id.fragment_messages_chat_input_field)
	LinearLayout submitContainer;

	@Bind(R.id.swipe_refresh_layout)
	SwipeRefreshLayout swipeRefreshLayout;

	@Bind(R.id.recyclerView)
	RecyclerView recyclerView;

	@Bind(R.id.progress_loading)
	ProgressBar progressLoading;

	@Bind(R.id.fragment_messages_conversation_send_message_btn)
	Button sendMessageButton;

	@NonNull
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_chat, container, false);

		ButterKnife.bind(this, view);
		
		initToolbar(view);
		initRecyclerView(view);
		initInputTextAndSubmitButton(view);
		initSwipeRefreshLayout(view);
		initStickersController(view);

		return view;
	}

	@Override
	protected ChatPresenter createMvpPresenter() {
		return new ChatPresenter();
	}

	@Override
	public void showChannelName(@NonNull String channelName) {

	}

	@Override
	public void showMessages(@NonNull List<Message> messageList) {

	}

	@Override
	public void showSendProgress() {
		sendMessageButton.setVisibility(View.GONE);
		progressLoading.setVisibility(View.VISIBLE);
	}

	@Override
	public void hideSendProgress() {
		sendMessageButton.setVisibility(View.VISIBLE);
		progressLoading.setVisibility(View.GONE);
	}

	@Override
	public void showError(@NonNull String message) {
		Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void showNewMessage(@NonNull Message message) {

	}
}
