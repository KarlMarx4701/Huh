package edu.bklawsonbsu.huh.groupClasses;

import java.util.ArrayList;
import java.util.Map;

import edu.bklawsonbsu.huh.messageClasses.Message;

@SuppressWarnings("WeakerAccess") // Both must be used for recycler view and connection to the database.
public class Group {
    private String groupName;
    private String photoUrl;
    private String users;
    private ArrayList<String> usersAllowedList = new ArrayList<>();
    private Map<String, Message> messages;
    private String key;
    private String owner;

    @SuppressWarnings("unused") //Empty Constructor must be in the class for recycler view.
    public Group() {}

    public Group(String groupName, String photoUrl, String users, String key, String owner) {
        this.groupName = groupName;
        this.photoUrl = photoUrl;
        this.users = users;
        this.key = key;
        this.owner = owner;
    }

    public void splitUsers() {
        String[] tempArr = users.split(",");
        for (String item: tempArr) {
            usersAllowedList.add(item.toLowerCase());
        }
    }

    public String getGroupName() {
        return groupName;
    }

    @SuppressWarnings("unused")
    public String getPhotoUrl() {
        return photoUrl;
    }

    public String getUsers() {
        return users;
    }

    public String getKey() {
        return key;
    }

    public String getOwner() {
        return owner;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @SuppressWarnings("unused") // Unused is for Firebase data binding. These will be utilized by the program.
    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public void setUsers(String userString) {
        this.users = userString;
    }

    @SuppressWarnings("unused")
    public void setKey(String key) {
        this.key = key;
    }

    @SuppressWarnings("unused")
    public void setOwner(String owner) {
        this.owner = owner;
    }

    public boolean isAllowed(String email) {
        splitUsers();
        return usersAllowedList.contains(email);
    }

    @SuppressWarnings("unused")
    public void setMessages(Map<String, Message> messages) {
        this.messages = messages;
    }

    @SuppressWarnings("unused")
    public Map<String, Message> getMessages() {
        return messages;
    }

}
