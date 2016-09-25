package com.easy.make.tenantmaker.core.login.displayer;

public interface LoginDisplayer {

    void showProgress();

    void dismissProgress();

    void setUpViewPager();

    void attach(LoginActionListener actionListener);

    void detach(LoginActionListener actionListener);

    void showAuthenticationError(String message);

    interface LoginActionListener {

        void onGooglePlusLoginSelected();

    }

}
