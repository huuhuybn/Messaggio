package com.dotplays.messaggio.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dotplays.messaggio.R;
import com.dotplays.messaggio.model.Chat;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatHolder> {

    private Context context;

    private List<Chat> chats;

    public ChatAdapter(Context context, List<Chat> chats) {
        this.context = context;
        this.chats = chats;
    }

    @NonNull
    @Override
    public ChatHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.chat, parent, false);
        return new ChatHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatHolder holder, int position) {
        Chat chat = chats.get(position);
        holder.tvUser.setText(chat.sendBy);
        holder.tvText.setText(chat.text);
        holder.tvTime.setText(chat.time + "");
    }

    @Override
    public int getItemCount() {
        return chats.size();
    }
}
