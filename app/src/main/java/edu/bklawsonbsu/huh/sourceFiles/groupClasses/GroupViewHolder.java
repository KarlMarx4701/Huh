package edu.bklawsonbsu.huh.sourceFiles.groupClasses;

import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewManager;
import android.widget.Button;
import android.widget.LinearLayout;

import edu.bklawsonbsu.huh.R;

@SuppressWarnings("WeakerAccess")
public class GroupViewHolder extends RecyclerView.ViewHolder{
    private Button groupNameButton;
    private View.OnClickListener buttonListener;
    private Group group;

    public GroupViewHolder(View itemView) {
        super(itemView);
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
        if (!group.isAllowed(email)) {
            groupNameButton.setClickable(false);
            groupNameButton.setBackgroundColor(Color.RED);
            groupNameButton.setText("");
            LinearLayout layout = (LinearLayout) itemView.findViewById(R.id.group_list_indi);
            ((ViewManager)layout.getParent()).removeView(layout);
        }
    }
}
