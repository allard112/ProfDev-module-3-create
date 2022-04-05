package com.example.therapieapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.text.method.DialerKeyListener;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class Settings extends AppCompatActivity {

    private Button openPrivacy;
    private Button openAbout;
    private Button openSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        openAbout = findViewById(R.id.about);
        openAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAboutDialog();
            }
        });

        openPrivacy = findViewById(R.id.privacy);
        openPrivacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPrivacyDialog();
            }
        });
    }

    void showPrivacyDialog() {
        final Dialog dialog = new Dialog(Settings.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.privacy_dialog);
        dialog.show();

    }
    void showAboutDialog() {
        final Dialog dialog = new Dialog(Settings.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.about_dialog);
        dialog.show();
    }
}