package com.lingkai.mymusic.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lingkai.mymusic.R;

public class RegisterActivity extends BaseTitleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    @Override
    protected void initViews() {
        super.initViews();
        enableBackMenu();
    }
}
