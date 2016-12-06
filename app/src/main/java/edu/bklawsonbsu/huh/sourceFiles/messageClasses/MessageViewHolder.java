package edu.bklawsonbsu.huh.sourceFiles.messageClasses;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import edu.bklawsonbsu.huh.R;
import edu.bklawsonbsu.huh.sourceFiles.translationClasses.Translator;

@SuppressWarnings("WeakerAccess") //Inspection problems
public class MessageViewHolder extends RecyclerView.ViewHolder{
    private TextView messageText;
    private TextView messageUsername;
    private TextView messageTime;
    private LinearLayout background;
    private String currentUsername;

    public MessageViewHolder(View itemView) {
        super(itemView);
        messageText = (TextView) itemView.findViewById(R.id.messageText);
        messageUsername = (TextView) itemView.findViewById(R.id.messageUsername);
        messageTime = (TextView) itemView.findViewById(R.id.messageTimeStamp);
        background = (LinearLayout) itemView.findViewById(R.id.messageBackground);
    }

    public void setCurrentUser(String username) {
        currentUsername = username;
    }

    public void setObjectData(String text, String username, String time) {
        messageText.setText(text);
        messageUsername.setText(username);
        messageTime.setText(time);
    }

    public void setUserCreatedColor(String username) {
        if (currentUsername.equals(username)) {
            background.setBackgroundColor(Color.rgb(51, 102, 255));
        } else {
            background.setBackgroundColor(Color.rgb(125, 205, 251));
        }
    }
}
