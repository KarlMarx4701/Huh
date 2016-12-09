package edu.bklawsonbsu.huh.tests;

import junit.framework.Assert;

import org.junit.Test;

import edu.bklawsonbsu.huh.changeLanguageClasses.Language;

public class LanguageTest {
    private Language testLanguage = new Language("Afrikaans", "af");

    @Test
    public void languageTest_getLanguageName() {
        Assert.assertEquals(testLanguage.getLanguageName(), "Afrikaans");
    }

    @Test
    public void languageTest_getLanguageCode() {
        Assert.assertEquals(testLanguage.getLanguageCode(), "af");
    }
}
