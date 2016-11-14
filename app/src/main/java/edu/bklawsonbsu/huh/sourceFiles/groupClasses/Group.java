package edu.bklawsonbsu.huh.sourceFiles.groupClasses;

import java.util.ArrayList;

public class Group {
    private String groupName;
    private String photoUrl;
    private String users;
    private ArrayList<String> usersAllowedList;
    private String key;

    public Group() {}

    public Group(String groupName, String photoUrl, String users, String key) {
        this.groupName = groupName;
        this.photoUrl = photoUrl;
        this.users = users;
        this.key = key;
        usersAllowedList = new ArrayList<>();
        splitUsers();
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

    public boolean isAllowed(String email) {
        return usersAllowedList.contains(email);
    }

}
