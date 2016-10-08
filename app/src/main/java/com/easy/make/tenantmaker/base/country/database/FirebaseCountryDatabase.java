package com.easy.make.tenantmaker.base.country.database;

import com.easy.make.tenantmaker.base.rx.FirebaseObservableListeners;
import com.easy.make.tenantmaker.core.country.database.CountryDatabase;
import com.easy.make.tenantmaker.core.country.model.Countries;
import com.easy.make.tenantmaker.core.country.model.Country;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by ravi on 30/09/16.
 */

public class FirebaseCountryDatabase implements CountryDatabase {

    private final DatabaseReference countryDB;
    private final FirebaseObservableListeners firebaseObservableListeners;

    public FirebaseCountryDatabase(FirebaseDatabase firebaseDatabase, FirebaseObservableListeners firebaseObservableListeners) {
        countryDB = firebaseDatabase.getReference("countries");
        this.firebaseObservableListeners = firebaseObservableListeners;
    }

    @Override
    public Observable<Countries> observeCountries() {
        return firebaseObservableListeners.listenToValueEvents(countryDB, toCountries());
    }

    private static Func1<DataSnapshot, Countries> toCountries() {
        return new Func1<DataSnapshot, Countries>() {
            @Override
            public Countries call(DataSnapshot dataSnapshot) {
                List<Country> countries = new ArrayList<Country>();
                if (dataSnapshot.hasChildren()) {
                    Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                    for (DataSnapshot child : children) {
                        Country country = (Country) child.getValue(Country.class);
                        country.setIsoCode(child.getKey());
                        countries.add(country);
                    }
                }
                return new Countries(countries);
            }
        };
    }

}
