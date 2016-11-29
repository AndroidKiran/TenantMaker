package com.easy.make.tenantmaker.core.flat.presenter;

import android.os.Bundle;

import com.easy.make.tenantmaker.core.Utils.PreferenceService;
import com.easy.make.tenantmaker.core.analytics.Analytics;
import com.easy.make.tenantmaker.core.analytics.ErrorLogger;
import com.easy.make.tenantmaker.core.database.DatabaseResult;
import com.easy.make.tenantmaker.core.flat.displayer.NewFlatDisplayer;
import com.easy.make.tenantmaker.core.flat.model.Flat;
import com.easy.make.tenantmaker.core.flat.service.FlatService;
import com.easy.make.tenantmaker.core.login.data.model.Authentication;
import com.easy.make.tenantmaker.core.login.service.LoginService;
import com.easy.make.tenantmaker.core.navigation.Navigator;
import com.easy.make.tenantmaker.core.user.data.model.User;

import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by ravi on 11/09/16.
 */
public class NewFlatPresenter {

    private final NewFlatDisplayer newFlatDisplayer;
    private final FlatService flatService;
    private final Navigator navigator;
    private final ErrorLogger errorLogger;
    private final Analytics analytics;
    private final LoginService loginService;
    private final PreferenceService preferenceService;

    private CompositeSubscription subscriptions = new CompositeSubscription();
    private User user;


    public NewFlatPresenter(NewFlatDisplayer newFlatDisplayer,
                            FlatService flatService,
                            LoginService loginService,
                            Navigator navigator,
                            ErrorLogger errorLogger,
                            Analytics analytics,
                            PreferenceService preferenceService) {

        this.newFlatDisplayer = newFlatDisplayer;
        this.flatService = flatService;
        this.loginService = loginService;
        this.navigator = navigator;
        this.errorLogger = errorLogger;
        this.analytics = analytics;
        this.preferenceService = preferenceService;
    }

    public void startPresenting() {
        newFlatDisplayer.attach(flatCreationListener);
        newFlatDisplayer.toggleViewVisibility(preferenceService);
        subscriptions.add(loginService.getAuthentication().subscribe(new Action1<Authentication>() {
            @Override
            public void call(Authentication authentication) {
                user = authentication.getUser();
            }
        }));
    }

    public void stopPresenting() {
        newFlatDisplayer.detach(flatCreationListener);
        subscriptions.clear();
        subscriptions = new CompositeSubscription();
    }

    final NewFlatDisplayer.FlatCreationListener flatCreationListener = new NewFlatDisplayer.FlatCreationListener() {
        @Override
        public void onCreateFlatClicked(Flat flat) {
            newFlatDisplayer.showProgress();
            flatService.createNewFlat(flat).subscribe(new Action1<DatabaseResult<Flat>>() {
                @Override
                public void call(DatabaseResult<Flat> flatDatabaseResult) {
                    newFlatDisplayer.dismissProgress();
                    if (!preferenceService.getFirstFlowValue()) {
                        preferenceService.setFirstFlowPreference(true);
                        navigator.toMain();
                    } else {
                    }
                }
            });
        }

        @Override
        public void onCancel() {
            navigator.toParent();
        }

//        @Override
//        public void onAddressTextChanged(String address) {
//            if (!TextUtils.isEmpty(address)) {
//                subscribeGeocoder(address);
//            }
//        }
    };

//    private void subscribeGeocoder(final String address) {
//        geoCodeSubscription = ReactiveNetwork.observeInternetConnectivity()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<Boolean>() {
//                    @Override
//                    public void call(Boolean isConnectedToInternet) {
//                        if (isConnectedToInternet) {
//                            locationProvider.getGeocodeObservable(address, 2)
//                                    .map(getAddress())
//                                    .doOnError(new Action1<Throwable>() {
//                                        @Override
//                                        public void call(Throwable throwable) {
//
//                                        }
//                                    })
//                                    .subscribe(new Action1<Address>() {
//                                        @Override
//                                        public void call(Address address) {
//                                            if (address != null) {
//                                                newFlatDisplayer.setMarker(address);
//                                                setLatitude(address.getLatitude());
//                                                setLongitude(address.getLongitude());
//                                            }
//                                        }
//
//
//                                    });
//                        }
//                    }
//                });
//
//    }


//    private Func1<List<Address>, Address> getAddress() {
//        return new Func1<List<Address>, Address>() {
//            @Override
//            public Address call(List<Address> addresses) {
//                return addresses != null && !addresses.isEmpty() ? addresses.get(0) : null;
//            }
//        };
//    }

    public void onFragmentInteraction(Bundle bundle) {
        newFlatDisplayer.onFragmentInteraction(bundle);
    }


//    public double getLatitude() {
//        return latitude;
//    }
//
//    public void setLatitude(double latitude) {
//        this.latitude = latitude;
//    }
//
//    public double getLongitude() {
//        return longitude;
//    }
//
//    public void setLongitude(double longitude) {
//        this.longitude = longitude;
//    }

//    public Flat updateFlat(Flat flat) {
//        flat.setOwnId(user.getId());
//        flat.setLongitude(getLongitude());
//        flat.setLatitude(getLatitude());
//        return flat;
//    }
}
