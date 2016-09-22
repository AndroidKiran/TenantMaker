package com.easy.make.tenantmaker.core.location;

import android.app.PendingIntent;
import android.location.Address;
import android.location.Location;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.ActivityRecognitionResult;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.maps.model.LatLngBounds;

import java.util.List;
import java.util.Locale;

import rx.Observable;

/**
 * Factory of observables that can manipulate location
 * delivered by Google Play Services.
 */
public interface ReactiveLocationProvider {


    public Observable<Location> getLastKnownLocation();

    public Observable<Location> getUpdatedLocation(LocationRequest locationRequest);


    public Observable<Status> mockLocation(Observable<Location> sourceLocationObservable);


    public Observable<Status> requestLocationUpdates(LocationRequest locationRequest, PendingIntent intent);


    public Observable<Status> removeLocationUpdates(PendingIntent intent);


    public Observable<List<Address>> getReverseGeocodeObservable(double lat, double lng, int maxResults);


    public Observable<List<Address>> getReverseGeocodeObservable(Locale locale, double lat, double lng, int maxResults);


    public Observable<List<Address>> getGeocodeObservable(String locationName, int maxResults);


    public Observable<List<Address>> getGeocodeObservable(String locationName, int maxResults, LatLngBounds bounds);

    public Observable<ActivityRecognitionResult> getDetectedActivity(int detectIntervalMiliseconds);


    public Observable<LocationSettingsResult> checkLocationSettings(final LocationSettingsRequest locationRequest);

    public Observable<GoogleApiClient> getGoogleApiClientObservable(Api... apis);

//    public static <T extends Result> Observable<T> fromPendingResult(PendingResult<T> result);
}
