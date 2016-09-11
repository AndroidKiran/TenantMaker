package com.easy.make.tenantmaker.home;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.easy.make.core.home.displayer.HomeDisplayer;
import com.easy.make.core.home.presenter.HomePresenter;
import com.easy.make.tenantmaker.BaseActivity;
import com.easy.make.tenantmaker.Dependencies;
import com.easy.make.tenantmaker.R;
import com.easy.make.tenantmaker.component.ViewPagerAdapter;
import com.easy.make.tenantmaker.flats.FlatListFragment;
import com.easy.make.tenantmaker.home.view.HomeView;
import com.easy.make.tenantmaker.navigation.AndroidNavigator;
import com.easy.make.tenantmaker.tenants.TenantListFragment;

/**
 * Created by ravi on 09/09/16.
 */
public class HomeActivity extends BaseActivity {

    private TenantListFragment tenantListFragment;
    private FlatListFragment flatListFragment;
    private HomePresenter homePresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        HomeView homeView = (HomeView) findViewById(R.id.home);
        homeView.setAppCompatActivity(this);
        homeView.setViewPagerAdapter(getViewPagerAdapter(savedInstanceState));
        homePresenter = new HomePresenter((HomeDisplayer) homeView, new AndroidNavigator(this), Dependencies.INSTANCE.getAnalytics(),
                Dependencies.INSTANCE.getErrorLogger());
    }


    @Override
    protected void onStart() {
        super.onStart();
        homePresenter.startPresenting();

    }


    @Override
    protected void onStop() {
        super.onStop();
        homePresenter.stopPresenting();
    }

    private ViewPagerAdapter getViewPagerAdapter(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            tenantListFragment = TenantListFragment.newInstance(null);
            flatListFragment = FlatListFragment.newInstance(null);
        } else {
            tenantListFragment = (TenantListFragment) getSupportFragmentManager().getFragment(savedInstanceState, TenantListFragment.TAG);
            flatListFragment = (FlatListFragment) getSupportFragmentManager().getFragment(savedInstanceState, FlatListFragment.TAG);
        }
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), this);
        viewPagerAdapter.addFragment(tenantListFragment, getString(R.string.str_due_tenants));
        viewPagerAdapter.addFragment(flatListFragment, getString(R.string.str_profile));
        return viewPagerAdapter;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        if (flatListFragment != null && flatListFragment.isAdded()) {
            getSupportFragmentManager().putFragment(outState, FlatListFragment.TAG, flatListFragment);
        }

        if (tenantListFragment != null && tenantListFragment.isAdded()) {
            getSupportFragmentManager().putFragment(outState, TenantListFragment.TAG, tenantListFragment);
        }
//
//        outState.putInt(ConsultBundle.BUNDLE_SHOW_TAB, mCurrentTab);
        super.onSaveInstanceState(outState);
    }
}
