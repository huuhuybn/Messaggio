package com.dotplays.messaggio.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dotplays.messaggio.R;
import com.dotplays.messaggio.activity.ChatActivity;
import com.dotplays.messaggio.adapter.GroupAdapter;
import com.dotplays.messaggio.firebase.FirebaseQuery;
import com.dotplays.messaggio.model.Group;
import com.dotplays.messaggio.utils.ItemClickSupport;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InboxFragment extends Fragment {

    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inbox, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.lvList);

        FirebaseQuery.getListGroups(FirebaseQuery.USERNAME, new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                GenericTypeIndicator<HashMap<String, Group>> objectsGTypeInd = new GenericTypeIndicator<HashMap<String, Group>>() {
                };
                Map<String, Group> objectHashMap = dataSnapshot.getValue(objectsGTypeInd);

                if (objectHashMap!=null){
                    final List<Group> objectArrayList = new ArrayList<>(objectHashMap.values());
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                    GroupAdapter groupAdapter = new GroupAdapter(getActivity(), objectArrayList);
                    recyclerView.setAdapter(groupAdapter);
                    recyclerView.setLayoutManager(linearLayoutManager);

                    ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                        @Override
                        public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                            Intent intent = new Intent(getActivity(), ChatActivity.class);
                            intent.putExtra("data", objectArrayList.get(position).id);
                            startActivity(intent);
                        }
                    });
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
