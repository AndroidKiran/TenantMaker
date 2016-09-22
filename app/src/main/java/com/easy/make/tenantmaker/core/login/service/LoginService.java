package com.easy.make.tenantmaker.core.login.service;


import com.easy.make.tenantmaker.core.login.data.model.Authentication;

import rx.Observable;

public interface LoginService {

    Observable<Authentication> getAuthentication();

    void loginWithGoogle(String idToken);

}
