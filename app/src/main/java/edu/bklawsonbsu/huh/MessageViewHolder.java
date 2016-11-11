package edu.bklawsonbsu.huh;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class MessageViewHolder extends RecyclerView.ViewHolder{
    private Message message;
    private TextView messageText;
    private TextView messageUsername;
    private TextView messageTime;


    public MessageViewHolder(View itemView) {
        super(itemView);
        messageText = (TextView) itemView.findViewById(R.id.messageText);
        messageUsername = (TextView) itemView.findViewById(R.id.messageUsername);
        messageTime = (TextView) itemView.findViewById(R.id.messageTimeStamp);
    }

    public void setMessage(Message message) {
        this.message = message;
        messageText.setText(message.getText());
        messageUsername.setText(message.getUsername());
        messageTime.setText(message.getTime());
    }
}
