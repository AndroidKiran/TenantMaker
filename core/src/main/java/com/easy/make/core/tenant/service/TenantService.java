package com.easy.make.core.tenant.service;


import com.easy.make.core.database.DatabaseResult;
import com.easy.make.core.tenant.data.model.Tenant;
import com.easy.make.core.tenant.data.model.Tenants;
import com.easy.make.core.user.data.model.User;

import rx.Observable;

/**
 * Created by ravi on 23/08/16.
 */
public interface TenantService {

    Observable<DatabaseResult<Tenants>> getTenantsFor(User user);

    Observable<Tenants> getTenants(User user);

    Observable<DatabaseResult<Tenant>> createNewTenant(Tenant newTenant);

}
