package com.easy.make.tenantmaker.core.country.displayer;

import com.easy.make.tenantmaker.core.country.model.Countries;
import com.easy.make.tenantmaker.core.country.model.Country;

/**
 * Created by ravi on 30/09/16.
 */

public interface CountryDisplayer {

    void attach(CountryInteractionListener countryInteractionListener);

    void detach(CountryInteractionListener countryInteractionListener);

    void display(Countries countries);

    interface CountryInteractionListener {
        void onCountrySelected(Country country);
    }

}
