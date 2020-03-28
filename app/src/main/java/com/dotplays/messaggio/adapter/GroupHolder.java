package com.dotplays.messaggio.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dotplays.messaggio.R;
import com.dotplays.messaggio.model.Group;
import com.dotplays.messaggio.utils.OnGroupClickListener;

class GroupHolder extends RecyclerView.ViewHolder {


    public TextView tvGroupName;
    public TextView tvLastUpdate;
    public TextView tvTime;
    public View item;
    public GroupHolder(@NonNull View convertView) {
        super(convertView);
        item = convertView;
        tvGroupName = convertView.findViewById(R.id.tvGroupName);
        tvLastUpdate = convertView.findViewById(R.id.tvLastUpdate);
        tvTime = convertView.findViewById(R.id.tvTime);
    }

}
