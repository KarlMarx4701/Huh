package edu.bklawsonbsu.huh;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class loginchatServiceActivity extends AppCompatActivity {
    private TextView usernameTextView;
    private TextView passwordTextview;
    private Button loginButton;
    private Button registerButton;
    private LoginChatService loginChatService = new LoginChatService();
    private boolean loggedIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginchat_service);
        getObjectsFromLayout();
        addListenerObjects();
    }

    public void getObjectsFromLayout() {
        usernameTextView = (TextView) findViewById(R.id.usernameChatText);
        passwordTextview = (TextView) findViewById(R.id.passwordChatText);
        loginButton = (Button) findViewById(R.id.loginChatButton);
        registerButton = (Button) findViewById(R.id.registerChatButton);
    }

    public void addListenerObjects() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loggedIn = loginChatService.loginUser(usernameTextView.getText().toString(), passwordTextview.getText().toString());
                checkLoggedIn();
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loggedIn = loginChatService.registerUser(usernameTextView.getText().toString(), passwordTextview.getText().toString());
                checkLoggedIn();
            }
        });
    }

    public void checkLoggedIn() {
        if (loggedIn) {
            Log.i("LOGIN", "User logged in.");
            finish();
        }
    }
}
