package com.hdgroup.plantip.Intro;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.hdgroup.plantip.Activity.HomeActivity;
import com.hdgroup.plantip.Helper.MyContextWrapper;
import com.hdgroup.plantip.MainActivity;
import com.hdgroup.plantip.R;
import com.hdgroup.plantip.ui.auth.AuthActivity;


public class LanguageActivity extends AppCompatActivity {

    TextView textView;
    Button E1, M1;
    private String LANG_CURRENT = "en";
    Context context;
    Resources resources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);
        textView = findViewById(R.id.text1);
        E1 = findViewById(R.id.Englishbtn);
        M1 = findViewById(R.id.Marathibtn);


        E1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                context = LocaleHelper.setLocale(LanguageActivity.this, "en");
//                resources = context.getResources();
//                textView.setText(resources.getString(R.string.hello_world));


                if (LANG_CURRENT.equals("mr")) {
                    changeLang(LanguageActivity.this, "en");
                } else {
                    changeLang(LanguageActivity.this, "mr");
                }
                finish();
                startActivity(new Intent(LanguageActivity.this, MainActivity.class));
            }
        });


        M1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (LANG_CURRENT.equals("en")) {
                    changeLang(LanguageActivity.this, "mr");
                } else {
                    changeLang(LanguageActivity.this, "en");
                }
                finish();
                startActivity(new Intent(LanguageActivity.this, MainActivity.class));
            }
        });
    }








    public void changeLang(Context context, String lang) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("Language", lang);
        editor.apply();
    }

    @Override
    protected void attachBaseContext(Context newBase) {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(newBase);
        LANG_CURRENT = preferences.getString("Language", "en");

        super.attachBaseContext(MyContextWrapper.wrap(newBase, LANG_CURRENT));
    }

}




