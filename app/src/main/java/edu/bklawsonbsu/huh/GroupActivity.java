package edu.bklawsonbsu.huh;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.net.URI;

public class GroupActivity extends AppCompatActivity {
    private static final String TAG = "GroupActivity";

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private String username;
    private String photoUrl;

    private RecyclerView groupList;
    private FirebaseRecyclerAdapter<Group, GroupViewHolder> firebaseRecyclerAdapter;
    private DatabaseReference databaseReference;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        if (firebaseUser == null) {
            startActivity(new Intent(this, SignInActivity.class));
            finish();
            return;
        } else {
            username = firebaseUser.getDisplayName();
            photoUrl = firebaseUser.getPhotoUrl().toString();
            TextView usernameLogo = (TextView) findViewById(R.id.usernameLogo);
            usernameLogo.setText(username);
        }

        groupList = (RecyclerView) findViewById(R.id.groupList);
        setupDataBind();
        groupList.setLayoutManager(layoutManager);
        groupList.setAdapter(firebaseRecyclerAdapter);
        Log.i(TAG, "Set adapter!");

    }

    public void setupDataBind() {
        layoutManager = new LinearLayoutManager(this);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Group, GroupViewHolder>(
                Group.class,
                R.layout.activity_group_chat,
                GroupViewHolder.class,
                databaseReference.child("Groups")
        ) {
            @Override
            protected void populateViewHolder(GroupViewHolder viewHolder, Group group, int position) {
                viewHolder.setGroupName(group.getGroupName());
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

}
