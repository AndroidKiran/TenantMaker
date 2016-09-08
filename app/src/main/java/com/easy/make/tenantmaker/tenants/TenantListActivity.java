package com.easy.make.tenantmaker.tenants;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.easy.make.core.tenant.displayer.TenantsDisplayer;
import com.easy.make.core.tenant.presenter.TenantPresenter;
import com.easy.make.tenantmaker.BaseActivity;
import com.easy.make.tenantmaker.Dependencies;
import com.easy.make.tenantmaker.R;
import com.easy.make.tenantmaker.navigation.AndroidNavigator;

/**
 * Created by ravi on 29/08/16.
 */
public class TenantListActivity extends BaseActivity {

    private TenantPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenants);
        TenantsDisplayer tenantsDisplayer = (TenantsDisplayer) findViewById(R.id.tenants_view);

        presenter = new TenantPresenter(
                Dependencies.INSTANCE.getLoginService(),
                Dependencies.INSTANCE.getTenantService(),
                tenantsDisplayer,
                Dependencies.INSTANCE.getAnalytics(),
                new AndroidNavigator(this),
                Dependencies.INSTANCE.getErrorLogger()
        );
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.startPresenting();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.stopPresenting();
    }
}
