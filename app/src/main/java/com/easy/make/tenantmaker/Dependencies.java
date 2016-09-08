package com.easy.make.tenantmaker;

import android.content.Context;

import com.easy.make.core.Config;
import com.easy.make.core.analytics.Analytics;
import com.easy.make.core.analytics.ErrorLogger;
import com.easy.make.core.login.service.FirebaseLoginService;
import com.easy.make.core.login.service.LoginService;
import com.easy.make.core.tenant.service.PersistedTenantService;
import com.easy.make.core.tenant.service.TenantService;
import com.easy.make.core.user.service.PersistedUserService;
import com.easy.make.core.user.service.UserService;
import com.easy.make.tenantmaker.analytics.FirebaseAnalyticsAnalytics;
import com.easy.make.tenantmaker.analytics.FirebaseErrorLogger;
import com.easy.make.tenantmaker.login.database.FirebaseAuthDatabase;
import com.easy.make.tenantmaker.rx.FirebaseObservableListeners;
import com.easy.make.tenantmaker.tenants.database.FirebaseTenantDatabase;
import com.easy.make.tenantmaker.user.database.FirebaseUserDatabase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public enum Dependencies {
    INSTANCE;

    private Analytics analytics;
    private ErrorLogger errorLogger;

    private LoginService loginService;
    private UserService userService;
    private Config config;
    private PersistedTenantService tenantService;

    public void init(Context context) {
        if (needsInitialisation()) {
            Context appContext = context.getApplicationContext();
            FirebaseApp firebaseApp = FirebaseApp.initializeApp(appContext, FirebaseOptions.fromResource(appContext), "TenantMaker");
            FirebaseAuth firebaseAuth = FirebaseAuth.getInstance(firebaseApp);
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance(firebaseApp);
            firebaseDatabase.setPersistenceEnabled(true);
            FirebaseObservableListeners firebaseObservableListeners = new FirebaseObservableListeners();
            FirebaseUserDatabase userDatabase = new FirebaseUserDatabase(firebaseDatabase, firebaseObservableListeners);

            analytics = new FirebaseAnalyticsAnalytics(FirebaseAnalytics.getInstance(appContext));
            errorLogger = new FirebaseErrorLogger();
            loginService = new FirebaseLoginService(new FirebaseAuthDatabase(firebaseAuth), userDatabase);
            tenantService = new PersistedTenantService(new FirebaseTenantDatabase(firebaseDatabase, firebaseObservableListeners));
            userService = new PersistedUserService(userDatabase);
            config = FirebaseConfig.newInstance().init(errorLogger);
        }
    }

    private boolean needsInitialisation() {
        return loginService == null ||  tenantService == null
                || userService == null || analytics == null || errorLogger == null;
    }

    public Analytics getAnalytics() {
        return analytics;
    }

    public LoginService getLoginService() {
        return loginService;
    }

//    public ChatService getChatService() {
//        return chatService;
//    }

    public TenantService getTenantService() {
        return tenantService;
    }

    public UserService getUserService() {
        return userService;
    }

    public ErrorLogger getErrorLogger() {
        return errorLogger;
    }

    public Config getConfig() {
        return config;
    }
}
