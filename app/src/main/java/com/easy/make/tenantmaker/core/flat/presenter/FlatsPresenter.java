package com.easy.make.tenantmaker.core.flat.presenter;

import com.easy.make.tenantmaker.core.analytics.Analytics;
import com.easy.make.tenantmaker.core.analytics.ErrorLogger;
import com.easy.make.tenantmaker.core.flat.displayer.FlatsDisplayer;
import com.easy.make.tenantmaker.core.flat.model.Flat;
import com.easy.make.tenantmaker.core.flat.model.Flats;
import com.easy.make.tenantmaker.core.flat.service.FlatService;
import com.easy.make.tenantmaker.core.login.data.model.Authentication;
import com.easy.make.tenantmaker.core.login.service.LoginService;
import com.easy.make.tenantmaker.core.navigation.Navigator;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by ravi on 22/10/16.
 */

public class FlatsPresenter {

    private final LoginService loginService;
    private final FlatService flatService;
    private final FlatsDisplayer flatsDisplayer;
    private final Analytics analytics;
    private final Navigator navigator;
    private final ErrorLogger errorLogger;
    private Subscription subscription;

    public FlatsPresenter(
            LoginService loginService,
            FlatService flatService,
            FlatsDisplayer flatsDisplayer,
            Analytics analytics,
            Navigator navigator,
            ErrorLogger errorLogger
    ) {
        this.loginService = loginService;
        this.flatService = flatService;
        this.flatsDisplayer = flatsDisplayer;
        this.analytics = analytics;
        this.navigator = navigator;
        this.errorLogger = errorLogger;
    }

    public void startPresenting(){
        flatsDisplayer.attach(flatInteractionListener);
        subscription = loginService.getAuthentication()
                .filter(successfullyAuthenticated())
                .flatMap(getAllFlats())
                .subscribe(new Action1<Flats>() {
                    @Override
                    public void call(Flats flats) {
                        if (flats.size() != 0){
                            flatsDisplayer.display(flats);
                        }
                    }
                });
    }

    public void stopPresenting() {
        subscription.unsubscribe();
        flatsDisplayer.detach(flatInteractionListener);
    }


    private Func1<Authentication, Boolean> successfullyAuthenticated() {
        return new Func1<Authentication, Boolean>() {
            @Override
            public Boolean call(Authentication authentication) {
                return authentication.isSuccess();
            }
        };
    }

    private Func1<Authentication, Observable<Flats>> getAllFlats(){
        return new Func1<Authentication, Observable<Flats>>() {
            @Override
            public Observable<Flats> call(Authentication authentication) {
                return flatService.getFlats(authentication.getUser());
            }
        };
    }

    private final FlatsDisplayer.FlatInteractionListener flatInteractionListener = new FlatsDisplayer.FlatInteractionListener() {
        @Override
        public void onFlatSelected(Flat flat) {

        }

        @Override
        public void onCreateFlat() {

        }
    };
}
