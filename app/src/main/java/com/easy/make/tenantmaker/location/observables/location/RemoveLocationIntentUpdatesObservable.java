package com.easy.make.tenantmaker.location.observables.location;

import android.app.PendingIntent;
import android.content.Context;

import com.easy.make.tenantmaker.location.observables.BaseLocationObservable;
import com.easy.make.tenantmaker.location.observables.StatusException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationServices;

import rx.Observable;
import rx.Observer;

public class RemoveLocationIntentUpdatesObservable extends BaseLocationObservable<Status> {
    private final PendingIntent intent;

    public static Observable<Status> createObservable(Context ctx, PendingIntent intent) {
        return Observable.create(new RemoveLocationIntentUpdatesObservable(ctx, intent));
    }

    private RemoveLocationIntentUpdatesObservable(Context ctx, PendingIntent intent) {
        super(ctx);
        this.intent = intent;
    }

    @Override
    protected void onGoogleApiClientReady(GoogleApiClient apiClient, final Observer<? super Status> observer) {
        LocationServices.FusedLocationApi.removeLocationUpdates(apiClient, intent)
                .setResultCallback(new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        if (!status.isSuccess()) {
                            observer.onError(new StatusException(status));
                        } else {
                            observer.onNext(status);
                            observer.onCompleted();
                        }
                    }
                });
    }
}
