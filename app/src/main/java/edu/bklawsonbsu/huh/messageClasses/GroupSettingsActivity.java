package edu.bklawsonbsu.huh.messageClasses;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import edu.bklawsonbsu.huh.R;
import edu.bklawsonbsu.huh.StaticGroupHolder;
import edu.bklawsonbsu.huh.groupClasses.Group;

public class GroupSettingsActivity extends AppCompatActivity {
    private EditText groupName;
    private EditText addUserText;
    private Button addUserButton;
    private Button removeUserButton;
    private Button updateGroupButton;
    private LinearLayout usersAddedList;
    private StaticGroupHolder staticGroupHolder = new StaticGroupHolder();
    private Group currentGroup;
    private Context context;
    private TextView selectedUser;
    private ArrayList<String> usersArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_group);
        context = this;
        currentGroup = staticGroupHolder.getGroup();
        initializeSettings();
    }

    private void initializeSettings() {
        initializeLayoutObjects();
        addEmptyObjectToList();
        populateLayoutObjects();
        addButtonListeners();
    }

    private void initializeLayoutObjects() {
        groupName = (EditText) findViewById(R.id.groupupdate_name);
        addUserText = (EditText) findViewById(R.id.groupupdate_addusertext);
        addUserButton = (Button) findViewById(R.id.groupupdate_adduserbutton);
        removeUserButton = (Button) findViewById(R.id.groupupdate_removeuserbutton);
        updateGroupButton = (Button) findViewById(R.id.updategroup_confirm);
        usersAddedList = (LinearLayout) findViewById(R.id.groupupdate_userlist);
    }

    private void addEmptyObjectToList() {
        TextView emptyTextView = new TextView(this);
        emptyTextView.setText("");
        usersAddedList.addView(emptyTextView);
    }

    private void populateLayoutObjects() {
        groupName.setText(currentGroup.getGroupName());
        populateListOfUsers();
    }

    private void populateListOfUsers() {
        String userString = currentGroup.getUsers();
        String [] usersSplit = userString.split(",");
        for (String email: usersSplit) {
            usersArrayList.add(email);
            TextView textHolder = new TextView(this);
            textHolder.setText(email);
            addTextListener(textHolder);
            usersAddedList.addView(textHolder);
        }
    }

    private void addTextListener(final TextView textHolder) {
        textHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < usersAddedList.getChildCount(); i++) {
                    usersAddedList.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
                }
                selectedUser = textHolder;
                textHolder.setBackgroundColor(Color.rgb(175, 206, 255));
            }
        });
    }

    private void addButtonListeners() {
        addUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!usersArrayList.contains(addUserText.getText().toString().toLowerCase())) {
                    TextView recyclerView = new TextView(context);
                    recyclerView.setText(addUserText.getText().toString().toLowerCase());
                    addTextListener(recyclerView);
                    usersArrayList.add(addUserText.getText().toString().toLowerCase());
                    usersAddedList.addView(recyclerView);
                }
                addUserText.setText("");
            }
        });
        removeUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usersAddedList.removeView(selectedUser);
                usersArrayList.remove(selectedUser.getText().toString().toLowerCase());
            }
        });
        updateGroupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateGroupInfo();
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                databaseReference.child("Groups/" + currentGroup.getKey()).setValue(currentGroup);
                staticGroupHolder.setGroup(currentGroup);
                finish();
            }
        });
    }

    private void updateGroupInfo() {
        if (!groupName.getText().toString().equals("")) {
            currentGroup.setGroupName(groupName.getText().toString());
        }
        currentGroup.setUsers(makeUserString());
    }

    private String makeUserString() {
        String returnString = usersArrayList.get(0);
        for (int i = 1; i < usersArrayList.size(); i++) {
            returnString += "," + usersArrayList.get(i);
        }
        if (!usersArrayList.contains(currentGroup.getOwner())) {
            returnString += currentGroup.getOwner();
        }
        return returnString;
    }
}
