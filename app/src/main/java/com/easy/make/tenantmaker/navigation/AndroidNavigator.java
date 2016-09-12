package com.easy.make.tenantmaker.navigation;

import android.app.Activity;
import android.content.Intent;

import com.easy.make.core.navigation.Navigator;
import com.easy.make.core.tenant.data.model.Tenant;
import com.easy.make.tenantmaker.flat.NewFlatActivity;
import com.easy.make.tenantmaker.login.LoginActivity;
import com.easy.make.tenantmaker.home.HomeActivity;
import com.easy.make.tenantmaker.tenants.CreateTenantActivity;
import com.easy.make.tenantmaker.tenants.TenantListActivity;
import com.easy.make.tenantmaker.utils.UtilBundles;

public class AndroidNavigator implements Navigator {

    private final Activity activity;
    public static  final int FIRST_FLOW_REQUEST_CODE = 001;
    public static  final int FIRST_FLOW_RESPONSE_CODE = 002;

    public AndroidNavigator(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void toHome() {
        activity.startActivity(new Intent(activity, HomeActivity.class));
        activity.finish();
    }

    @Override
    public void toTenants() {
        activity.startActivityForResult(new Intent(activity, TenantListActivity.class), FIRST_FLOW_REQUEST_CODE);
    }

    @Override
    public void toTenants(String query) {
        Intent intent = new Intent(activity, TenantListActivity.class);
        intent.putExtra(UtilBundles.EXTRA_TEXT, query);
        activity.startActivity(intent);
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
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivityForResult(intent, FIRST_FLOW_REQUEST_CODE);
    }

    @Override
    public void toParent() {
        activity.finish();
    }

    @Override
    public void toNewFlat() {
        activity.startActivityForResult(new Intent(activity, NewFlatActivity.class), FIRST_FLOW_REQUEST_CODE);
    }

    @Override
    public void toMain() {
        activity.setResult(FIRST_FLOW_RESPONSE_CODE);
        activity.finish();
    }
}
