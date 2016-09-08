package com.easy.make.tenantmaker.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ravi on 12/08/16.
 */
public class AppPreferences {

    private final static String PREFS_FILE = "app_prefs";
    private SharedPreferences prefs;

    private final static String KEY_CACHE_LOCATION = "key_cache_location";

    public AppPreferences(Context context) {
        prefs = context.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
    }

    public void setCacheLocation(int cacheLocation) {
        prefs.edit().putInt(KEY_CACHE_LOCATION, cacheLocation).commit();
    }

    public int getCacheLocation() {
        return prefs.getInt(KEY_CACHE_LOCATION, 0);
    }
}
