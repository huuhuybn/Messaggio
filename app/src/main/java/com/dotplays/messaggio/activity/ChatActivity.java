package com.dotplays.messaggio.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dotplays.messaggio.R;
import com.dotplays.messaggio.adapter.ChatAdapter;
import com.dotplays.messaggio.firebase.FirebaseQuery;
import com.dotplays.messaggio.model.Chat;
import com.dotplays.messaggio.model.Group;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.dotplays.messaggio.firebase.FirebaseQuery.USERNAME;

public class ChatActivity extends AppCompatActivity {

    private String group;
    private RecyclerView recyclerView;
    private EditText edtInput;
    private Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        recyclerView = findViewById(R.id.lvList);
        edtInput = findViewById(R.id.edtInput);
        btnSend = findViewById(R.id.btnSend);

        group = getIntent().getStringExtra("data");

        setTitle(group);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = edtInput.getText().toString().trim();

                if (text.isEmpty()) {
                    edtInput.setError(getString(R.string.notify_empty_text));
                    return;
                }

                FirebaseQuery.sendMessage(group, text,USERNAME, System.currentTimeMillis(), new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                        edtInput.setText("");
                    }
                });

            }
        });

        FirebaseQuery.getListMessages(group, new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                GenericTypeIndicator<HashMap<String, Chat>> objectsGTypeInd =
                        new GenericTypeIndicator<HashMap<String, Chat>>() {
                        };
                Map<String, Chat> objectHashMap = dataSnapshot.getValue(objectsGTypeInd);

                if (objectHashMap != null){
                    final List<Chat> objectArrayList = new ArrayList<>(objectHashMap.values());

                    ChatAdapter chatAdapter = new ChatAdapter(ChatActivity.this, objectArrayList);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ChatActivity.this);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setAdapter(chatAdapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
