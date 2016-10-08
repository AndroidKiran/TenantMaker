package com.easy.make.tenantmaker.core.country.database;

import com.easy.make.tenantmaker.core.country.model.Countries;

import rx.Observable;

/**
 * Created by ravi on 30/09/16.
 */

public interface CountryDatabase {

    Observable<Countries> observeCountries();
}
