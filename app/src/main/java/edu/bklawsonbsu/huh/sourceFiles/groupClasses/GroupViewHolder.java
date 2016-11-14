package edu.bklawsonbsu.huh.sourceFiles.groupClasses;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import edu.bklawsonbsu.huh.R;

public class GroupViewHolder extends RecyclerView.ViewHolder{
    private ImageView groupPhoto;
    private Button groupNameButton;
    private View.OnClickListener buttonListener;
    private Group group;

    public GroupViewHolder(View itemView) {
        super(itemView);
        groupPhoto = (ImageView) itemView.findViewById(R.id.groupLogo);
        groupNameButton = (Button) itemView.findViewById(R.id.group_name_button);
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void setOnClick(View.OnClickListener listener) {
        buttonListener = listener;
    }

    public void setGroupName(String groupName) {
        groupNameButton.setText(groupName);
        groupNameButton.setOnClickListener(buttonListener);
    }

    public void checkAllowable(String email) {
        if (group.isAllowed(email)) {
            groupNameButton.setClickable(false);
            groupNameButton.setBackgroundColor(Color.RED);
            groupNameButton.setText("");
        }
    }
}
