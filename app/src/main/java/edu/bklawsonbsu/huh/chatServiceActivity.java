package edu.bklawsonbsu.huh;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class chatServiceActivity extends AppCompatActivity {
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_service);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser == null) {
            Log.i("LOGIN", "Starting login action.");
            startActivity(new Intent(chatServiceActivity.this, loginchatServiceActivity.class));
        } else {
            TextView usernameOutput = (TextView) findViewById(R.id.usernameInfo);
            usernameOutput.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(chatServiceActivity.this, loginchatServiceActivity.class));
                }
            });
            usernameOutput.setText(firebaseUser.getEmail());
        }
    }
}
