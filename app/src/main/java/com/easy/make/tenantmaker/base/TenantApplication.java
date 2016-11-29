package com.easy.make.tenantmaker.base;

import android.support.multidex.MultiDexApplication;

import com.digits.sdk.android.Digits;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;

import java.util.Locale;

import io.fabric.sdk.android.Fabric;

/**
 * Created by ravi on 28/06/16.
 */
public class TenantApplication extends MultiDexApplication {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "nZJq3jOrV3v0nOkvGZQFeJ4of";
    private static final String TWITTER_SECRET = "kUGJDJeS9590o66N4gVi1M9wfi4fCsvF6Mx4yfoziXjYQpOx34";


    private static TenantApplication sInstance;
    private Locale current;
//    private AppPreferences appPreferences;


    @Override
    public void onCreate() {
        super.onCreate();
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new TwitterCore(authConfig), new Digits.Builder().build());
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
