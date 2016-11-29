package com.easy.make.tenantmaker.core.flat.displayer;


import android.os.Bundle;

import com.easy.make.tenantmaker.core.Utils.PreferenceService;
import com.easy.make.tenantmaker.core.flat.model.Flat;

/**
 * Created by ravi on 11/09/16.
 */
public interface NewFlatDisplayer {

    void attach(FlatCreationListener flatCreationListener);

    void detach(FlatCreationListener flatCreationListener);

    void showProgress();

    void dismissProgress();

    void toggleViewVisibility(PreferenceService preferenceService);

    void clear();

    void onFragmentInteraction(Bundle bundle);

    void showDialog();

    void dismissDialog();

    interface FlatCreationListener {

        void onCreateFlatClicked(Flat flat);

        void onCancel();
    }
}
