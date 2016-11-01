package edu.bklawsonbsu.huh;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.net.URI;

public class GroupActivity extends AppCompatActivity {
    private static final String TAG = "GroupActivity";

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private String username;

    private RecyclerView groupList;

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
            TextView usernameLogo = (TextView) findViewById(R.id.usernameLogo);
            usernameLogo.setText(username);
        }

        groupList = (RecyclerView) findViewById(R.id.groupList);

    }

}
