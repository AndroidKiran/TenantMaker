package com.easy.make.tenantmaker.login.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.easy.make.core.login.displayer.LoginDisplayer;
import com.easy.make.tenantmaker.R;
import com.easy.make.tenantmaker.component.ViewPagerAdapter;
import com.novoda.notils.caster.Views;


public class LoginView extends LinearLayout implements LoginDisplayer {

    private View loginButton;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;

    public LoginView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        View.inflate(getContext(), R.layout.merge_login_view, this);
        loginButton = Views.findById(this, R.id.sign_in_button);
        viewPager = Views.findById(this, R.id.vp_home);
    }

    @Override
    public void setUpViewPager() {
        viewPager.setAdapter(getViewPagerAdapter());
        viewPager.setOffscreenPageLimit(3);
    }

    @Override
    public void attach(final LoginActionListener actionListener) {
        loginButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                actionListener.onGooglePlusLoginSelected();
            }
        });
    }

    @Override
    public void detach(LoginActionListener actionListener) {
        loginButton.setOnClickListener(null);
    }

    @Override
    public void showAuthenticationError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show(); //TODO improve error display
    }

    public ViewPagerAdapter getViewPagerAdapter() {
        return viewPagerAdapter;
    }

    public void setViewPagerAdapter(ViewPagerAdapter viewPagerAdapter) {
        this.viewPagerAdapter = viewPagerAdapter;
    }

}
