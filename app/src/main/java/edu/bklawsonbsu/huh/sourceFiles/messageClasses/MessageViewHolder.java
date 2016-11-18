package edu.bklawsonbsu.huh.sourceFiles.messageClasses;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import edu.bklawsonbsu.huh.R;
import edu.bklawsonbsu.huh.sourceFiles.translationClasses.Translator;

@SuppressWarnings("WeakerAccess") //Inspection problems
public class MessageViewHolder extends RecyclerView.ViewHolder{
    private Message message;
    private String messageTranslated;
    private TextView messageText;
    private TextView messageUsername;
    private TextView messageTime;
    private LinearLayout background;
    private Translator translator;
    private Boolean isUserCreated;


    public MessageViewHolder(View itemView) {
        super(itemView);
        translator = new Translator();
        messageText = (TextView) itemView.findViewById(R.id.messageText);
        messageUsername = (TextView) itemView.findViewById(R.id.messageUsername);
        messageTime = (TextView) itemView.findViewById(R.id.messageTimeStamp);
        background = (LinearLayout) itemView.findViewById(R.id.messageBackground);
    }

    public void setMessage(Message message, String username) {
            this.message = message;
            messageText.setText(message.getText());
            messageUsername.setText(message.getUsername());
            messageTime.setText(message.getTime());
            translateText(message.getText());
            messageText.setText(messageTranslated);
            checkUserCreated(username);
            setupUserCreated();

    }

    public void translateText(String text) {
        messageTranslated = translator.translateText(text, "el");
    }

    public void checkUserCreated(String username) {
        isUserCreated = username.equals(messageUsername.getText().toString());
    }

    public void setupUserCreated() {
        if (isUserCreated) {
            background.setBackgroundColor(Color.rgb(51, 102, 255));
        }
    }
}
