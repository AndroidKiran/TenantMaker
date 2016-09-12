package com.easy.make.tenantmaker.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.easy.make.core.login.displayer.LoginDisplayer;
import com.easy.make.core.login.presenter.LoginPresenter;
import com.easy.make.tenantmaker.BaseActivity;
import com.easy.make.tenantmaker.Dependencies;
import com.easy.make.tenantmaker.R;
import com.easy.make.tenantmaker.component.ViewPagerAdapter;
import com.easy.make.tenantmaker.login.view.LoginView;
import com.easy.make.tenantmaker.navigation.AndroidLoginNavigator;
import com.easy.make.tenantmaker.navigation.AndroidNavigator;
import com.easy.make.tenantmaker.utils.UtilBundles;

public class LoginActivity extends BaseActivity {

    private static final int RC_SIGN_IN = 42;

    private LoginPresenter presenter;
    private AndroidLoginNavigator navigator;
    private OnBoardFragment onBoardFragment1;
    private OnBoardFragment onBoardFragment2;
    private OnBoardFragment onBoardFragment3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        LoginView loginView = (LoginView) findViewById(R.id.login_view);
        loginView.setViewPagerAdapter(getViewPagerAdapter(savedInstanceState));

        LoginGoogleApiClient loginGoogleApiClient = new LoginGoogleApiClient(this);
        loginGoogleApiClient.setupGoogleApiClient();

        navigator = new AndroidLoginNavigator(this, loginGoogleApiClient, new AndroidNavigator(this));

        presenter = new LoginPresenter(Dependencies.INSTANCE.getLoginService(),
                (LoginDisplayer) loginView,
                navigator,
                Dependencies.INSTANCE.getErrorLogger(),
                Dependencies.INSTANCE.getAnalytics(),
                Dependencies.INSTANCE.getPreference(),
                Dependencies.INSTANCE.getGsonService());

    }

    private ViewPagerAdapter getViewPagerAdapter(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            Bundle bundle = new Bundle();
            bundle.putInt(UtilBundles.EXTRA_INT, OnBoardFragment.TYPE_ONE);
            onBoardFragment1 = OnBoardFragment.newInstance(bundle);
            bundle = new Bundle();
            bundle.putInt(UtilBundles.EXTRA_INT, OnBoardFragment.TYPE_TWO);
            onBoardFragment2 = OnBoardFragment.newInstance(bundle);
            bundle = new Bundle();
            bundle.putInt(UtilBundles.EXTRA_INT, OnBoardFragment.TYPE_THREE);
            onBoardFragment3 = OnBoardFragment.newInstance(bundle);
        } else {
            onBoardFragment1 = (OnBoardFragment) getSupportFragmentManager().getFragment(savedInstanceState, OnBoardFragment.TAG_1);
            onBoardFragment2 = (OnBoardFragment) getSupportFragmentManager().getFragment(savedInstanceState, OnBoardFragment.TAG_2);
            onBoardFragment3 = (OnBoardFragment) getSupportFragmentManager().getFragment(savedInstanceState, OnBoardFragment.TAG_3);
        }
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), this);
        viewPagerAdapter.addFragment(onBoardFragment1, getString(R.string.str_due_tenants));
        viewPagerAdapter.addFragment(onBoardFragment2, getString(R.string.str_profile));
        viewPagerAdapter.addFragment(onBoardFragment3, getString(R.string.str_profile));
        return viewPagerAdapter;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!navigator.onActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.startPresenting();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.stopPresenting();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (onBoardFragment1 != null && onBoardFragment1.isAdded()) {
            getSupportFragmentManager().putFragment(outState, OnBoardFragment.TAG_1, onBoardFragment1);
        }

        if (onBoardFragment2 != null && onBoardFragment2.isAdded()) {
            getSupportFragmentManager().putFragment(outState, OnBoardFragment.TAG_2, onBoardFragment2);
        }

        if (onBoardFragment3 != null && onBoardFragment3.isAdded()) {
            getSupportFragmentManager().putFragment(outState, OnBoardFragment.TAG_3, onBoardFragment3);
        }
    }
}
