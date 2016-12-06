package edu.bklawsonbsu.huh.sourceFiles.groupClasses;

import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import edu.bklawsonbsu.huh.R;

@SuppressWarnings("WeakerAccess")
public class GroupViewHolder extends RecyclerView.ViewHolder{
    private Button groupNameButton;
    private View.OnClickListener buttonListener;
    private TextView ownerText;
    private Group group;
    private LinearLayout groupLinearLayout;

    public GroupViewHolder(View itemView) {
        super(itemView);
        groupNameButton = (Button) itemView.findViewById(R.id.group_name_button);
        ownerText = (TextView) itemView.findViewById(R.id.ownerText);
        groupLinearLayout = (LinearLayout) itemView.findViewById(R.id.group_list_indi);
    }

    public void setGroup(Group group) {
        this.group = group;
        setColor();
        setOwner();
    }

    private void setOwner() {
        ownerText.setText("Owner: " + group.getOwner());
    }

    public void setColor() {
        groupLinearLayout.setBackgroundColor(Color.parseColor(group.getColor()));
    }

    public void setOnClick(View.OnClickListener listener) {
        buttonListener = listener;
        groupLinearLayout.setOnClickListener(buttonListener);
        LinearLayout groupNameLayout = (LinearLayout) groupLinearLayout.getChildAt(0);
        for (int i = 0; i < groupNameLayout.getChildCount(); i++) {
            groupNameLayout.getChildAt(i).setOnClickListener(buttonListener);
        }
        groupLinearLayout.getChildAt(1).setOnClickListener(buttonListener);
    }

    public void setGroupName(String groupName) {
        groupNameButton.setText(groupName);
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
