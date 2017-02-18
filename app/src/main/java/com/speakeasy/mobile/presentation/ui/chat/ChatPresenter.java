package com.speakeasy.mobile.presentation.ui.chat;

import android.support.annotation.NonNull;

import com.speakeasy.mobile.data.model.Channel;
import com.speakeasy.mobile.data.model.Message;
import com.speakeasy.mobile.data.model.User;
import com.speakeasy.mobile.domain.usecases.AuthenticateUseCase;
import com.speakeasy.mobile.domain.usecases.MessagesUseCase;
import com.speakeasy.mobile.domain.usecases.UseCaseExecutor;
import com.speakeasy.mobile.presentation.Injector;
import com.speakeasy.mobile.presentation.common.presenter.BasePresenter;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import rx.functions.Action1;

/**
 * Created by Alexandr Golovach on 18.02.17.
 */

public class ChatPresenter extends BasePresenter<ChatView> {

	@Inject
	AuthenticateUseCase authenticateUseCase;

	@Inject
	MessagesUseCase messagesUseCase;

	@Inject
	UseCaseExecutor useCaseExecutor;

	private Channel currentChannel;

	public ChatPresenter(@NonNull Injector injector) {
		injector.getApplicationComponent().inject(this);
	}

	@Override
	public void attachView(ChatView mvpView) {
		super.attachView(mvpView);
		authenticateUseCase.isAuthenticated()
				.compose(useCaseExecutor.applySchedulers())
				.subscribe(channel -> {
					currentChannel = channel;
					if (isViewAttached()) {
						getMvpView().showChannelName(channel.getChannelId());
						loadMessages();
					}
				});

		messagesUseCase.receiveMessage()
				.compose(useCaseExecutor.applySchedulers())
				.subscribe(message -> {
					if (isViewAttached()) {
						getMvpView().showNewMessage(message);
					}
				});
	}

	private void loadMessages() {
		messagesUseCase.getMessages(currentChannel)
				.compose(useCaseExecutor.applySchedulers())
				.subscribe(messages -> {
					if (isViewAttached()) {
						List<User> userList = currentChannel.getUserList();
						User currentUser = userList.get(0);
						getMvpView().showMessages(currentUser, messages);
					}
				});
	}

	public void sendNewMessage(@NonNull String content) {
		getMvpView().showSendProgress();

		List<User> userList = currentChannel.getUserList();
		User currentUser = userList.get(0);
		Message message = new Message(currentChannel, currentUser, content, Message.Type.TEXT, new Date());
		messagesUseCase.sendMessage(message)
				.compose(useCaseExecutor.applySchedulers())
				.subscribe(aVoid -> {
					if (isViewAttached()) {
						getMvpView().showNewMessage(message);
						getMvpView().hideSendProgress();
					}
				});

	}
}
