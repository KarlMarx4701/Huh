package edu.bklawsonbsu.huh.messageClasses;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import edu.bklawsonbsu.huh.R;
import edu.bklawsonbsu.huh.translationClasses.Translator;

public class MessageViewHolder extends RecyclerView.ViewHolder{
    private Message message;
    private TextView messageText;
    private TextView messageUsername;
    private TextView messageTime;
    private Button translateButton;
    private static Translator translator;


    public MessageViewHolder(View itemView) {
        super(itemView);
        translator = new Translator();
        messageText = (TextView) itemView.findViewById(R.id.messageText);
        messageUsername = (TextView) itemView.findViewById(R.id.messageUsername);
        messageTime = (TextView) itemView.findViewById(R.id.messageTimeStamp);
        translateButton = (Button) itemView.findViewById(R.id.translateButton);
        translateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                translateText(message.getText());
            }
        });
    }

    public void setMessage(Message message) {
        this.message = message;
        messageText.setText(message.getText());
        messageUsername.setText(message.getUsername());
        messageTime.setText(message.getTime());
    }

    public void translateText(String text) {
        String translatedText = "";
        translatedText = translator.translateText(text, "es");
        messageText.setText(translatedText);
    }
}
