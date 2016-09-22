package com.easy.make.tenantmaker.base.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.easy.make.tenantmaker.base.BaseActivity;
import com.easy.make.tenantmaker.base.Dependencies;
import com.easy.make.tenantmaker.base.navigation.AndroidNavigator;
import com.easy.make.tenantmaker.core.main.presenter.MainPresenter;

/**
 * Created by ravi on 12/09/16.
 */
public class MainActivity extends BaseActivity {

    private MainPresenter mainPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainPresenter = new MainPresenter(new AndroidNavigator(this), Dependencies.INSTANCE.getPreference());
    }

    @Override
    protected void onStart() {
        super.onStart();
        mainPresenter.startPresenting();
    }

    @Override
    protected void onStop() {
        mainPresenter.stopPresenting();
        super.onStop();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == AndroidNavigator.FIRST_FLOW_RESPONSE_CODE && requestCode == AndroidNavigator.FIRST_FLOW_REQUEST_CODE){
//            mainPresenter.stopPresenting();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
