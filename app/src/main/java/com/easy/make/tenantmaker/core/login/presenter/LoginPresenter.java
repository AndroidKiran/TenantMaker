package com.easy.make.tenantmaker.core.login.presenter;


import com.easy.make.tenantmaker.core.Utils.GsonService;
import com.easy.make.tenantmaker.core.Utils.PreferenceService;
import com.easy.make.tenantmaker.core.analytics.Analytics;
import com.easy.make.tenantmaker.core.analytics.ErrorLogger;
import com.easy.make.tenantmaker.core.flat.model.Flats;
import com.easy.make.tenantmaker.core.flat.service.FlatService;
import com.easy.make.tenantmaker.core.login.data.model.Authentication;
import com.easy.make.tenantmaker.core.login.displayer.LoginDisplayer;
import com.easy.make.tenantmaker.core.login.service.LoginService;
import com.easy.make.tenantmaker.core.navigation.LoginNavigator;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;

public class LoginPresenter {

    private final LoginService loginService;
    private final LoginDisplayer loginDisplayer;
    private final LoginNavigator navigator;
    private final ErrorLogger errorLogger;
    private final Analytics analytics;
    private final GsonService gsonService;
    private final PreferenceService preferenceService;
    private final FlatService flatService;

    private Subscription subscription;

    public LoginPresenter(LoginService loginService,
                          LoginDisplayer loginDisplayer,
                          LoginNavigator navigator,
                          ErrorLogger errorLogger,
                          Analytics analytics,
                          PreferenceService preferenceService,
                          GsonService gsonService,
                          FlatService flatService) {
        this.loginService = loginService;
        this.loginDisplayer = loginDisplayer;
        this.navigator = navigator;
        this.errorLogger = errorLogger;
        this.analytics = analytics;
        this.preferenceService = preferenceService;
        this.gsonService = gsonService;
        this.flatService = flatService;
    }

    public void startPresenting() {
        navigator.attach(loginResultListener);
        loginDisplayer.setUpViewPager();
        loginDisplayer.attach(actionListener);
        subscription = loginService.getAuthentication()
                .doOnNext(new Action1<Authentication>() {
                    @Override
                    public void call(Authentication authentication) {
                        if (authentication.isSuccess()) {
                            preferenceService.setLoginUserPreference(gsonService.toString(authentication.getUser()));
                        } else {
                            errorLogger.reportError(authentication.getFailure(), "Authentication failed");
                            loginDisplayer.showAuthenticationError(authentication.getFailure().getLocalizedMessage()); //TODO improve error display
                        }
                    }
                })
                .flatMap(getFlatsForUser())
                .subscribe(new Action1<Flats>() {
                    @Override
                    public void call(Flats flats) {
                        if (flats != null && flats.size() > 0){
                            preferenceService.setFirstFlowPreference(true);
                        } else {
                            preferenceService.setFirstFlowPreference(false);
                        }
                        navigator.toMain();
                    }
                });
//                .subscribe(new Action1<Authentication>() {
//                    @Override
//                    public void call(Authentication authentication) {
//                        if (authentication.isSuccess()) {
//                            preferenceService.setLoginUserPreference(gsonService.toString(authentication.getUser()));
//                            navigator.toMain();
//                        } else {
//                            errorLogger.reportError(authentication.getFailure(), "Authentication failed");
//                            loginDisplayer.showAuthenticationError(authentication.getFailure().getLocalizedMessage()); //TODO improve error display
//                        }
//                    }
//                });
    }

    public void stopPresenting() {
        navigator.detach(loginResultListener);
        loginDisplayer.detach(actionListener);
        subscription.unsubscribe(); //TODO handle checks
    }

    private final LoginDisplayer.LoginActionListener actionListener = new LoginDisplayer.LoginActionListener() {

        @Override
        public void onGooglePlusLoginSelected() {
//            analytics.trackSignInStarted("google");
            navigator.toGooglePlusLogin();
        }

    };

    private final LoginNavigator.LoginResultListener loginResultListener = new LoginNavigator.LoginResultListener() {
        @Override
        public void onGooglePlusLoginSuccess(String tokenId) {
//            analytics.trackSignInSuccessful("google");
            loginService.loginWithGoogle(tokenId);
        }

        @Override
        public void onGooglePlusLoginFailed(String statusMessage) {
            loginDisplayer.showAuthenticationError(statusMessage);
        }
    };

    private Func1<Authentication, Observable<Flats>> getFlatsForUser(){
        return new Func1<Authentication, Observable<Flats>>() {
            @Override
            public Observable<Flats> call(Authentication authentication) {
                return flatService.getFlats(authentication.getUser());
            }
        };
    }
}
