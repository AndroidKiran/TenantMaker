package com.easy.make.tenantmaker.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.easy.make.tenantmaker.BuildConfig;
import com.novoda.notils.logger.simple.Log;

/**
 * Created by ravi on 10/09/16.
 */
public class BaseFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.setShowLogs(BuildConfig.DEBUG);
        Dependencies.INSTANCE.init(getActivity());
    }
}
