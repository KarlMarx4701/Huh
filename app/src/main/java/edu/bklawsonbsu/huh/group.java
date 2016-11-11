package edu.bklawsonbsu.huh;

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
    private List<String> usersList;

    public Group() {

    }

    public Group(String groupName, String photoUrl, String users) {
        this.groupName = groupName;
        this.photoUrl = photoUrl;
        this.users = users;
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

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public void setUsers(String userString) {
        this.users = userString;
    }

}
