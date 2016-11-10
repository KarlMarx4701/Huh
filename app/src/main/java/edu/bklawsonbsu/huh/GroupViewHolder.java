package edu.bklawsonbsu.huh;

import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class GroupViewHolder extends RecyclerView.ViewHolder{
    private ImageView groupPhoto;
    private Button groupNameButton;

    public GroupViewHolder(View itemView) {
        super(itemView);
        groupPhoto = (ImageView) itemView.findViewById(R.id.groupLogo);
        groupNameButton = (Button) itemView.findViewById(R.id.group_name_button);
    }

    public void setGroupName(String groupName) {
        groupNameButton.setText(groupName);
    }

}
