package com.easy.make.core.tenant.service;

import com.easy.make.core.database.DatabaseResult;
import com.easy.make.core.tenant.data.model.Tenant;
import com.easy.make.core.tenant.data.model.Tenants;
import com.easy.make.core.tenant.database.TenantDatabase;
import com.easy.make.core.user.data.model.User;

import java.util.List;

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
    public Observable<DatabaseResult<Tenants>> getTenantsFor(User user) {
        return tenantDatabase.observeTenantIdsFor(user)
                .flatMap(getTenantsFromIds(user))
                .onErrorReturn(DatabaseResult.<Tenants>errorAsDatabaseResult());
    }

    @Override
    public Observable<Tenants> getTenants(User user) {
        return tenantDatabase.observeTenantsFor(user);
    }

    @Override
    public Observable<DatabaseResult<Tenant>> createNewTenant(Tenant newTenant) {
        return tenantDatabase.writeTenant(newTenant)
                .map(new Func1<Tenant, DatabaseResult<Tenant>>() {
                    @Override
                    public DatabaseResult<Tenant> call(Tenant tenant) {
                        return new DatabaseResult<>(tenant);
                    }
                });
    }


    private Func1<List<String>, Observable<DatabaseResult<Tenants>>> getTenantsFromIds(final User user) {
        return new Func1<List<String>, Observable<DatabaseResult<Tenants>>>() {
            @Override
            public Observable<DatabaseResult<Tenants>> call(List<String> uids) {
                return Observable.from(uids)
                        .flatMap(getTenantFromId(user))
                        .toList()
                        .map(new Func1<List<Tenant>, DatabaseResult<Tenants>>() {
                            @Override
                            public DatabaseResult<Tenants> call(List<Tenant> tenants) {
                                System.out.println("flhfdufdhg"+tenants.get(0).getAddress());
                                return new DatabaseResult<Tenants>(new Tenants(tenants));
                            }
                        });
            }
        };
    }

    private Func1<String, Observable<Tenant>> getTenantFromId(final User user) {
        return new Func1<String, Observable<Tenant>>() {
            @Override
            public Observable<Tenant> call(final String uid) {
                return tenantDatabase.readTenantFrom(uid, user);
            }
        };
    }

}




