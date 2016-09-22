package com.easy.make.tenantmaker.core.tenant.database;


import com.easy.make.tenantmaker.core.tenant.data.model.Tenant;
import com.easy.make.tenantmaker.core.tenant.data.model.Tenants;
import com.easy.make.tenantmaker.core.user.data.model.User;

import rx.Observable;

/**
 * Created by ravi on 23/08/16.
 */
public interface TenantDatabase {

    Observable <Tenants> observeTenantsFor(User user);

    Observable <Tenants> observeTenantsFor(User user, String query);

    Observable<Tenant> writeTenant(Tenant newTenant, User user);

}
