package edu.bklawsonbsu.huh;

import android.provider.Settings;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class GroupViewHolder extends RecyclerView.ViewHolder{
    private ImageView groupPhoto;
    private Button groupNameButton;
    private View.OnClickListener buttonListener;
    private Group group;
    private List<String> usersAllowedList;

    public GroupViewHolder(View itemView) {
        super(itemView);
        groupPhoto = (ImageView) itemView.findViewById(R.id.groupLogo);
        groupNameButton = (Button) itemView.findViewById(R.id.group_name_button);
        usersAllowedList = new ArrayList<>();
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void splitUsers() {
        String[] tempArr = group.getUsers().split(",");

        for (String item: tempArr) {
            usersAllowedList.add(item);
        }
    }

    public void setOnClick(String email) {
        final String finalEmail = email.toLowerCase();
        splitUsers();
        buttonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (usersAllowedList.contains(finalEmail)) {
                    System.out.println("Allowed! " + finalEmail + " | " + group.getUsers());
                } else {
                    System.out.println("Not Allowed!");
                }
            }
        };
    }

    public void setGroupName(String groupName) {
        groupNameButton.setText(groupName);
        groupNameButton.setOnClickListener(buttonListener);
    }

}
