package com.easy.make.tenantmaker.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;

import com.easy.make.tenantmaker.BuildConfig;
import com.novoda.notils.logger.simple.Log;

/**
 * Created by ravi on 01/10/16.
 */

public class BaseDialogFragment extends DialogFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.setShowLogs(BuildConfig.DEBUG);
        Dependencies.INSTANCE.init(getActivity());
    }
}
