package com.easy.make.tenantmaker.core.country.model;

import android.content.Context;

import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

/**
 * Created by ravi on 30/09/16.
 */

public class Countries {

    private List<Country> countriesList;

    public Countries(List<Country> countries){
        this.countriesList = countries;
    }

    public int size(){
        return countriesList.size();
    }

    public Country getCountry(int index){
        return countriesList.get(index);
    }


    public List<Country> getCountriesList() {
        return countriesList;
    }

    public void addCountry(Country country) {
        countriesList.add(country);
    }

    public void sort(final Context context){
        Collections.sort(countriesList, new Comparator<Country>() {
            @Override
            public int compare(Country country1, Country country2) {
                final Locale locale = context.getResources().getConfiguration().locale;
                final Collator collator = Collator.getInstance(locale);
                collator.setStrength(Collator.PRIMARY);
                return collator.compare(
                        new Locale(locale.getLanguage(), country1.getIsoCode()).getDisplayCountry(),
                        new Locale(locale.getLanguage(), country2.getIsoCode()).getDisplayCountry());
            }
        });
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Countries)) return false;

        Countries countries = (Countries) o;

        return countriesList != null ? countriesList.equals(countries.countriesList) : countries.countriesList == null;

    }

    @Override
    public int hashCode() {
        return countriesList != null ? countriesList.hashCode() : 0;
    }
}
