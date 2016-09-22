package com.easy.make.tenantmaker.core.login.database;


import com.easy.make.tenantmaker.core.login.data.model.Authentication;

import rx.Observable;

public interface AuthDatabase {

    Observable<Authentication> readAuthentication();

    Observable<Authentication> loginWithGoogle(String idToken);

}
