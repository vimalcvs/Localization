package com.vimalcvs.localization;

import android.app.Application;
import android.content.Context;

import java.util.Locale;

public class MyApplication extends Application {

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(LocaleHelper.onAttach(context, Locale.getDefault().getLanguage()));
    }
}