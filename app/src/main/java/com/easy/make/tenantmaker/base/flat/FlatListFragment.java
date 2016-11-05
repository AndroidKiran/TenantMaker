package com.easy.make.tenantmaker.base.flat;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.easy.make.tenantmaker.R;
import com.easy.make.tenantmaker.base.BaseFragment;
import com.easy.make.tenantmaker.base.Dependencies;
import com.easy.make.tenantmaker.base.navigation.AndroidNavigator;
import com.easy.make.tenantmaker.core.flat.displayer.FlatsDisplayer;
import com.easy.make.tenantmaker.core.flat.presenter.FlatsPresenter;

/**
 * Created by ravi on 10/09/16.
 */
public class FlatListFragment extends BaseFragment {

    public static final String TAG = "FlatListFragment";
    private FlatsPresenter presenter;


    public static FlatListFragment newInstance(Bundle bundle) {
        FlatListFragment instance = new FlatListFragment();
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
        return inflater.inflate(R.layout.flats_view, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FlatsDisplayer flatsDisplayer = (FlatsDisplayer) view.findViewById(R.id.flats_view);

        presenter = new FlatsPresenter(
                Dependencies.INSTANCE.getLoginService(),
                Dependencies.INSTANCE.getFlatService(),
                flatsDisplayer,
                Dependencies.INSTANCE.getAnalytics(),
                new AndroidNavigator(getActivity()),
                Dependencies.INSTANCE.getErrorLogger()
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
