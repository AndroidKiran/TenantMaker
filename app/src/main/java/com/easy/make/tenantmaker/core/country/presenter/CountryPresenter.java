package com.easy.make.tenantmaker.core.country.presenter;

import android.os.Bundle;

import com.easy.make.tenantmaker.base.OnFragmentInteractionListener;
import com.easy.make.tenantmaker.base.utils.UtilBundles;
import com.easy.make.tenantmaker.core.analytics.Analytics;
import com.easy.make.tenantmaker.core.analytics.ErrorLogger;
import com.easy.make.tenantmaker.core.country.displayer.CountryDisplayer;
import com.easy.make.tenantmaker.core.country.model.Countries;
import com.easy.make.tenantmaker.core.country.model.Country;
import com.easy.make.tenantmaker.core.country.service.CountryService;

import rx.Subscription;
import rx.functions.Action1;

/**
 * Created by ravi on 01/10/16.
 */

public class CountryPresenter {

    private final CountryService countryService;
    private final CountryDisplayer countryDisplayer;
    private final Analytics analytics;
    private final ErrorLogger errorLogger;
    private final OnFragmentInteractionListener fragmentInteractionListener;
    private Subscription subscription;

    public CountryPresenter(CountryService countryService,
                            CountryDisplayer countryDisplayer,
                            OnFragmentInteractionListener fragmentInteractionListener,
                            Analytics analytics,
                            ErrorLogger errorLogger) {
        this.countryService = countryService;
        this.countryDisplayer = countryDisplayer;
        this.analytics = analytics;
        this.errorLogger = errorLogger;
        this.fragmentInteractionListener = fragmentInteractionListener;
    }

    public void startPresenting(){
        countryDisplayer.attach(countryInteractionListener);
        subscription = countryService.getCountries()
                .subscribe(new Action1<Countries>() {
                    @Override
                    public void call(Countries countries) {
                        countryDisplayer.display(countries);
                    }
                });
    }

    public void stopPresenting(){
        subscription = null;
        countryDisplayer.detach(countryInteractionListener);
    }

    final CountryDisplayer.CountryInteractionListener countryInteractionListener = new CountryDisplayer.CountryInteractionListener() {
        @Override
        public void onCountrySelected(Country country) {
            Bundle countryBundle = new Bundle();
            countryBundle.putParcelable(UtilBundles.EXTRA_BUNDLE, country);
            fragmentInteractionListener.onFragmentInteraction(countryBundle);
        }
    };
}
