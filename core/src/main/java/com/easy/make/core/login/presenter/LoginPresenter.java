package com.easy.make.core.login.presenter;


import com.easy.make.core.Utils.GsonService;
import com.easy.make.core.Utils.PreferenceService;
import com.easy.make.core.analytics.Analytics;
import com.easy.make.core.analytics.ErrorLogger;
import com.easy.make.core.login.data.model.Authentication;
import com.easy.make.core.login.displayer.LoginDisplayer;
import com.easy.make.core.login.service.LoginService;
import com.easy.make.core.navigation.LoginNavigator;

import rx.Subscription;
import rx.functions.Action1;

public class LoginPresenter {

    private final LoginService loginService;
    private final LoginDisplayer loginDisplayer;
    private final LoginNavigator navigator;
    private final ErrorLogger errorLogger;
    private final Analytics analytics;
    private final GsonService gsonService;
    private final PreferenceService preferenceService;

    private Subscription subscription;

    public LoginPresenter(LoginService loginService,
                          LoginDisplayer loginDisplayer,
                          LoginNavigator navigator,
                          ErrorLogger errorLogger,
                          Analytics analytics,
                          PreferenceService preferenceService,
                          GsonService gsonService) {
        this.loginService = loginService;
        this.loginDisplayer = loginDisplayer;
        this.navigator = navigator;
        this.errorLogger = errorLogger;
        this.analytics = analytics;
        this.preferenceService = preferenceService;
        this.gsonService = gsonService;
    }

    public void startPresenting() {
        navigator.attach(loginResultListener);
        loginDisplayer.setUpViewPager();
        loginDisplayer.attach(actionListener);
        subscription = loginService.getAuthentication()
                .subscribe(new Action1<Authentication>() {
                    @Override
                    public void call(Authentication authentication) {
                        if (authentication.isSuccess()) {
                            preferenceService.setLoginUserPreference(gsonService.toString(authentication.getUser()));
                            navigator.toMain();
                        } else {
                            errorLogger.reportError(authentication.getFailure(), "Authentication failed");
                            loginDisplayer.showAuthenticationError(authentication.getFailure().getLocalizedMessage()); //TODO improve error display
                        }
                    }
                });
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


}