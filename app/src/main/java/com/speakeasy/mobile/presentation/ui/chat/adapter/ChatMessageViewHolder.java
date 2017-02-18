package com.speakeasy.mobile.presentation.ui.chat.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.speakeasy.mobile.data.model.Message;

import butterknife.Bind;
import butterknife.ButterKnife;
import speakeasy.benallfree.com.speakeasy.R;

/**
 * Created by Alexandr Golovach on 18.02.17.
 */

public class ChatMessageViewHolder extends RecyclerView.ViewHolder {

	@Bind(R.id.conversation_list_item_message_time)
	private TextView timeView;

	@Bind(R.id.conversation_list_item_message_text)
	private TextView contentView;

	@Bind(R.id.conversation_list_item_user_photo)
	private ImageView userPhotoView;

	public ChatMessageViewHolder(View v) {
		super(v);
		ButterKnife.bind(this, v);
	}

	public void bindData(@NonNull Message model) {
		timeView.setText(ChatListAdapter.DATE_FORMAT.format(model.getSentAt()));
		contentView.setText(model.getContent());
	}

}
