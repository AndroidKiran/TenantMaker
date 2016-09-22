package com.easy.make.tenantmaker.core.tenant.displayer;


import com.easy.make.tenantmaker.core.tenant.data.model.Tenant;

/**
 * Created by ravi on 04/09/16.
 */
public interface NewTenantDisplayer {

    void attach(TenantCreationListener tenantCreationListener);

    void detach(TenantCreationListener tenantCreationListener);

    void showProgress();

    void dismissProgress();

    void clear();

    interface TenantCreationListener {

        void onCreateTenantClicked(Tenant tenant);

        void onCancel();

    }
}
