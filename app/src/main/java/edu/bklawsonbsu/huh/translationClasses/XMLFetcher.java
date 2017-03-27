package edu.bklawsonbsu.huh.translationClasses;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

@SuppressWarnings("WeakerAccess") //Inspection Problems
public class XMLFetcher {

    public Document getXMLDocument(String linkText) { //Takes string and sends to XML
        Document document = null;
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            document = documentBuilder.parse(new URL(linkText).openStream());
        } catch (ParserConfigurationException | IOException | SAXException ignored) {
        }
        return document;
    }
}
