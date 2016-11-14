package edu.bklawsonbsu.huh.translationClasses;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class XMLFetcher {

    public Document getXMLDocument(String linkText) {
        Document document = null;
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            document = documentBuilder.parse(new URL(linkText).openStream());
        } catch (ParserConfigurationException e) {
        } catch (MalformedURLException e) {
        } catch (SAXException e) {
        } catch (IOException e) {
        }
        return document;
    }
}
