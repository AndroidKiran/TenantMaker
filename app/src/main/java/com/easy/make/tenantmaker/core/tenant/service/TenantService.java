package com.easy.make.tenantmaker.core.tenant.service;


import com.easy.make.tenantmaker.core.database.DatabaseResult;
import com.easy.make.tenantmaker.core.tenant.data.model.Tenant;
import com.easy.make.tenantmaker.core.tenant.data.model.Tenants;
import com.easy.make.tenantmaker.core.user.data.model.User;

import rx.Observable;

/**
 * Created by ravi on 23/08/16.
 */
public interface TenantService {

    Observable<Tenants> getTenants(User user);

    Observable<Tenants> getTenants(User user, String query);

    Observable<DatabaseResult<Tenant>> createNewTenant(Tenant newTenant, User user);

}
