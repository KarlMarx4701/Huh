package edu.bklawsonbsu.huh.translationClasses;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

@SuppressWarnings("WeakerAccess")
public class XMLParser {
    private Document xmlDocument;

    public XMLParser(Document xmlDocument) {
        this.xmlDocument = xmlDocument;
    }


    public String getTranslation() {
        Element translation = getTranslateElement();
        String translatedText = translation.getTextContent();
        return formatTranslatedText(translatedText);
    }

    public Element getTranslateElement() {
        xmlDocument.getDocumentElement().normalize();
        NodeList nodeList = xmlDocument.getElementsByTagName("text");
        return (Element) nodeList.item(0);
    }

    public String formatTranslatedText(String text) {
        return text.replaceAll("\n"," ");
    }

    public String getLanguage() {
        Element translation = getLangElement();
        return translation.getAttribute("lang");
    }

    public Element getLangElement() {
        xmlDocument.getDocumentElement().normalize();
        NodeList translations = xmlDocument.getElementsByTagName("Translation");
        return (Element) translations.item(0);
    }
}
