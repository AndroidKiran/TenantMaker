package com.easy.make.tenantmaker.core.login.displayer;

import com.easy.make.tenantmaker.core.Utils.GsonService;
import com.easy.make.tenantmaker.core.Utils.PreferenceService;
import com.easy.make.tenantmaker.core.user.data.model.User;

public interface LoginDisplayer {

    void saveUserPreference(PreferenceService preferenceService, GsonService gsonService, User user);

    void saveFirstFlowPreference(PreferenceService preferenceService, GsonService gsonService, boolean b);

    void showFlatViewComponent();

    void hideFlatViewComponent();

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
