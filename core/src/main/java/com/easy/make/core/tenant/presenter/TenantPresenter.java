package com.easy.make.core.tenant.presenter;


import com.easy.make.core.analytics.Analytics;
import com.easy.make.core.analytics.ErrorLogger;
import com.easy.make.core.database.DatabaseResult;
import com.easy.make.core.login.data.model.Authentication;
import com.easy.make.core.login.service.LoginService;
import com.easy.make.core.navigation.Navigator;
import com.easy.make.core.tenant.data.model.Tenant;
import com.easy.make.core.tenant.data.model.Tenants;
import com.easy.make.core.tenant.displayer.TenantsDisplayer;
import com.easy.make.core.tenant.service.TenantService;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by ravi on 23/08/16.
 */
public class TenantPresenter {

    private final LoginService loginService;
    private final TenantService tenantService;
    private final TenantsDisplayer tenantsDisplayer;
    private final Analytics analytics;
    private final Navigator navigator;
    private final ErrorLogger errorLogger;

    private Subscription subscription;

    public TenantPresenter(
            LoginService loginService,
            TenantService tenantService,
            TenantsDisplayer tenantsDisplayer,
            Analytics analytics,
            Navigator navigator,
            ErrorLogger errorLogger
    ) {
        this.loginService = loginService;
        this.tenantService = tenantService;
        this.tenantsDisplayer = tenantsDisplayer;
        this.analytics = analytics;
        this.navigator = navigator;
        this.errorLogger = errorLogger;
    }

    public void startPresenting() {
        tenantsDisplayer.attach(tenantInteractionListener);
//        subscription = loginService.getAuthentication()
//                .filter(successfullyAuthenticated())
//                .flatMap(tenantsForUser())
//                .subscribe(new Action1<DatabaseResult<Tenants>>() {
//                    @Override
//                    public void call(DatabaseResult<Tenants> tenantsDatabaseResult) {
//                        System.out.println("sdljjsd"+tenantsDatabaseResult.getData());
//                    }
//                });

        subscription = loginService.getAuthentication()
                .filter(successfullyAuthenticated())
                .flatMap(getTenantsForUser())
                .subscribe(new Action1<Tenants>() {
                    @Override
                    public void call(Tenants tenants) {
                        System.out.println(""+tenants.size());
                        tenantsDisplayer.display(tenants);
                    }
                });
    }

    public void stopPresenting() {
        subscription.unsubscribe();
        tenantsDisplayer.detach(tenantInteractionListener);
    }

    private final TenantsDisplayer.TenantInteractionListener tenantInteractionListener = new TenantsDisplayer.TenantInteractionListener() {
        @Override
        public void onTenantSelected(Tenant tenant) {
            navigator.toChatWithTenant(tenant);
        }

        @Override
        public void onCreateTenant() {
            navigator.toCreateTenant();
        }
    };

    private Func1<Authentication, Boolean> successfullyAuthenticated() {
        return new Func1<Authentication, Boolean>() {
            @Override
            public Boolean call(Authentication authentication) {
                return authentication.isSuccess();
            }
        };
    }

    private Func1<Authentication, Observable<DatabaseResult<Tenants>>> tenantsForUser() {
        return new Func1<Authentication, Observable<DatabaseResult<Tenants>>>() {
            @Override
            public Observable<DatabaseResult<Tenants>> call(Authentication authentication) {
                return tenantService.getTenantsFor(authentication.getUser());
            }
        };
    }


    private Func1<Authentication, Observable<Tenants>> getTenantsForUser(){
        return new Func1<Authentication, Observable<Tenants>>() {
            @Override
            public Observable<Tenants> call(Authentication authentication) {
                return tenantService.getTenants(authentication.getUser());
            }
        };
    }
}
