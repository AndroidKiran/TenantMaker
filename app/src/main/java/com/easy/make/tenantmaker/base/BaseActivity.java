package com.easy.make.tenantmaker.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.easy.make.tenantmaker.BuildConfig;
import com.novoda.notils.logger.simple.Log;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.setShowLogs(BuildConfig.DEBUG);
        Dependencies.INSTANCE.init(this);
    }
}
