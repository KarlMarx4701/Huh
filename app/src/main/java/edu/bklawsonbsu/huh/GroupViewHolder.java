package edu.bklawsonbsu.huh;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
            usersAllowedList.add(item.toLowerCase());
        }
    }

    public void setOnClick(String email, View.OnClickListener listener) {
        buttonListener = listener;
    }

    public void setGroupName(String groupName) {
        groupNameButton.setText(groupName);
        groupNameButton.setOnClickListener(buttonListener);
    }

    public void checkAllowable(String email) {
        splitUsers();
        if (!usersAllowedList.contains(email)) {
            groupNameButton.setClickable(false);
            groupNameButton.setBackgroundColor(Color.RED);
            groupNameButton.setText("");
        }
    }
}
