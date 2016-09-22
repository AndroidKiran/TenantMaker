package com.easy.make.tenantmaker.core.login.displayer;

public interface LoginDisplayer {

    void setUpViewPager();

    void attach(LoginActionListener actionListener);

    void detach(LoginActionListener actionListener);

    void showAuthenticationError(String message);

    public interface LoginActionListener {

        void onGooglePlusLoginSelected();

    }

}
