package edu.bklawsonbsu.huh.groupClasses;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import edu.bklawsonbsu.huh.R;

@SuppressWarnings("ConstantConditions") // Inspection warning for getEmail()
public class AddGroup extends AppCompatActivity {
    private EditText groupNameText;
    private EditText groupUserAddEmailText;
    private Button addUserButton;
    private Button createGroup;
    private ArrayList<String> userArrayList = new ArrayList<>();
    private LinearLayout userAddedList;
    private Button removeUserButton;
    private TextView selectedUsername;
    private Context context;
    private DatabaseReference databaseReference;
    private String currentUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group);
        context = this;
        initializeObjects();
    }

    private void initializeObjects() {
        groupNameText = (EditText) findViewById(R.id.groupname_textbox);
        groupUserAddEmailText = (EditText) findViewById(R.id.groupuser_textbox);
        addUserButton = (Button) findViewById(R.id.adduser_button);
        createGroup = (Button) findViewById(R.id.create_group_button);
        userAddedList = (LinearLayout) findViewById(R.id.userList);
        removeUserButton = (Button) findViewById(R.id.removeuser_button);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        currentUserName = FirebaseAuth.getInstance().getCurrentUser().getEmail().toLowerCase();
        addEmptyObject();
        addOnClickListener();
    }

    private void addEmptyObject() {
        TextView emptyUser = new TextView(context);
        emptyUser.setText("");
        userAddedList.addView(emptyUser);
    }

    private void addOnClickListener() {
        addUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEmail = groupUserAddEmailText.getText().toString().toLowerCase();
                if (!userEmail.equals("")) {
                    addUserToList(userEmail);
                    groupUserAddEmailText.setText("");
                }
            }
        });
        removeUserButton.setOnClickListener(new View.OnClickListener() {
            @SuppressWarnings("SuspiciousMethodCalls") // Inspection warning for removing items from a list
            @Override
            public void onClick(View v) {
                if (selectedUsername != null) {
                    userAddedList.removeView(selectedUsername);
                    userArrayList.remove(selectedUsername.getText());
                }
            }
        });
        createGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEmailListString = getUserEmailString();
                String groupName = groupNameText.getText().toString();
                String ownerInfo = FirebaseAuth.getInstance().getCurrentUser().getEmail();
                String key = databaseReference.child("Groups").push().getKey();
                Group newGroup = new Group(groupName, " ", userEmailListString, key, ownerInfo);
                databaseReference.child("Groups/" + key).setValue(newGroup);
                finish();
            }
        });
    }

    private void addUserToList(String email) {
        if (!userArrayList.contains(email)) {
            userArrayList.add(email);
            TextView userEmailView = new TextView(context);
            userEmailView.setText(email);
            userEmailView.setOnClickListener(selectUserEmail(userEmailView));
            userAddedList.addView(userEmailView);
        }
    }

    private String getUserEmailString() {
        checkOwnerIsInGroup();
        String returnString = userArrayList.get(0);
        for (int i = 1; i < userArrayList.size(); i++) {
            returnString += "," + userArrayList.get(i);
        }
        return returnString;
    }

    private void checkOwnerIsInGroup() {
        if (!userArrayList.contains(currentUserName)) {
            userArrayList.add(currentUserName);
        }
    }

    private View.OnClickListener selectUserEmail(final TextView emailTextView) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < userAddedList.getChildCount(); i++) {
                    userAddedList.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
                }
                emailTextView.setBackgroundColor(Color.rgb(175, 206, 255));
                selectedUsername = emailTextView;
            }
        };
    }

}
