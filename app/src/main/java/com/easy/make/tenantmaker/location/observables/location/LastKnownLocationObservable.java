package com.easy.make.tenantmaker.location.observables.location;

import android.content.Context;
import android.location.Location;

import com.easy.make.tenantmaker.location.observables.BaseLocationObservable;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import rx.Observable;
import rx.Observer;

public class LastKnownLocationObservable extends BaseLocationObservable<Location> {

    public static Observable<Location> createObservable(Context ctx) {
        return Observable.create(new LastKnownLocationObservable(ctx));
    }

    public LastKnownLocationObservable(Context ctx) {
        super(ctx);
    }

    @Override
    protected void onGoogleApiClientReady(GoogleApiClient apiClient, Observer<? super Location> observer) {
        Location location = LocationServices.FusedLocationApi.getLastLocation(apiClient);
        if (location != null) {
            observer.onNext(location);
        }
        observer.onCompleted();
    }
}
