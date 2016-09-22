package com.easy.make.tenantmaker.core.home.displayer;

/**
 * Created by ravi on 09/09/16.
 */
public interface HomeDisplayer {

    void setUpViewPager();

    void attach(HomeInteractionListener homeInteractionListener);

    void detach(HomeInteractionListener homeInteractionListener);

    interface HomeInteractionListener {
        void onFabBtnClicked(int tab);

        void onQueryTextChange(String query);
    }
}
