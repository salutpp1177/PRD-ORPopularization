package com.example.orgame;

import android.app.Application;
import android.content.Context;

import com.example.orgame.helper.LocaleHelper;

public class MyApplication extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base, "en"));
    }
}
