package com.easy.make.tenantmaker.core.flat.displayer;


import com.easy.make.tenantmaker.core.flat.model.Flat;

/**
 * Created by ravi on 11/09/16.
 */
public interface NewFlatDisplayer {

    void attach(FlatCreationListener flatCreationListener);

    void detach(FlatCreationListener flatCreationListener);

    void showProgress();

    void dismissProgress();

    void clear();

    interface FlatCreationListener {

        void onCreateFlatClicked(Flat flat);

        void onCancel();

        void onAddressTextChanged(String address);

    }
}
