package com.easy.make.tenantmaker.base.flat;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.easy.make.tenantmaker.R;
import com.easy.make.tenantmaker.base.BaseActivity;
import com.easy.make.tenantmaker.base.Dependencies;
import com.easy.make.tenantmaker.base.flat.view.NewFlatView;
import com.easy.make.tenantmaker.base.navigation.AndroidNavigator;
import com.easy.make.tenantmaker.core.flat.displayer.NewFlatDisplayer;
import com.easy.make.tenantmaker.core.flat.presenter.NewFlatPresenter;
import com.easy.make.tenantmaker.location.ReactiveLocationProviderImpl;
import com.google.android.gms.location.LocationRequest;


/**
 * Created by ravi on 04/09/16.
 */
public class NewFlatActivity extends BaseActivity {

    private NewFlatView newFlatView;
    private NewFlatPresenter newFlatPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_flat);
        newFlatView = (NewFlatView) findViewById(R.id.create_new_flat);
        newFlatView.setAppCompatActivity(this);
        newFlatView.setMap();

        final LocationRequest locationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setNumUpdates(5)
                .setInterval(100);

        newFlatPresenter = new NewFlatPresenter((NewFlatDisplayer) newFlatView,
                Dependencies.INSTANCE.getFlatService(),
                Dependencies.INSTANCE.getLoginService(),
                new AndroidNavigator(this),
                Dependencies.INSTANCE.getErrorLogger(),
                Dependencies.INSTANCE.getAnalytics(),
                Dependencies.INSTANCE.getPreference(),
                Dependencies.INSTANCE.getGsonService(),
                new ReactiveLocationProviderImpl(this),
                locationRequest);
    }


    @Override
    protected void onStart() {
        super.onStart();
        newFlatPresenter.startPresenting();
    }

    @Override
    protected void onStop() {
        newFlatPresenter.stopPresenting();
        super.onStop();
    }
}
