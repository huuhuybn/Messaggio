package com.dotplays.messaggio.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.dotplays.messaggio.R;
import com.dotplays.messaggio.firebase.FirebaseQuery;
import com.dotplays.messaggio.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.dotplays.messaggio.firebase.FirebaseQuery.USERS;

public class SignUpActivity extends AppCompatActivity {

    private EditText edtFirstName, edtLastName, edtUsername, edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        edtFirstName = findViewById(R.id.edtFirstName);
        edtLastName = findViewById(R.id.edtLastName);
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) finish();
        return super.onOptionsItemSelected(item);
    }

    public void signUp(View view) {
        // get all value in edit text
        final String firstName = edtFirstName.getText().toString().trim();
        final String lastName = edtLastName.getText().toString().trim();
        final String username = edtUsername.getText().toString().trim();
        final String password = edtPassword.getText().toString().trim();

        // dont forget validating value

        // pls do it yourself

        // Firstly, we check username is exits or not

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference users = database.getReference(USERS).child(username);

        users.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                // chua co user voi username duoc nhap
                if (dataSnapshot.getValue() == null) {

                    // hoc vien tu khoi tao model User
                    User user = new User();
                    user.firstName = firstName;
                    user.lastName = lastName;
                    user.password = password;
                    user.isOnline = true;
                    user.username = username.toLowerCase();


                    // them user vao nhanh Users
                    users.setValue(user, new DatabaseReference.CompletionListener() {

                        @Override
                        public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {

                            // hoc vien tu viet va kiem tra su kien loi va thanh cong
                            if (databaseError == null) {
                                String username = databaseReference.getRef().getKey();
                                Intent intent = new Intent();
                                intent.putExtra("data", username);
                                setResult(999, intent);
                                Toast.makeText(SignUpActivity.this,
                                        getString(R.string.notify_create_user_successful), Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(SignUpActivity.this,
                                        getString(R.string.notify_create_user_failure), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                    // username da ton tai, thong bao chon username khac
                } else {
                    Toast.makeText(SignUpActivity.this,
                            getString(R.string.notify_user_is_exits), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
