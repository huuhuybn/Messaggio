package com.dotplays.messaggio.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dotplays.messaggio.R;
import com.dotplays.messaggio.model.Group;
import com.dotplays.messaggio.utils.OnGroupClickListener;

import java.util.List;

public class GroupAdapter extends RecyclerView.Adapter<GroupHolder> {


    private Context context;
    private List<Group> groupList;

    public void setOnGroupClickListener(OnGroupClickListener onGroupClickListener) {
        this.onGroupClickListener = onGroupClickListener;
    }

    private OnGroupClickListener onGroupClickListener;

    public GroupAdapter(Context context, List<Group> groupList) {
        this.context = context;
        this.groupList = groupList;
    }


    @NonNull
    @Override
    public GroupHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GroupHolder(LayoutInflater.from(context).inflate(R.layout.group, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull GroupHolder holder, int position) {
        Group group = groupList.get(position);
        holder.tvGroupName.setText(group.id);
        holder.tvLastUpdate.setText(group.lastUpdate);
        holder.tvTime.setText(String.valueOf(group.time));
    }

    @Override
    public int getItemCount() {
        return groupList.size();
    }
}
