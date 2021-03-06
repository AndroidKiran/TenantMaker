package com.easy.make.tenantmaker.base.navigation;

import android.content.Intent;

import com.easy.make.tenantmaker.base.BaseActivity;
import com.easy.make.tenantmaker.base.login.LoginGoogleApiClient;
import com.easy.make.tenantmaker.core.navigation.LoginNavigator;
import com.easy.make.tenantmaker.core.navigation.Navigator;
import com.easy.make.tenantmaker.core.tenant.data.model.Tenant;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.novoda.notils.logger.simple.Log;

public class AndroidLoginNavigator implements LoginNavigator {

    private static final int RC_SIGN_IN = 242;

    private final BaseActivity activity;
    private final LoginGoogleApiClient googleApiClient;
    private final Navigator navigator;
    private LoginResultListener loginResultListener;

    public AndroidLoginNavigator(BaseActivity activity, LoginGoogleApiClient googleApiClient, Navigator navigator) {
        this.activity = activity;
        this.googleApiClient = googleApiClient;
        this.navigator = navigator;
    }

    @Override
    public void toHome() {
        navigator.toHome();
        activity.finish();
    }

    @Override
    public void toTenants() {
        navigator.toTenants();
        activity.finish();
    }

    @Override
    public void toTenants(String query) {
        navigator.toTenants(query);
    }

    @Override
    public void toChatWithTenant(Tenant tenant) {
        navigator.toChatWithTenant(tenant);
    }

    @Override
    public void toCreateTenant() {
        navigator.toCreateTenant();
    }

    @Override
    public void toParent() {
        navigator.toParent();
    }

    @Override
    public void toNewFlat() {
        navigator.toNewFlat();
    }

    @Override
    public void toMain() {
        navigator.toMain();
    }

    @Override
    public void toLogin() {
        navigator.toLogin();
    }

    @Override
    public void toGooglePlusLogin() {
        Intent signInIntent = googleApiClient.getSignInIntent();
        activity.startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void attach(LoginResultListener loginResultListener) {
        this.loginResultListener = loginResultListener;
    }

    @Override
    public void detach(LoginResultListener loginResultListener) {
        this.loginResultListener = null;
    }

    public boolean onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode != RC_SIGN_IN) {
            return false;
        }
        GoogleSignInResult result = googleApiClient.getSignInResultFromIntent(data);
        if (result.isSuccess()) {
            GoogleSignInAccount account = result.getSignInAccount();
            loginResultListener.onGooglePlusLoginSuccess(account.getIdToken());
        } else {
            Log.e("Failed to authenticate GooglePlus", result.getStatus().getStatusCode());
            loginResultListener.onGooglePlusLoginFailed(result.getStatus().getStatusMessage());
        }
        return true;
    }
}
