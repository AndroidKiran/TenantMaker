package com.easy.make.tenantmaker.tenants;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.easy.make.core.tenant.displayer.TenantsDisplayer;
import com.easy.make.core.tenant.presenter.TenantPresenter;
import com.easy.make.tenantmaker.BaseActivity;
import com.easy.make.tenantmaker.Dependencies;
import com.easy.make.tenantmaker.R;
import com.easy.make.tenantmaker.navigation.AndroidNavigator;
import com.easy.make.tenantmaker.utils.UtilBundles;

/**
 * Created by ravi on 29/08/16.
 */
public class TenantListActivity extends BaseActivity {

    private TenantPresenter presenter;
    private Bundle args;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenants);

        if(savedInstanceState == null){
            args = getIntent().getExtras();
        } else {
            args = savedInstanceState.getBundle(UtilBundles.EXTRA_BUNDLE);
        }

        TenantsDisplayer tenantsDisplayer = (TenantsDisplayer) findViewById(R.id.tenants_view);

        presenter = new TenantPresenter(
                Dependencies.INSTANCE.getLoginService(),
                Dependencies.INSTANCE.getTenantService(),
                tenantsDisplayer,
                Dependencies.INSTANCE.getAnalytics(),
                new AndroidNavigator(this),
                Dependencies.INSTANCE.getErrorLogger(),
                args.getString(UtilBundles.EXTRA_TEXT),
                true
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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putBundle(UtilBundles.EXTRA_BUNDLE, args);
        super.onSaveInstanceState(outState);
    }
}
