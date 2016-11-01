package edu.bklawsonbsu.huh;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class GroupViewHolder extends RecyclerView.ViewHolder{
    private ImageView groupPhoto;
    private TextView groupName;

    public GroupViewHolder(View itemView) {
        super(itemView);
        groupPhoto = (ImageView) itemView.findViewById(R.id.groupLogo);
        groupName = (TextView) itemView.findViewById(R.id.group_name);
    }
}
