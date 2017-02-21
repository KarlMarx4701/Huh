package edu.bklawsonbsu.huh;

import android.content.Context;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class LoginChatService {
    private FirebaseAuth firebaseAuth;

    public LoginChatService() {
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public boolean loginUser(String username, String password) {
        Task signInTask = firebaseAuth.signInWithEmailAndPassword(username, password);
        if (signInTask.isSuccessful()) {
            System.out.println("Signin successful!");
            return true;
        } else {
            System.out.println("Signin failed!");
            return false;
        }
    }

    public boolean registerUser(String username, String password) {
        Task registerTask = firebaseAuth.createUserWithEmailAndPassword(username, password);
        if (registerTask.isSuccessful()) {
            System.out.println("Register successful!");
            firebaseAuth.signInWithEmailAndPassword(username, password);
            return true;
        } else {
            System.out.println("Register failed!");
            return false;
        }
    }
}
