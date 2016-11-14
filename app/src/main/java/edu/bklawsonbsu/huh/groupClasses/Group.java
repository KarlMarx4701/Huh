package edu.bklawsonbsu.huh.groupClasses;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Group {
    private String groupName;
    private String photoUrl;
    private String users;
    private String key;

    public Group() {

    }

    public Group(String groupName, String photoUrl, String users, String key) {
        this.groupName = groupName;
        this.photoUrl = photoUrl;
        this.users = users;
        this.key = key;
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

}
