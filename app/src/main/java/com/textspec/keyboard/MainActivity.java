package com.textspec.keyboard;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Button;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button enableBtn = findViewById(R.id.enableBtn);
        Button selectBtn = findViewById(R.id.selectBtn);
        enableBtn.setOnClickListener(v -> {
            startActivity(new Intent(Settings.ACTION_INPUT_METHOD_SETTINGS));
        });
        selectBtn.setOnClickListener(v -> {
            ((android.view.inputmethod.InputMethodManager) getSystemService(INPUT_METHOD_SERVICE)).showInputMethodPicker();
        });
    }
