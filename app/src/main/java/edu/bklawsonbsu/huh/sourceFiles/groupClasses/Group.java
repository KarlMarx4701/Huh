package edu.bklawsonbsu.huh.sourceFiles.groupClasses;

import java.util.ArrayList;

@SuppressWarnings({"WeakerAccess", "unused"}) // Both must be used for recycler view and connection to the database.
public class Group {
    private String groupName;
    private String photoUrl;
    private String users;
    private ArrayList<String> usersAllowedList = new ArrayList<>();
    private String key;
    private String color;
    private String owner;

    @SuppressWarnings("unused") //Empty Constructor must be in the class for recycler view.
    public Group() {}

    public Group(String groupName, String photoUrl, String users, String key, String color, String owner) {
        this.groupName = groupName;
        this.photoUrl = photoUrl;
        this.users = users;
        this.key = key;
        this.color = color;
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

    public String getPhotoUrl() {
        return photoUrl;
    }

    public String getUsers() {
        return users;
    }

    public String getKey() {
        return key;
    }

    public String getColor() {
        return color;
    }

    public String getOwner() {
        return owner;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public void setUsers(String userString) {
        this.users = userString;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public boolean isAllowed(String email) {
        splitUsers();
        return usersAllowedList.contains(email);
    }

}
