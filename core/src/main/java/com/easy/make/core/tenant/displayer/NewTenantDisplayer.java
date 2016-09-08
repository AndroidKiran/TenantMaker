package com.easy.make.core.tenant.displayer;

import com.easy.make.core.tenant.data.model.Tenant;

/**
 * Created by ravi on 04/09/16.
 */
public interface NewTenantDisplayer {

    void attach(TenantCreationListener tenantCreationListener);

    void detach(TenantCreationListener tenantCreationListener);

    interface TenantCreationListener {

        void onCreateTenantClicked(Tenant tenant);

        void onCancel();

    }
}
