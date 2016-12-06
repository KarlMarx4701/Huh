package edu.bklawsonbsu.huh.sourceFiles.messageClasses;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

import edu.bklawsonbsu.huh.R;
import edu.bklawsonbsu.huh.sourceFiles.KeyStore;

public class MessageActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
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
        initializeMessageActivity();
    }

    public void initializeMessageActivity() {
        initializeFirebase();
        setupDataBind();
        initializeLogo();
        initializeMessageText();
    }

    public void initializeFirebase() {
        firebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();
        linearLayoutManager = new LinearLayoutManager(this);
    }

    public void setupDataBind() {
        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Message, MessageViewHolder>(
                Message.class,
                R.layout.message,
                MessageViewHolder.class,
                firebaseDatabaseReference.child("Groups/" + keyStore.getKey() + "/messages")
        ) {
            @Override
            public void populateViewHolder(MessageViewHolder messageHolder, final Message message, int position) {
                message.translateText(new KeyStore().getLanguageAbbr());
                messageHolder.setCurrentUser(firebaseAuth.getCurrentUser().getDisplayName());
                messageHolder.setObjectData(message.getText(), message.getUsername(), message.getTime());
                messageHolder.setUserCreatedColor(message.getUsername());

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
    }

    public void initializeLogo() {
        TextView groupNameLogo = (TextView) findViewById(R.id.groupNameMessaging);
        groupNameLogo.setText(keyStore.getGroupName());
        groupNameLogo.setBackgroundColor(Color.parseColor(keyStore.getColor()));
    }

    public void initializeMessageText() {
        messageSendText = (TextView) findViewById(R.id.messageTextBox);
        ImageButton sendButton = (ImageButton) findViewById(R.id.sendMessageButton);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @SuppressWarnings("ConstantConditions")
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
