package edu.bklawsonbsu.huh.changeLanguageClasses;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import edu.bklawsonbsu.huh.R;

@SuppressWarnings("WeakerAccess") // Inspection Warning
public class LanguageFileParser {
    private Context context;

    public LanguageFileParser(Context context) {
        this.context = context;
    }

    public ArrayList<Language> parseDocument() {
        ArrayList<Language> returnList = new ArrayList<>();
        InputStream fileInputStream = context.getResources().openRawResource(R.raw.language_list);
        BufferedReader fileReader = new BufferedReader(new InputStreamReader(fileInputStream));
        String line;
        try {
            while (null != (line = fileReader.readLine())) {
                String[] lineSplit = line.split(",");
                returnList.add(new Language(lineSplit[0], lineSplit[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnList;
    }
}
