package com.speakeasy.mobile.presentation.ui.chat;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.speakeasy.mobile.data.model.Message;
import com.speakeasy.mobile.data.model.User;
import com.speakeasy.mobile.presentation.BaseApp;
import com.speakeasy.mobile.presentation.common.fragment.BaseMvpFragment;
import com.speakeasy.mobile.presentation.ui.chat.adapter.ChatListAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import speakeasy.benallfree.com.speakeasy.R;

/**
 * Created by Alexandr Golovach on 18.02.17.
 */

public class ChatFragment extends BaseMvpFragment<ChatView, ChatPresenter> implements ChatView {

	@Bind(R.id.toolbar)
	Toolbar toolbar;

	@Bind(R.id.fragment_messages_chat_input_field)
	LinearLayout submitContainer;

	@Bind(R.id.recyclerView)
	RecyclerView recyclerView;

	@Bind(R.id.progress_loading)
	ProgressBar progressLoading;

	@Bind(R.id.fragment_messages_conversation_send_message_btn)
	Button sendMessageButton;

	@Bind(R.id.fragment_messages_conversation_new_message_input)
	EditText editText;

	ChatListAdapter chatListAdapter;

	@OnClick(R.id.fragment_messages_conversation_send_message_btn)
	public void onSendClicked(View view) {
		String content = editText.getText().toString();
		if (!TextUtils.isEmpty(content)) {
			getMvpPresenter().sendNewMessage(content);
		}
	}

	@NonNull
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_chat, container, false);

		ButterKnife.bind(this, view);

		initToolbar();
		initRecyclerView();

		submitContainer.setVisibility(View.GONE);
		recyclerView.setVisibility(View.GONE);

		return view;
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		getMvpPresenter().attachView(this);
	}

	private void initToolbar() {
		toolbar.setTitle(R.string.conecting);
	}

	private void initRecyclerView() {
		LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, true);
		layoutManager.setReverseLayout(true);
		recyclerView.setLayoutManager(layoutManager);
		recyclerView.setHasFixedSize(true);
	}

	@Override
	protected ChatPresenter createMvpPresenter() {
		return new ChatPresenter(BaseApp.get());
	}

	@Override
	public void showChannelName(@NonNull String channelName) {
		toolbar.setTitle(channelName);
	}

	@Override
	public void showMessages(@NonNull User user, @NonNull List<Message> messageList) {
		chatListAdapter = new ChatListAdapter(user);
		recyclerView.setAdapter(chatListAdapter);
		chatListAdapter.setMessageList(messageList);
		submitContainer.setVisibility(View.VISIBLE);
		recyclerView.setVisibility(View.VISIBLE);
		recyclerView.smoothScrollToPosition(0);
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
		chatListAdapter.addNewMessage(message);
		recyclerView.smoothScrollToPosition(0);
	}
}
