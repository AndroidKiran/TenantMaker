package com.easy.make.tenantmaker.base.login.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.easy.make.tenantmaker.R;
import com.easy.make.tenantmaker.base.component.ViewPagerAdapter;
import com.easy.make.tenantmaker.base.component.materialcomponent.MaterialProgressDialog;
import com.easy.make.tenantmaker.base.utils.DialogUtils;
import com.easy.make.tenantmaker.core.Utils.GsonService;
import com.easy.make.tenantmaker.core.Utils.PreferenceService;
import com.easy.make.tenantmaker.core.login.displayer.LoginDisplayer;
import com.easy.make.tenantmaker.core.user.data.model.User;
import com.novoda.notils.caster.Views;


public class LoginView extends LinearLayout implements LoginDisplayer {

    private View loginButton;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private AppCompatActivity appCompatActivity;
    private MaterialProgressDialog materialProgressDialog;

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
    public void saveUserPreference(PreferenceService preferenceService, GsonService gsonService, User user) {
        preferenceService.setLoginUserPreference(gsonService.toString(user));
    }

    @Override
    public void saveFirstFlowPreference(PreferenceService preferenceService, GsonService gsonService, boolean b) {

    }

    @Override
    public void showFlatViewComponent() {

    }

    @Override
    public void hideFlatViewComponent() {

    }

    @Override
    public void showProgress() {
        materialProgressDialog =  new MaterialProgressDialog(getAppCompatActivity());
        DialogUtils.showMaterialProgressDialog(materialProgressDialog, getAppCompatActivity().getString(R.string.str_progress_tenant_title), getAppCompatActivity().getString(R.string.str_progress_wait), getAppCompatActivity());
    }

    @Override
    public void dismissProgress() {
        if (materialProgressDialog != null){
            materialProgressDialog.dismiss();
        }
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

    public AppCompatActivity getAppCompatActivity() {
        return appCompatActivity;
    }

    public void setAppCompatActivity(AppCompatActivity appCompatActivity) {
        this.appCompatActivity = appCompatActivity;
    }


}
