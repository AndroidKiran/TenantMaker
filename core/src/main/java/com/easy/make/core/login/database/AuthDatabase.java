package com.easy.make.core.login.database;


import com.easy.make.core.login.data.model.Authentication;

import rx.Observable;

public interface AuthDatabase {

    Observable<Authentication> readAuthentication();

    Observable<Authentication> loginWithGoogle(String idToken);

}
