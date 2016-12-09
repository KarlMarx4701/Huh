package edu.bklawsonbsu.huh.groupClasses;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import edu.bklawsonbsu.huh.StaticGroupHolder;
import edu.bklawsonbsu.huh.changeLanguageClasses.SelectLanguage;
import edu.bklawsonbsu.huh.messageClasses.MessageActivity;
import edu.bklawsonbsu.huh.R;
import edu.bklawsonbsu.huh.signinClasses.SignInActivity;

public class GroupActivity extends AppCompatActivity {
    private static final String TAG = "GroupActivity";
    private StaticGroupHolder staticGroupHolder = new StaticGroupHolder();
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private RecyclerView groupList;
    private FirebaseRecyclerAdapter<Group, GroupViewHolder> firebaseRecyclerAdapter;
    private LinearLayoutManager layoutManager;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);
        context = this;
        initializeGroupActivity();
    }

    public void initializeGroupActivity() {
        initializeFirebaseUser();
        initializeSingoutButton();
        initializeGroupList();
        initializeAddGroup();
        initializeChangeLanguage();
    }

    public void initializeFirebaseUser() {
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser == null) {
            startActivity(new Intent(this, SignInActivity.class));
        }
    }

    public void initializeSingoutButton() {
        ImageButton signoutButton = (ImageButton) findViewById(R.id.signoutbutton);
        signoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                startActivity(new Intent(context, SignInActivity.class));
                finish();
            }
        });
    }

    private void initializeGroupList() {
        groupList = (RecyclerView) findViewById(R.id.groupList);
        setupDataBind();
        groupList.setLayoutManager(layoutManager);
        groupList.setAdapter(firebaseRecyclerAdapter);
        Log.i(TAG, "Set adapter!");
    }

    @SuppressWarnings("ConstantConditions") // Inspection Warning for toLowerCase()
    public void setupDataBind() {
        layoutManager = new LinearLayoutManager(this);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        final String userEmail = firebaseUser.getEmail().toLowerCase();
        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Group, GroupViewHolder>(
                Group.class,
                R.layout.activity_group_chat,
                GroupViewHolder.class,
                databaseReference.child("Groups")
        ) {
            @Override
            protected void populateViewHolder(GroupViewHolder viewHolder, final Group group, int position) {
                viewHolder.setGroup(group);
                viewHolder.setOnClick(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        staticGroupHolder.setGroup(group);
                        startMessaging();
                    }
                });
                viewHolder.setGroupName(group.getGroupName());
                viewHolder.checkAllowable(userEmail);
            }
        };
        Log.i(TAG ,"Got data!");
        firebaseRecyclerAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                int groupCount = firebaseRecyclerAdapter.getItemCount();
                int lastPosition = layoutManager.findLastCompletelyVisibleItemPosition();
                if (lastPosition == -1
                        || positionStart >= (groupCount - 1) && lastPosition == (positionStart - 1)) {
                    groupList.scrollToPosition(positionStart);
                }
            }
        });
    }

    public void startMessaging() {
        startActivity(new Intent(context, MessageActivity.class));
    }

    private void initializeAddGroup() {
        ImageButton addGroup = (ImageButton) findViewById(R.id.addgroupButton);
        addGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAddGroup();
            }
        });
    }

    public void startAddGroup(){
        startActivity(new Intent(context, AddGroup.class));
    }

    private void initializeChangeLanguage() {
        ImageButton changeLanguageButton = (ImageButton) findViewById(R.id.changeLangButton);
        changeLanguageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startChangeLanguageActivity();
            }
        });
    }

    public void startChangeLanguageActivity() {
        startActivity(new Intent(context, SelectLanguage.class));
    }
}
