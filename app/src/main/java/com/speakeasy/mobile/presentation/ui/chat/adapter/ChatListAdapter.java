package com.speakeasy.mobile.presentation.ui.chat.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.speakeasy.mobile.data.model.Message;
import com.speakeasy.mobile.data.model.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import speakeasy.benallfree.com.speakeasy.R;

/**
 * Created by Alexandr Golovach on 18.02.17.
 */

public class ChatListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

	public static final java.text.DateFormat DATE_FORMAT = new SimpleDateFormat("dd MMM yyyy hh:mm a", Locale.US);

	public static final int VIEW_TYPE_ITEM_MY_MESSAGE = 0;
	public static final int VIEW_TYPE_ITEM_OTHER_MESSAGE = 1;

	private List<Message> messageList;
	private User user;

	public ChatListAdapter(@NonNull User user) {
		this.messageList = new ArrayList<>();
		this.user = user;
	}


	@Override
	public int getItemViewType(int position) {
		Message item = messageList.get(position);
		User owner = item.getOwner();
		if (TextUtils.equals(owner.getUserId(), user.getUserId())) {
			return VIEW_TYPE_ITEM_MY_MESSAGE;
		} else {
			return VIEW_TYPE_ITEM_OTHER_MESSAGE;
		}
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		View view = null;
		if (viewType == VIEW_TYPE_ITEM_MY_MESSAGE) {
			// user message
			view = inflater.inflate(R.layout.fragment_chat_my_message_item, parent, false);
			return new ChatMessageViewHolder(view);
		} else if (viewType == VIEW_TYPE_ITEM_OTHER_MESSAGE) {
			// other message
			view = inflater.inflate(R.layout.fragment_chat_other_message_item, parent, false);
			return new ChatMessageViewHolder(view);
		}
		return null;
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

		Message model = messageList.get(position);

		if (viewHolder instanceof ChatMessageViewHolder) {
			ChatMessageViewHolder holder = (ChatMessageViewHolder) viewHolder;
			holder.bindData(model);
		}
	}

	@Override
	public int getItemCount() {
		return messageList.size();
	}

	public void setMessageList(@NonNull List<Message> messageList) {
		this.messageList = messageList;
		notifyDataSetChanged();
	}

	public void addNewMessage(@NonNull Message message) {
		this.messageList.add(0, message);
		notifyItemInserted(0);
	}

}

