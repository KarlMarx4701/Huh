package edu.bklawsonbsu.huh.sourceFiles.changeLanguageClasses;

public class Language {
    private String languageName;
    private String languageCode;

    public Language(String languageName, String languageCode) {
        this.languageName = languageName;
        this.languageCode = languageCode;
    }

    public String getLanguageName() {
        return languageName;
    }

    public String getLanguageCode() {
        return languageCode;
    }
}
