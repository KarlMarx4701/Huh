package edu.bklawsonbsu.huh.messageClasses;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

import edu.bklawsonbsu.huh.R;
import edu.bklawsonbsu.huh.StaticGroupHolder;

public class MessageActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private FirebaseRecyclerAdapter<Message, MessageViewHolder> firebaseRecyclerAdapter;
    private LinearLayoutManager linearLayoutManager;
    private DatabaseReference firebaseDatabaseReference;
    private RecyclerView messageList;
    private StaticGroupHolder staticGroupHolder = new StaticGroupHolder();
    private TextView messageSendText;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_activity);
        initializeMessageActivity();
    }

    public void initializeMessageActivity() {
        context = this;
        initializeFirebase();
        setupDataBind();
        initializeLogo(staticGroupHolder.getGroup().getGroupName());
        initializeMessageText();
        initializeButtonListener();
    }

    public void initializeFirebase() {
        firebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();
        linearLayoutManager = new LinearLayoutManager(this);
    }

    @SuppressWarnings("ConstantConditions") // Inspection Warning for getDisplayName()
    public void setupDataBind() {
        final String username = firebaseAuth.getCurrentUser().getDisplayName();
        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Message, MessageViewHolder>(
                Message.class,
                R.layout.message,
                MessageViewHolder.class,
                firebaseDatabaseReference.child("Groups/" + staticGroupHolder.getGroup().getKey() + "/messages")
        ) {
            @Override
            public void populateViewHolder(MessageViewHolder messageHolder, final Message message, int position) {
                message.translateText(staticGroupHolder.getLanguageAbbr());
                if (message.getUsername().equals(username)) {
                    messageHolder.setOwnerCreatedMessageColor();
                }
                messageHolder.setObjectData(message.getText(), message.getUsername(), message.getTime());
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

    public void initializeLogo(String name) {
        TextView groupNameLogo = (TextView) findViewById(R.id.groupNameMessaging);
        groupNameLogo.setText(name);
    }

    public void initializeMessageText() {
        messageSendText = (TextView) findViewById(R.id.messageTextBox);
        ImageButton sendButton = (ImageButton) findViewById(R.id.sendMessageButton);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @SuppressWarnings("ConstantConditions")
            @Override
            public void onClick(View v) {
                if (!messageSendText.getText().toString().equals("")) {
                    Message pushMessage = new Message(firebaseAuth.getCurrentUser().getDisplayName(), messageSendText.getText().toString(), formatTimeStamp());
                    firebaseDatabaseReference.child("Groups/" + staticGroupHolder.getGroup().getKey() + "/messages").push().setValue(pushMessage);
                    messageSendText.setText("");
                }
            }
        });
    }

    public String formatTimeStamp() {
        String unformmatedTime = Calendar.getInstance().getTime().toString();
        return String.format("%s %s", unformmatedTime.substring(4,16), unformmatedTime.substring(24,28));
    }

    private void initializeButtonListener() {
        ImageButton settingsButton = (ImageButton) findViewById(R.id.group_settings_button);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, GroupSettingsActivity.class));
                initializeLogo(staticGroupHolder.getGroup().getGroupName());
            }
        });
    }
}
