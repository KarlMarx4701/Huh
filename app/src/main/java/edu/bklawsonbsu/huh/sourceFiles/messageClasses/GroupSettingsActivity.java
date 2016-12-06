package edu.bklawsonbsu.huh.sourceFiles.messageClasses;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.security.Key;
import java.util.ArrayList;
import java.util.Date;

import edu.bklawsonbsu.huh.R;
import edu.bklawsonbsu.huh.sourceFiles.KeyStore;
import edu.bklawsonbsu.huh.sourceFiles.groupClasses.Group;

/**
 * Created by Ben on 12/6/2016.
 */

public class GroupSettingsActivity extends AppCompatActivity {
    private EditText groupName;
    private Spinner groupColor;
    private EditText addUserText;
    private Button addUserButton;
    private Button removeUserButton;
    private Button updateGroupButton;
    private LinearLayout usersAddedList;
    private Group currentGroup = new Group();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_group);
        initializeLayoutObjects();
        addEmptyObjectToList();
        populateLayoutObjects();
        addButtonListeners();
    }

    private void populateLayoutObjects() {
        getGroupFromFirebase();
        groupName.setText(currentGroup.getGroupName());
        populateListOfUsers();
    }

    private void populateListOfUsers() {
        ArrayList<String> currentUsers = new ArrayList<>();
        String userString = currentGroup.getUsers();
        String [] usersSplit = userString.split(",");
        for (String email: usersSplit) {
            TextView textHolder = new TextView(this);
            textHolder.setText(email);
            addTextListener(textHolder);
            usersAddedList.addView(textHolder);
        }
    }

    private void addTextListener(TextView textHolder) {

    }

    private Group getGroupFromFirebase() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("Groups").child(new KeyStore().getKey()).get
        return currentGroup;
    }

    private void addEmptyObjectToList() {
        TextView emptyTextView = new TextView(this);
        emptyTextView.setText("");
        usersAddedList.addView(emptyTextView);
    }

    private void addButtonListeners() {
        addUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void initializeLayoutObjects() {
        groupName = (EditText) findViewById(R.id.groupupdate_name);
        groupColor = (Spinner) findViewById(R.id.groupupdate_color);
        addUserText = (EditText) findViewById(R.id.groupupdate_addusertext);
        addUserButton = (Button) findViewById(R.id.groupupdate_adduserbutton);
        removeUserButton = (Button) findViewById(R.id.groupupdate_removeuserbutton);
        updateGroupButton = (Button) findViewById(R.id.updategroup_confirm);
        usersAddedList = (LinearLayout) findViewById(R.id.groupupdate_userlist);
    }
}
