package com.easy.make.tenantmaker.core.tenant.service;

import com.easy.make.tenantmaker.core.database.DatabaseResult;
import com.easy.make.tenantmaker.core.tenant.data.model.Tenant;
import com.easy.make.tenantmaker.core.tenant.data.model.Tenants;
import com.easy.make.tenantmaker.core.tenant.database.TenantDatabase;
import com.easy.make.tenantmaker.core.user.data.model.User;

import rx.Observable;
import rx.functions.Func1;


/**
 * Created by ravi on 23/08/16.
 */
public class PersistedTenantService implements TenantService {

    private final TenantDatabase tenantDatabase;

    public PersistedTenantService(TenantDatabase tenantDatabase) {
        this.tenantDatabase = tenantDatabase;
    }

    @Override
    public Observable<Tenants> getTenants(User user) {
        return tenantDatabase.observeTenantsFor(user);
    }

    @Override
    public Observable<Tenants> getTenants(User user, String query) {
        return tenantDatabase.observeTenantsFor(user, query);
    }

    @Override
    public Observable<DatabaseResult<Tenant>> createNewTenant(Tenant newTenant, User user) {
        return tenantDatabase.writeTenant(newTenant, user)
                .map(new Func1<Tenant, DatabaseResult<Tenant>>() {
                    @Override
                    public DatabaseResult<Tenant> call(Tenant tenant) {
                        return new DatabaseResult<>(tenant);
                    }
                });
    }
}




