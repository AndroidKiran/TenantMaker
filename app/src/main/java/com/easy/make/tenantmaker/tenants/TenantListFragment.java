package com.easy.make.tenantmaker.tenants;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.easy.make.core.tenant.displayer.TenantsDisplayer;
import com.easy.make.core.tenant.presenter.TenantPresenter;
import com.easy.make.tenantmaker.BaseFragment;
import com.easy.make.tenantmaker.Dependencies;
import com.easy.make.tenantmaker.R;
import com.easy.make.tenantmaker.navigation.AndroidNavigator;

/**
 * Created by ravi on 10/09/16.
 */
public class TenantListFragment extends BaseFragment {

    public static final String TAG = "TenantListFragment";
    private TenantPresenter presenter;

    public static TenantListFragment newInstance(Bundle bundle) {
        TenantListFragment instance = new TenantListFragment();
        instance.setArguments(bundle);
        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_tenants,container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        TenantsDisplayer tenantsDisplayer = (TenantsDisplayer) view.findViewById(R.id.tenants_view);

        presenter = new TenantPresenter(
                Dependencies.INSTANCE.getLoginService(),
                Dependencies.INSTANCE.getTenantService(),
                tenantsDisplayer,
                Dependencies.INSTANCE.getAnalytics(),
                new AndroidNavigator(getActivity()),
                Dependencies.INSTANCE.getErrorLogger(),
                "",
                false
        );
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.startPresenting();
    }

    @Override
    public void onStop() {
        presenter.stopPresenting();
        super.onStop();
    }
}
