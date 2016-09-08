package com.easy.make.core.tenant.displayer;

import com.easy.make.core.tenant.data.model.Tenant;
import com.easy.make.core.tenant.data.model.Tenants;

/**
 * Created by ravi on 23/08/16.
 */
public interface TenantsDisplayer {

    void display(Tenants tenants);

    void attach(TenantInteractionListener tenantInteractionListener);

    void detach(TenantInteractionListener tenantInteractionListener);

    interface TenantInteractionListener {
        void onTenantSelected(Tenant tenant);

        void onCreateTenant();
    }
}
