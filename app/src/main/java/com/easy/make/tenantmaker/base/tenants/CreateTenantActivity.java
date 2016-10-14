package com.easy.make.tenantmaker.base.tenants;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.easy.make.tenantmaker.R;
import com.easy.make.tenantmaker.base.BaseActivity;
import com.easy.make.tenantmaker.base.Dependencies;
import com.easy.make.tenantmaker.base.navigation.AndroidNavigator;
import com.easy.make.tenantmaker.base.tenants.view.NewTenantView;
import com.easy.make.tenantmaker.core.tenant.displayer.NewTenantDisplayer;
import com.easy.make.tenantmaker.core.tenant.presenter.NewTenantPresenter;


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
                Dependencies.INSTANCE.getFlatService(),
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
