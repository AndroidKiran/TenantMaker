package com.easy.make.tenantmaker.flat;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.easy.make.core.flat.displayer.NewFlatDisplayer;
import com.easy.make.core.flat.presenter.NewFlatPresenter;
import com.easy.make.tenantmaker.BaseActivity;
import com.easy.make.tenantmaker.Dependencies;
import com.easy.make.tenantmaker.R;
import com.easy.make.tenantmaker.flat.view.NewFlatView;
import com.easy.make.tenantmaker.navigation.AndroidNavigator;


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
        newFlatPresenter = new NewFlatPresenter((NewFlatDisplayer) newFlatView,
                Dependencies.INSTANCE.getFlatService(),
                Dependencies.INSTANCE.getLoginService(),
                new AndroidNavigator(this),
                Dependencies.INSTANCE.getErrorLogger(),
                Dependencies.INSTANCE.getAnalytics());

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
