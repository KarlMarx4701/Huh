package edu.bklawsonbsu.huh.tests;

import org.junit.Assert;
import org.junit.Test;

import edu.bklawsonbsu.huh.translationClasses.YandexLinkBuilder;

public class LinkTest {
    private YandexLinkBuilder yandexLinkBuilder = new YandexLinkBuilder();

    @Test
    public void testGetLink_NoTextNoLanguage() {
        String testLink = yandexLinkBuilder.getLink("", "");
        Assert.assertEquals("", testLink);
    }

    @Test
    public void testGetLink_NoTextDeLanguage() {
        String testLink = yandexLinkBuilder.getLink("", "de");
        Assert.assertEquals("", testLink);
    }

    @Test
    public void testGetLink_SingleWordNoLanguage() {
        String testLink = yandexLinkBuilder.getLink("Ben", "");
        Assert.assertEquals("", testLink);
    }

    @Test
    public void testGetLink_SingleWordDeLanguage() {
        String testLink = yandexLinkBuilder.getLink("Ben", "de");
        Assert.assertEquals(testLink.substring(139), "&text=Ben&lang=de&[format=plain]&[options=1]");
    }

    @Test
    public void testGetLink_FiveWordsDeLanguage() {
        String testLink = yandexLinkBuilder.getLink("This is my first test", "de");
        Assert.assertEquals(testLink.substring(139), "&text=This+is+my+first+test&lang=de&[format=plain]&[options=1]");
    }

    @Test
    public void testGetLink_FiveWordsPunctuationDeLanguage() {
        String testLink = yandexLinkBuilder.getLink("This, i's my! first? test&", "de");
        Assert.assertEquals(testLink.substring(139), "&text=This%2C+i%27s+my%21+first%3F+test%26&lang=de&[format=plain]&[options=1]");
    }

    @Test
    public void testGetLink_SymbolicTextEnLanguage() {
        String testLink = yandexLinkBuilder.getLink("Это тест, русский язык!", "en");
        Assert.assertEquals(testLink.substring(139), "&text=%D0%AD%D1%82%D0%BE+%D1%82%D0%B5%D1%81%D1%82%2C+%D1%80%D1%83%D1%81%D1%81%D0%BA%D0%B8%D0%B9+%D1%8F%D0%B7%D1%8B%D0%BA%21&lang=en&[format=plain]&[options=1]");
    }
}
