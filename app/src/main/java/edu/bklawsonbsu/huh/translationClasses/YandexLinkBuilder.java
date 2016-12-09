package edu.bklawsonbsu.huh.translationClasses;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@SuppressWarnings("WeakerAccess") //Inspection Problems
public class YandexLinkBuilder {
    private final String yandexKey = "trnsl.1.1.20160928T164006Z.7e589f335c3a577d.7040cb30d2a8f3deb920e0fd3a915cf67411f120";

    public String getLink(String text, String languageCode) {
        if (!(text.equals("")) && !(languageCode.equals(""))) {
            return "https://translate.yandex.net/api/v1.5/tr/translate?key=" + yandexKey + "&text=" + formatText(text) + "&lang=" + languageCode + "&[format=plain]&[options=1]";
        }
        return "";
    }

    @SuppressWarnings("ObjectEqualsNull") //Checking for null object.
    public String formatText(String text) {
        if (!text.equals(null)) {
            try {
                text = URLEncoder.encode(text, "UTF-8");
                return text;
            } catch (UnsupportedEncodingException e) {
                return text;
            }
        } else {
            return "";
        }
    }
}
