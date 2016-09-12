package com.easy.make.core.flat.presenter;

import com.easy.make.core.analytics.Analytics;
import com.easy.make.core.analytics.ErrorLogger;
import com.easy.make.core.database.DatabaseResult;
import com.easy.make.core.flat.displayer.NewFlatDisplayer;
import com.easy.make.core.flat.model.Flat;
import com.easy.make.core.flat.service.FlatService;
import com.easy.make.core.login.data.model.Authentication;
import com.easy.make.core.login.service.LoginService;
import com.easy.make.core.navigation.Navigator;
import com.easy.make.core.user.data.model.User;

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

    private CompositeSubscription subscriptions = new CompositeSubscription();
    private User user;


    public NewFlatPresenter(NewFlatDisplayer newFlatDisplayer,
                            FlatService flatService,
                            LoginService  loginService,
                            Navigator navigator,
                            ErrorLogger errorLogger,
                            Analytics analytics){
        this.newFlatDisplayer = newFlatDisplayer;
        this.flatService = flatService;
        this.loginService = loginService;
        this.navigator = navigator;
        this.errorLogger = errorLogger;
        this.analytics = analytics;
    }

    public void startPresenting(){
        newFlatDisplayer.attach(flatCreationListener);
        subscriptions.add(loginService.getAuthentication().subscribe(new Action1<Authentication>() {
            @Override
            public void call(Authentication authentication) {
                user = authentication.getUser();
            }
        }));
    }

    public void stopPresenting(){
        newFlatDisplayer.detach(flatCreationListener);
        subscriptions.clear();
        subscriptions = new CompositeSubscription();
    }

    final NewFlatDisplayer.FlatCreationListener flatCreationListener = new NewFlatDisplayer.FlatCreationListener() {
        @Override
        public void onCreateFlatClicked(Flat flat) {
            newFlatDisplayer.showProgress();
            flatService.createNewFlat(flat, user).subscribe(new Action1<DatabaseResult<Flat>>() {
                @Override
                public void call(DatabaseResult<Flat> flatDatabaseResult) {
                    newFlatDisplayer.dismissProgress();
                }
            });
        }

        @Override
        public void onCancel() {
            navigator.toParent();
        }
    };
}
