package com.easy.make.tenantmaker.navigation;

import android.app.Activity;
import android.content.Intent;

import com.easy.make.core.navigation.Navigator;
import com.easy.make.core.tenant.data.model.Tenant;
import com.easy.make.tenantmaker.login.LoginActivity;
import com.easy.make.tenantmaker.tenants.CreateTenantActivity;
import com.easy.make.tenantmaker.tenants.TenantListActivity;

public class AndroidNavigator implements Navigator {

    private final Activity activity;

    public AndroidNavigator(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void toTenants() {
        activity.startActivity(new Intent(activity, TenantListActivity.class));
    }

    @Override
    public void toChatWithTenant(Tenant tenant) {
        System.out.println("Item clicked=======  "+tenant.getEmail());

    }

    @Override
    public void toCreateTenant() {
        System.out.println("on create tenant clicked=======  ");
        activity.startActivity(new Intent(activity, CreateTenantActivity.class));
    }

    @Override
    public void toLogin() {
        Intent intent = new Intent(activity, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
        activity.finish();
    }

    @Override
    public void toParent() {
        activity.finish();
    }
}
