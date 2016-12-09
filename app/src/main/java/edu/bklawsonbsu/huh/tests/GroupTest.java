package edu.bklawsonbsu.huh.tests;

import org.junit.Assert;
import org.junit.Test;

import edu.bklawsonbsu.huh.groupClasses.Group;

public class GroupTest {
    private Group testGroup;

    public GroupTest() {
        testGroup = new Group("Business Group", "pictureURL", "lawson470189@gmail.com,bstrayer0@gmail.com", "-a111134", "lawson470189@gmail.com");
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
}


