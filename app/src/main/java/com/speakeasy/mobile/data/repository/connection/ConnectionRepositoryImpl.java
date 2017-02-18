package com.speakeasy.mobile.data.repository.connection;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.speakeasy.mobile.data.model.Channel;
import com.speakeasy.mobile.data.model.Message;
import com.speakeasy.mobile.data.model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by Alexandr Golovach on 18.02.17.
 */

public class ConnectionRepositoryImpl implements ConnectionRepository {

	private PublishSubject<Message> receiveMessagePublishSubject = PublishSubject.create();

	@Override
	public Observable<Channel> authenticate(@NonNull User user) {
		return Observable.fromCallable(() -> {
			// emulate long running operation
			Thread.sleep(1000);

			// create fake echo user
			User echoUser = new User("fake_user_id", "Echo Message Service");
			List<User> participants = new ArrayList<>();
			participants.add(user);
			participants.add(echoUser);

			return new Channel("channel_1", participants);
		});
	}

	@Override
	public Observable<List<Message>> getMessages(@NonNull Channel channel) {
		return Observable.fromCallable(() -> {
			// emulate long running operation
			Thread.sleep(1000);

			List<User> userList = channel.getUserList();
			User currentUser = userList.get(0);
			User echoUser = userList.get(1);

			List<Message> messageList = new ArrayList<>();
			for (int i = 0; i < 10; i++) {
				messageList.add(createMessage(channel, currentUser, "Text message " + i, Message.Type.TEXT, new Date()));
				messageList.add(createMessage(channel, echoUser, "Text message " + i, Message.Type.TEXT, new Date()));
			}

			return messageList;
		});
	}

	@Override
	public Observable<Void> sendMessage(final @NonNull Message message) {
		return Observable.fromCallable(new Callable<Void>() {
			@Override
			public Void call() throws Exception {
				// emulate long running operation
				Thread.sleep(1000);
				return null;
			}
		}).doOnCompleted(() -> {
			// send echo message from other user
			if (receiveMessagePublishSubject != null) {
				Channel channel = message.getChannel();
				User owner = message.getOwner();
				List<User> userList = channel.getUserList();
				for (User user : userList) {
					if (!TextUtils.equals(user.getUserId(), owner.getUserId())) {
						owner = user;
						break;
					}
				}
				Date date = new Date();
				receiveMessagePublishSubject.onNext(createMessage(channel, owner, message.getContent(),
						message.getType(), date));
			}
		});
	}

	@Override
	public Observable<Message> receiveMessage() {
		return receiveMessagePublishSubject;
	}

	private Message createMessage(@NonNull Channel channel, @NonNull User user, @NonNull String content, @NonNull Message.Type type, @NonNull Date date) {
		return new Message(channel, user, content, type, date);
	}
}
