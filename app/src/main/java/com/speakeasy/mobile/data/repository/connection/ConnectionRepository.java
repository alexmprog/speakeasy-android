package com.speakeasy.mobile.data.repository.connection;

import android.support.annotation.NonNull;

import com.speakeasy.mobile.data.model.Channel;
import com.speakeasy.mobile.data.model.Message;
import com.speakeasy.mobile.data.model.User;

import java.util.List;

import rx.Observable;

/**
 * Created by Alexandr Golovach on 18.02.17.
 */

public interface ConnectionRepository {

	Observable<Channel> authenticate(@NonNull User user);

	Observable<List<Message>> getMessages(@NonNull Channel channel); // need add pagination in future

	Observable<Void> sendMessage(@NonNull Message message);

	Observable<Message> receiveMessage();
}
