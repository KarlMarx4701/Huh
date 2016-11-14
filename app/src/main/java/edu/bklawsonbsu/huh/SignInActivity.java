package edu.bklawsonbsu.huh;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import edu.bklawsonbsu.huh.groupClasses.GroupActivity;


public class SignInActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    private static final int RC_SIGN_IN = 9001;

    private View.OnClickListener signinButtonListener;
    private SignInButton signInButton;
    private GoogleSignInOptions googleSignInOptions;
    private GoogleApiClient googleApiClient;
    private FirebaseAuth firebaseAuth;

    private static final String TAG = "SignIn";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        signInButton = (SignInButton) findViewById(R.id.sign_in_button);
        setupButtonListener();
        setupSignInOptions();
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void setupButtonListener() {
        signinButtonListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        };
        signInButton.setOnClickListener(signinButtonListener);
    }

    private void setupSignInOptions() {
        googleSignInOptions = new GoogleSignInOptions
                .Builder(googleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient
                .Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
                .build();
    }

    private void signIn() {
        Log.i(TAG, "SignIn Reached.");
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    public void handleFirebaseAuthResult(AuthResult authResult) {
        if (authResult != null) {
            FirebaseUser user = authResult.getUser();
            Toast.makeText(this, "Welcome " + user.getDisplayName(), Toast.LENGTH_SHORT).show();

            startActivity(new Intent(this, GroupActivity.class));
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Log.i(TAG, "Request code: " + requestCode);
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleActivityResult(result);
        }
    }

    public void handleActivityResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            Log.i(TAG, "Result is successful");
            GoogleSignInAccount account = result.getSignInAccount();
            firebaseAuthWithGoogle(account);
        } else {
            Log.e(TAG, "Google Sign In Failed.");
        }
    }

    public void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        Log.i(TAG, "firebaseAuthWithGoogle: " + account.getDisplayName());
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.i(TAG, "Sign in successful");
                            startActivity(new Intent(SignInActivity.this, GroupActivity.class));
                            finish();
                        } else {
                            Log.e(TAG, "Sign in falied");
                        }
                    }
                });
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.e(TAG, "Connection error!");
    }
}
