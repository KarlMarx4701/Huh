package edu.bklawsonbsu.huh;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MessageActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private DatabaseReference firebaseDatabaseReference;
    private KeyStore keyStore = new KeyStore();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_acitivy);

        firebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        FirebaseRecyclerAdapter firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Message, MessageViewHolder>(
                Message.class,
                R.layout.message,
                MessageViewHolder.class,
                firebaseDatabaseReference.child("Groups/" + keyStore.getKey() + "/messages")
        ) {
            @Override
            public void populateViewHolder(MessageViewHolder messageHolder, final Message message, int position) {
                messageHolder.setMessage(message);
            }
        };

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.messageList);
        recyclerView.setAdapter(firebaseRecyclerAdapter);
        recyclerView.setLayoutManager(layoutManager);
    }
}
