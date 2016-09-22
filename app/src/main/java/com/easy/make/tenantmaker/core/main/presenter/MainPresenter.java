package com.easy.make.tenantmaker.core.main.presenter;

import com.easy.make.tenantmaker.core.Utils.PreferenceService;
import com.easy.make.tenantmaker.core.navigation.Navigator;

/**
 * Created by ravi on 12/09/16.
 */
public class MainPresenter {

    private final Navigator navigator;
    private final PreferenceService preferenceService;

    public MainPresenter(Navigator navigator, PreferenceService preferenceService){
        this.navigator = navigator;
        this.preferenceService = preferenceService;
    }

    public void startPresenting(){
        manageFirstFlow();
    }

    public void stopPresenting(){

    }


    private void manageFirstFlow(){
        String userData = preferenceService.getLoginUserPreference();
        boolean flatOnBoarding = preferenceService.getFirstFlowValue();
        if (userData == null || userData.trim().length() == 0 ){
            navigator.toLogin();
        } else if (!flatOnBoarding){
            navigator.toNewFlat();
        } else {
            navigator.toHome();
        }
    }
}
