package com.easy.make.tenantmaker.tenants;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.View;

import com.easy.make.core.tenant.displayer.NewTenantDisplayer;
import com.easy.make.core.tenant.presenter.NewTenantPresenter;
import com.easy.make.tenantmaker.BaseActivity;
import com.easy.make.tenantmaker.Dependencies;
import com.easy.make.tenantmaker.R;
import com.easy.make.tenantmaker.navigation.AndroidNavigator;
import com.easy.make.tenantmaker.tenants.view.NewTenantView;
import com.easy.make.tenantmaker.utils.DateTimeUtils;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;


/**
 * Created by ravi on 04/09/16.
 */
public class CreateTenantActivity extends BaseActivity {

    private NewTenantPresenter newTenantPresenter;
    private NewTenantView newTenantView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_tenant);
        newTenantView = (NewTenantView) findViewById(R.id.create_new_tenant);
        newTenantView.setAppCompatActivity(this);
        newTenantPresenter = new NewTenantPresenter((NewTenantDisplayer) newTenantView,
                Dependencies.INSTANCE.getTenantService(),
                Dependencies.INSTANCE.getLoginService(),
                new AndroidNavigator(this),
                Dependencies.INSTANCE.getErrorLogger());

    }


    @Override
    protected void onStart() {
        super.onStart();
        newTenantPresenter.startPresenting();
    }

    @Override
    protected void onStop() {
        newTenantPresenter.stopPresenting();
        super.onStop();
    }

}
