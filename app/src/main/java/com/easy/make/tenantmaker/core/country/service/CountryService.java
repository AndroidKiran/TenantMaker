package com.easy.make.tenantmaker.core.country.service;

import com.easy.make.tenantmaker.core.country.model.Countries;

import rx.Observable;

/**
 * Created by ravi on 30/09/16.
 */

public interface CountryService {
    Observable<Countries> getCountries();
}
