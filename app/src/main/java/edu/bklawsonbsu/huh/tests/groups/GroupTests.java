package edu.bklawsonbsu.huh.tests.groups;

import org.junit.Assert;
import org.junit.Test;

import edu.bklawsonbsu.huh.sourceFiles.groupClasses.Group;

public class GroupTests {
    private Group testGroup;

    public GroupTests() {
        testGroup = new Group("Business Group", "pictureURL", "lawson470189@gmail.com,bstrayer0@gmail.com", "-a111134", "#f48c42", "lawson470189@gmail.com");
    }

    @Test
    public void testGroup_getName() {
        Assert.assertEquals(testGroup.getGroupName(), "Business Group");
    }

    @Test
    public void testGroup_getPhotoURL() {
        Assert.assertEquals(testGroup.getPhotoUrl(), "pictureURL");
    }

    @Test
    public void testGroup_getUsers() {
        Assert.assertEquals(testGroup.getUsers(), "lawson470189@gmail.com,bstrayer0@gmail.com");
    }

    @Test
    public void testGroup_getKey() {
        Assert.assertEquals(testGroup.getKey(), "-a111134");
    }

    @Test
    public void testGroup_isAllowedTrueLawson() {
        String testEmail = "lawson470189@gmail.com";
        Assert.assertEquals(testGroup.isAllowed(testEmail), true);
    }

    @Test
    public void testGroup_isAllowedTrueStrayer() {
        String testEmail = "bstrayer0@gmail.com";
        Assert.assertEquals(testGroup.isAllowed(testEmail), true);
    }

    @Test
    public void testGroup_isAllowedFalse() {
        String testEmail = "developers@gmail.com";
        Assert.assertEquals(testGroup.isAllowed(testEmail), false);
    }

    @Test
    public void testGroup_getColor() {
        Assert.assertEquals(testGroup.getColor(), "#f48c42");
    }

    @Test
    public void testGroup_getOwner() {
        Assert.assertEquals(testGroup.getOwner(), "lawson470189@gmail.com");
    }
}


