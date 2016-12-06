package edu.bklawsonbsu.huh.sourceFiles;

public class KeyStore {
    private static String key;
    private static String groupName;
    private static String groupColor;
    private static String languageAbbr = "en";

    public KeyStore() {

    }

    public void setKey(String key) {
        KeyStore.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setGroupName(String name) {
        groupName = name;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setColor(String color) {
        groupColor = color;
    }

    public String getColor() {
        return groupColor;
    }

    public String getLanguageAbbr() {
        return languageAbbr;
    }

    public void setLanguageAbbr(String languageAbbr) {
        this.languageAbbr = languageAbbr;
    }

}
