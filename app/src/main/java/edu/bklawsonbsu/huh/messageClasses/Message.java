package edu.bklawsonbsu.huh.messageClasses;

import edu.bklawsonbsu.huh.translationClasses.Translator;

@SuppressWarnings("WeakerAccess") // Inspection problems
public class Message {
    private String username;
    private String text;
    private String time;
    private Translator messageTranslator = new Translator();
    private boolean isTranslated = false;

    public Message() {

    }

    public Message(String username, String text, String time) {
        this.username = username;
        this.text = text;
        this.time = time;
    }

    public String getUsername() {
        return username;
    }

    @SuppressWarnings("unused") // Needed for Firebase link/Recycler View
    public void setUsername(String username) {
        this.username = username;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void translateText(String langCode) {
        if (!isTranslated) {
            text = messageTranslator.translateText(text, langCode);
            isTranslated = true;
        }
    }
}
