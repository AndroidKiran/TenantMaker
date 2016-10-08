package com.easy.make.tenantmaker.core.country.service;

import com.easy.make.tenantmaker.core.country.database.CountryDatabase;
import com.easy.make.tenantmaker.core.country.model.Countries;

import rx.Observable;

/**
 * Created by ravi on 30/09/16.
 */

public class PersistedCountryService implements CountryService {

    private final CountryDatabase countryDatabase;

    public PersistedCountryService(CountryDatabase countryDatabase){
        this.countryDatabase = countryDatabase;
    }

    @Override
    public Observable<Countries> getCountries() {
        return countryDatabase.observeCountries();
    }
}
