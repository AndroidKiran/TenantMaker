package com.easy.make.tenantmaker.location.observables.activity;

import android.content.Context;

import com.easy.make.tenantmaker.location.observables.BaseObservable;
import com.google.android.gms.location.ActivityRecognition;



public abstract class BaseActivityObservable<T> extends BaseObservable<T> {
    protected BaseActivityObservable(Context ctx) {
        super(ctx, ActivityRecognition.API);
    }
}
