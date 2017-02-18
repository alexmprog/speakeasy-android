package com.speakeasy.mobile.domain.usecases;

import android.support.annotation.NonNull;

import com.speakeasy.mobile.data.model.Channel;
import com.speakeasy.mobile.data.model.Message;
import com.speakeasy.mobile.data.repository.connection.ConnectionRepository;

import java.util.List;

import rx.Observable;

/**
 * Created by Alexandr Golovach on 18.02.17.
 */

public class MessagesUseCase {

	@NonNull
	private ConnectionRepository connectionRepository;

	public MessagesUseCase(@NonNull ConnectionRepository connectionRepository) {
		this.connectionRepository = connectionRepository;
	}

	public Observable<List<Message>> getMessages(@NonNull Channel channel) {
		return connectionRepository.getMessages(channel);
	}

	public Observable<Void> sendMessage(@NonNull Message message) {
		return connectionRepository.sendMessage(message);
	}

	public Observable<Message> receiveMessage() {
		return connectionRepository.receiveMessage();
	}
}
