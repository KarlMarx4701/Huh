package edu.bklawsonbsu.huh.changeLanguageClasses;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import edu.bklawsonbsu.huh.R;
import edu.bklawsonbsu.huh.StaticGroupHolder;

public class SelectLanguage extends AppCompatActivity {
    private ArrayList<Language> languageList;
    private String selectedLanguageAbbr;
    private StaticGroupHolder staticGroupHolder = new StaticGroupHolder();
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_language);
        context = this;
        languageList = new LanguageFileParser(context).parseDocument();
        populateLangList();
        initializeConfirmButton();
    }

    public void populateLangList() {
        final LinearLayout langList = (LinearLayout) findViewById(R.id.languagesList);
        final TextView selectedTextView = (TextView) findViewById(R.id.selectedLanguage);
        for (final Language language: languageList) {
            final TextView textView = new TextView(context);
            textView.setText(language.getLanguageName());
            textView.setTextSize(18);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    resetColors();
                    textView.setBackgroundColor(Color.rgb(175, 206, 255));
                    selectedTextView.setText(language.getLanguageName());
                    selectedLanguageAbbr = language.getLanguageCode();
                }
            });
            langList.addView(textView);
        }
    }

    public void resetColors() {
        final LinearLayout langList = (LinearLayout) findViewById(R.id.languagesList);
        for (int i = 0; i < langList.getChildCount(); i++) {
            langList.getChildAt(i).setBackgroundColor(Color.rgb(255, 255, 255));
        }
    }

    private void initializeConfirmButton() {
        Button confirmButton = (Button) findViewById(R.id.selectLang);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                staticGroupHolder.setLanguageAbbr(selectedLanguageAbbr);
                finish();
            }
        });
    }
}
