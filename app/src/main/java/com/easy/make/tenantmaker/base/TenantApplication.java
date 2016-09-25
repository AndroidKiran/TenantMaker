package com.easy.make.tenantmaker.base;

import android.support.multidex.MultiDexApplication;

import java.util.Locale;

/**
 * Created by ravi on 28/06/16.
 */
public class TenantApplication extends MultiDexApplication {

    private static TenantApplication sInstance;
    private Locale current;
//    private AppPreferences appPreferences;


    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
//        appPreferences = new AppPreferences(this);
    }

    public static synchronized TenantApplication getInstance() {
        return sInstance;
    }

    public Locale getLocale() {
        if (current == null) {
            current = Locale.US;
        }
        return current;
    }

//    public AppPreferences getAppPreferences() {
//        return appPreferences;
//    }
}
