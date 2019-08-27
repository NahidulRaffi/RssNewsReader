package com.androhungers.rssnewsreader.common;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class PreferenceHelper {
    String PREFS_NAME = "RSS";
    private SharedPreferences preference;
    private SharedPreferences.Editor editor;

    public PreferenceHelper(Context context) {
        preference = context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        editor = preference.edit();
    }

    public String getString(String field){
        return preference.getString(field,"");
    }

    public void saveString(String field, String value){
        editor.putString(field,value);
        editor.commit();
    }
}
