package com.easy.make.tenantmaker.location;

import android.app.PendingIntent;
import android.content.Context;
import android.location.Address;
import android.location.Location;

import com.easy.make.tenantmaker.core.location.ReactiveLocationProvider;
import com.easy.make.tenantmaker.location.observables.GoogleAPIClientObservable;
import com.easy.make.tenantmaker.location.observables.PendingResultObservable;
import com.easy.make.tenantmaker.location.observables.activity.ActivityUpdatesObservable;
import com.easy.make.tenantmaker.location.observables.geocode.GeocodeObservable;
import com.easy.make.tenantmaker.location.observables.geocode.ReverseGeocodeObservable;
import com.easy.make.tenantmaker.location.observables.location.AddLocationIntentUpdatesObservable;
import com.easy.make.tenantmaker.location.observables.location.LastKnownLocationObservable;
import com.easy.make.tenantmaker.location.observables.location.LocationUpdatesObservable;
import com.easy.make.tenantmaker.location.observables.location.MockLocationObservable;
import com.easy.make.tenantmaker.location.observables.location.RemoveLocationIntentUpdatesObservable;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.ActivityRecognitionResult;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.maps.model.LatLngBounds;

import java.util.List;
import java.util.Locale;

import rx.Observable;
import rx.functions.Func1;

/**
 * Factory of observables that can manipulate location
 * delivered by Google Play Services.
 */
public class ReactiveLocationProviderImpl implements ReactiveLocationProvider{

    private final Context ctx;

    public ReactiveLocationProviderImpl(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    public Observable<Location> getLastKnownLocation() {
        return LastKnownLocationObservable.createObservable(ctx);
    }

    @Override
    public Observable<Location> getUpdatedLocation(LocationRequest locationRequest) {
        return LocationUpdatesObservable.createObservable(ctx, locationRequest);
    }

    @Override
    public Observable<Status> mockLocation(Observable<Location> sourceLocationObservable) {
        return MockLocationObservable.createObservable(ctx, sourceLocationObservable);
    }

    @Override
    public Observable<Status> requestLocationUpdates(LocationRequest locationRequest, PendingIntent intent) {
        return AddLocationIntentUpdatesObservable.createObservable(ctx, locationRequest, intent);
    }

    @Override
    public Observable<Status> removeLocationUpdates(PendingIntent intent) {
        return RemoveLocationIntentUpdatesObservable.createObservable(ctx, intent);
    }

    @Override
    public Observable<List<Address>> getReverseGeocodeObservable(double lat, double lng, int maxResults) {
        return ReverseGeocodeObservable.createObservable(ctx, Locale.getDefault(), lat, lng, maxResults);
    }

    @Override
    public Observable<List<Address>> getReverseGeocodeObservable(Locale locale, double lat, double lng, int maxResults) {
        return ReverseGeocodeObservable.createObservable(ctx, locale, lat, lng, maxResults);
    }

    @Override
    public Observable<List<Address>> getGeocodeObservable(String locationName, int maxResults) {
        return getGeocodeObservable(locationName, maxResults, null);
    }

    @Override
    public Observable<List<Address>> getGeocodeObservable(String locationName, int maxResults, LatLngBounds bounds) {
        return GeocodeObservable.createObservable(ctx, locationName, maxResults, bounds);
    }

    @Override
    public Observable<ActivityRecognitionResult> getDetectedActivity(int detectIntervalMiliseconds) {
        return ActivityUpdatesObservable.createObservable(ctx, detectIntervalMiliseconds);
    }

    @Override
    public Observable<LocationSettingsResult> checkLocationSettings(final LocationSettingsRequest locationRequest) {
        return getGoogleApiClientObservable(LocationServices.API)
                .flatMap(new Func1<GoogleApiClient, Observable<LocationSettingsResult>>() {
                    @Override
                    public Observable<LocationSettingsResult> call(GoogleApiClient googleApiClient) {
                        return fromPendingResult(LocationServices.SettingsApi.checkLocationSettings(googleApiClient, locationRequest));
                    }
                });
    }

    @Override
    public Observable<GoogleApiClient> getGoogleApiClientObservable(Api... apis) {
        //noinspection unchecked
        return GoogleAPIClientObservable.create(ctx, apis);
    }

    public static <T extends Result> Observable<T> fromPendingResult(PendingResult<T> result) {
        return Observable.create(new PendingResultObservable<>(result));
    }
}
