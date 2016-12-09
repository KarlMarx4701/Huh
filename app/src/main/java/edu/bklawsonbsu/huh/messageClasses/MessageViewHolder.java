package edu.bklawsonbsu.huh.messageClasses;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import edu.bklawsonbsu.huh.R;

@SuppressWarnings("WeakerAccess") //Inspection problems
public class MessageViewHolder extends RecyclerView.ViewHolder{
    private TextView messageText;
    private TextView messageUsername;
    private TextView messageTime;
    private LinearLayout background;

    public MessageViewHolder(View itemView) {
        super(itemView);
        messageText = (TextView) itemView.findViewById(R.id.messageText);
        messageUsername = (TextView) itemView.findViewById(R.id.messageUsername);
        messageTime = (TextView) itemView.findViewById(R.id.messageTimeStamp);
        background = (LinearLayout) itemView.findViewById(R.id.messageBackground);
    }

    public void setObjectData(String text, String username, String time) {
        messageText.setText(text);
        messageUsername.setText(username);
        messageTime.setText(time);
    }

    public void setOwnerCreatedMessageColor() {
        background.setBackgroundColor(Color.rgb(100, 160, 210));
    }


}
