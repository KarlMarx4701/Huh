package edu.bklawsonbsu.huh.translation;

import android.os.Process;
import android.util.Log;

import org.w3c.dom.Document;

public class Translator {
    private static YandexLinkBuilder linkBuilder;
    private static XMLFetcher dataFetcher;
    private static String result;
    private static String link;
    private Thread thread;

    public Translator() {
        linkBuilder = new YandexLinkBuilder();
        dataFetcher = new XMLFetcher();

        thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try  {
                    result = "";
                    Document xmlDoc = dataFetcher.getXMLDocument(link);
                    XMLParser parser = new XMLParser(xmlDoc);
                    result = parser.getTranslation();
                    Log.d("Translation", "Got data! " + result);
                    thread.interrupt();
                } catch (Exception e) {
                    result = "";
                    Log.d("Translation", "No Data!");
                    e.printStackTrace();
                    thread.interrupt();
                }
            }
        });

        thread.setPriority(Process.THREAD_PRIORITY_BACKGROUND);
    }

    public String translateText(String text, String languageCode) {
        link = linkBuilder.getLink(text, languageCode);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;

    }
}
