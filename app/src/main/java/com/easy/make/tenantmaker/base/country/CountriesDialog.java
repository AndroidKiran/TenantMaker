package com.easy.make.tenantmaker.base.country;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.easy.make.tenantmaker.R;
import com.easy.make.tenantmaker.base.BaseDialogFragment;
import com.easy.make.tenantmaker.base.Dependencies;
import com.easy.make.tenantmaker.base.OnFragmentInteractionListener;
import com.easy.make.tenantmaker.base.country.view.CountriesView;
import com.easy.make.tenantmaker.core.country.presenter.CountryPresenter;

/**
 * Created by ravi on 01/10/16.
 */

public class CountriesDialog extends BaseDialogFragment {
//    public final String TAG = "Countries Dialog";

    private CountryPresenter countryPresenter;
    private OnFragmentInteractionListener fragmentInteractionListener;

    public static CountriesDialog newInstance(FragmentManager fragmentManager){
        CountriesDialog countriesDialog = new CountriesDialog();
        countriesDialog.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
        countriesDialog.show(fragmentManager, "Countries Dialog");
        return countriesDialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_countries, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        CountriesView countriesView = (CountriesView) view.findViewById(R.id.countries_view);

        countryPresenter = new CountryPresenter(Dependencies.INSTANCE.getCountryService(),
                countriesView,
                fragmentInteractionListener,
                Dependencies.INSTANCE.getAnalytics(),
                Dependencies.INSTANCE.getErrorLogger());

//        countriesView.setAdpater(Dependencies.INSTANCE.getFirebaseDatabase());

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragmentInteractionListener = (OnFragmentInteractionListener) context;
    }

    @Override
    public void onDetach() {
        fragmentInteractionListener = null;
        super.onDetach();
    }

    @Override
    public void onStart() {
        super.onStart();
        countryPresenter.startPresenting();
    }

    @Override
    public void onStop() {
        countryPresenter.stopPresenting();
        super.onStop();
    }
}
