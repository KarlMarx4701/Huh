package edu.bklawsonbsu.huh;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.Calendar;

public class MessageActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private FirebaseRecyclerAdapter<Message, MessageViewHolder> firebaseRecyclerAdapter;
    private LinearLayoutManager linearLayoutManager;
    private DatabaseReference firebaseDatabaseReference;
    private RecyclerView messageList;
    private KeyStore keyStore = new KeyStore();
    private TextView messageSendText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_acitivy);

        firebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        linearLayoutManager = new LinearLayoutManager(this);

        TextView groupNameLogo = (TextView) findViewById(R.id.groupNameMessaging);
        groupNameLogo.setText(keyStore.getGroupName());

        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Message, MessageViewHolder>(
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

        messageList = (RecyclerView) findViewById(R.id.messageList);
        messageList.setAdapter(firebaseRecyclerAdapter);
        messageList.setLayoutManager(linearLayoutManager);

        firebaseRecyclerAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                int groupCount = firebaseRecyclerAdapter.getItemCount();
                int lastPosition = linearLayoutManager.findLastCompletelyVisibleItemPosition();
                if (lastPosition == -1
                        || positionStart >= (groupCount - 1) && lastPosition == (positionStart - 1)) {
                    messageList.scrollToPosition(positionStart);
                }
            }
        });

        messageSendText = (TextView) findViewById(R.id.messageTextBox);
        Button sendButton = (Button) findViewById(R.id.sendMessagningButton);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!messageSendText.getText().equals("")) {
                    Message pushMessage = new Message(firebaseAuth.getCurrentUser().getDisplayName(), messageSendText.getText().toString(), Calendar.getInstance().getTime().toString());
                    firebaseDatabaseReference.child("Groups/" + keyStore.getKey() + "/messages").push().setValue(pushMessage);
                    messageSendText.setText("");
                }
            }
        });
    }
}
