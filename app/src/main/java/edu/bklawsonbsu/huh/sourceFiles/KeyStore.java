package edu.bklawsonbsu.huh.sourceFiles;

public class KeyStore {
    private static String key;
    private static String groupName;

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
}
