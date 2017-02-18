package com.speakeasy.mobile.data.model;

import android.support.annotation.NonNull;

import java.util.Date;

/**
 * Created by Alexandr Golovach on 18.02.17.
 */

public class Message {

	public enum Type {
		TEXT;// can add IMAGE, VIDEO, STICKERS in future
	}

	private String content;

	private Channel channel;

	private User owner;

	private Type type;

	private Date sentAt;

	public Message(@NonNull Channel channel, @NonNull User owner, @NonNull String content, @NonNull Type type, @NonNull Date sentAt) {
		this.channel = channel;
		this.content = content;
		this.owner = owner;
		this.type = type;
		this.sentAt = sentAt;
	}

	public String getContent() {
		return content;
	}

	public User getOwner() {
		return owner;
	}

	public Type getType() {
		return type;
	}

	public Date getSentAt() {
		return sentAt;
	}

	public Channel getChannel() {
		return channel;
	}
}
