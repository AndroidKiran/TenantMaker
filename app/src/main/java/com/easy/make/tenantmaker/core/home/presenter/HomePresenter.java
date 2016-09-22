package com.easy.make.tenantmaker.core.home.presenter;

import com.easy.make.tenantmaker.core.analytics.Analytics;
import com.easy.make.tenantmaker.core.analytics.ErrorLogger;
import com.easy.make.tenantmaker.core.home.displayer.HomeDisplayer;
import com.easy.make.tenantmaker.core.navigation.Navigator;

/**
 * Created by ravi on 09/09/16.
 */
public class HomePresenter {

    private final HomeDisplayer homeDisplayer;
    private final Navigator navigator;
    private final Analytics analytics;
    private final ErrorLogger errorLogger;

    public HomePresenter(HomeDisplayer homeDisplayer, Navigator navigator, Analytics analytics, ErrorLogger errorLogger){
        this.homeDisplayer = homeDisplayer;
        this.navigator = navigator;
        this.analytics = analytics;
        this.errorLogger = errorLogger;
    }

    public void startPresenting(){
        homeDisplayer.attach(homeInteractionListener);
        homeDisplayer.setUpViewPager();
    }

    public void stopPresenting(){
        homeDisplayer.detach(homeInteractionListener);
    }

    private final HomeDisplayer.HomeInteractionListener homeInteractionListener = new HomeDisplayer.HomeInteractionListener() {
        @Override
        public void onFabBtnClicked(int tab) {
            switch (tab){
                case 0:
                    navigator.toCreateTenant();
                    break;
                case 1:
                    System.out.println("Pg selected");
                    break;
            }
        }

        @Override
        public void onQueryTextChange(String query) {
            navigator.toTenants(query);
        }
    };
}
