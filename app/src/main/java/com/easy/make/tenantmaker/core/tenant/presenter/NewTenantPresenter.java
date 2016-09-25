package com.easy.make.tenantmaker.core.tenant.presenter;

import com.easy.make.tenantmaker.core.analytics.ErrorLogger;
import com.easy.make.tenantmaker.core.database.DatabaseResult;
import com.easy.make.tenantmaker.core.login.data.model.Authentication;
import com.easy.make.tenantmaker.core.login.service.LoginService;
import com.easy.make.tenantmaker.core.navigation.Navigator;
import com.easy.make.tenantmaker.core.tenant.data.model.Tenant;
import com.easy.make.tenantmaker.core.tenant.displayer.NewTenantDisplayer;
import com.easy.make.tenantmaker.core.tenant.service.TenantService;
import com.easy.make.tenantmaker.core.user.data.model.User;

import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by ravi on 04/09/16.
 */
public class NewTenantPresenter {


    private final NewTenantDisplayer newTenantDisplayer;
    private final TenantService tenantService;
    private final LoginService loginService;
    private final Navigator navigator;
    private final ErrorLogger errorLogger;
    private CompositeSubscription subscriptions = new CompositeSubscription();
    private User user;


    public NewTenantPresenter(NewTenantDisplayer newTenantDisplayer,
                              TenantService tenantService,
                              LoginService loginService,
                              Navigator navigator,
                              ErrorLogger errorLogger) {
        this.newTenantDisplayer = newTenantDisplayer;
        this.tenantService = tenantService;
        this.loginService = loginService;
        this.navigator = navigator;
        this.errorLogger = errorLogger;
    }


    public void startPresenting(){
        newTenantDisplayer.attach(tenantCreationListener);
        subscriptions.add(loginService.getAuthentication().subscribe(new Action1<Authentication>() {
            @Override
            public void call(Authentication authentication) {
                user = authentication.getUser();
            }
        }));
    }

    public void stopPresenting() {
        newTenantDisplayer.detach(tenantCreationListener);
        subscriptions.clear();
        subscriptions = new CompositeSubscription();
    }

    private final NewTenantDisplayer.TenantCreationListener tenantCreationListener = new NewTenantDisplayer.TenantCreationListener() {
        @Override
        public void onCreateTenantClicked(Tenant tenant) {
            newTenantDisplayer.showProgress();
            tenantService.createNewTenant(tenant, user).subscribe(new Action1<DatabaseResult<Tenant>>() {
                @Override
                public void call(DatabaseResult<Tenant> tenantDatabaseResult) {
                    newTenantDisplayer.dismissProgress();
                }
            });
        }

        @Override
        public void onCancel() {
            navigator.toParent();
        }
    };
}
