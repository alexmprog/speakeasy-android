package com.speakeasy.mobile.presentation.ui.chat;

import android.support.annotation.NonNull;

import com.speakeasy.mobile.data.model.Message;
import com.speakeasy.mobile.presentation.common.view.MvpView;

import java.util.List;

/**
 * Created by Alexandr Golovach on 18.02.17.
 */

public interface ChatView extends MvpView {

	void showChannelName(@NonNull String channelName);

	void showMessages(@NonNull List<Message> messageList);

	void showSendProgress();

	void hideSendProgress();

	void showError(@NonNull String message);

	void showNewMessage(@NonNull Message message);
}
