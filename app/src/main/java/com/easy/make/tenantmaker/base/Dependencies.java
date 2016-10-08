package com.easy.make.tenantmaker.base;

import android.content.Context;

import com.easy.make.tenantmaker.base.analytics.FirebaseAnalyticsAnalytics;
import com.easy.make.tenantmaker.base.analytics.FirebaseErrorLogger;
import com.easy.make.tenantmaker.base.country.database.FirebaseCountryDatabase;
import com.easy.make.tenantmaker.base.flat.database.FirebaseFlatDatabase;
import com.easy.make.tenantmaker.base.login.database.FirebaseAuthDatabase;
import com.easy.make.tenantmaker.base.rx.FirebaseObservableListeners;
import com.easy.make.tenantmaker.base.tenants.database.FirebaseTenantDatabase;
import com.easy.make.tenantmaker.base.user.database.FirebaseUserDatabase;
import com.easy.make.tenantmaker.base.utils.AppPreferences;
import com.easy.make.tenantmaker.base.utils.GsonServiceImpl;
import com.easy.make.tenantmaker.core.Config;
import com.easy.make.tenantmaker.core.Utils.GsonService;
import com.easy.make.tenantmaker.core.Utils.PreferenceService;
import com.easy.make.tenantmaker.core.analytics.Analytics;
import com.easy.make.tenantmaker.core.analytics.ErrorLogger;
import com.easy.make.tenantmaker.core.country.service.CountryService;
import com.easy.make.tenantmaker.core.country.service.PersistedCountryService;
import com.easy.make.tenantmaker.core.flat.service.FlatService;
import com.easy.make.tenantmaker.core.flat.service.PersistedFlatService;
import com.easy.make.tenantmaker.core.login.service.FirebaseLoginService;
import com.easy.make.tenantmaker.core.login.service.LoginService;
import com.easy.make.tenantmaker.core.tenant.service.PersistedTenantService;
import com.easy.make.tenantmaker.core.tenant.service.TenantService;
import com.easy.make.tenantmaker.core.user.service.PersistedUserService;
import com.easy.make.tenantmaker.core.user.service.UserService;
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
    private PersistedFlatService flatService;
    private AppPreferences pref;
    private GsonServiceImpl gsonService;
    private CountryService countryService;
    private FirebaseDatabase firebaseDatabase;

    public void init(Context context) {
        if (needsInitialisation()) {
            Context appContext = context.getApplicationContext();
            FirebaseApp firebaseApp = FirebaseApp.initializeApp(appContext, FirebaseOptions.fromResource(appContext), "TenantMaker");
            FirebaseAuth firebaseAuth = FirebaseAuth.getInstance(firebaseApp);
            firebaseDatabase = FirebaseDatabase.getInstance(firebaseApp);
            firebaseDatabase.setPersistenceEnabled(true);
            FirebaseObservableListeners firebaseObservableListeners = new FirebaseObservableListeners();
            FirebaseUserDatabase userDatabase = new FirebaseUserDatabase(firebaseDatabase, firebaseObservableListeners);

            analytics = new FirebaseAnalyticsAnalytics(FirebaseAnalytics.getInstance(appContext));
            errorLogger = new FirebaseErrorLogger();
            loginService = new FirebaseLoginService(new FirebaseAuthDatabase(firebaseAuth), userDatabase);
            tenantService = new PersistedTenantService(new FirebaseTenantDatabase(firebaseDatabase, firebaseObservableListeners));
            userService = new PersistedUserService(userDatabase);
            flatService = new PersistedFlatService(new FirebaseFlatDatabase(firebaseDatabase, firebaseObservableListeners));
            countryService = new PersistedCountryService(new FirebaseCountryDatabase(firebaseDatabase, firebaseObservableListeners));

            config = FirebaseConfig.newInstance().init(errorLogger);
            pref = new AppPreferences(appContext);
            gsonService = new GsonServiceImpl();

        }
    }

    private boolean needsInitialisation() {
        return loginService == null ||  tenantService == null
                || userService == null || analytics == null || flatService == null || errorLogger == null;
    }

    public Analytics getAnalytics() {
        return analytics;
    }

    public LoginService getLoginService() {
        return loginService;
    }

    public TenantService getTenantService() {
        return tenantService;
    }

    public UserService getUserService() {
        return userService;
    }

    public FlatService getFlatService() {
        return flatService;
    }

    public CountryService getCountryService(){
        return countryService;
    }

    public ErrorLogger getErrorLogger() {
        return errorLogger;
    }

    public Config getConfig() {
        return config;
    }

    public PreferenceService getPreference(){
        return pref;
    }

    public GsonService getGsonService(){
        return gsonService;
    }

    public FirebaseDatabase getFirebaseDatabase() {
        return firebaseDatabase;
    }
}
