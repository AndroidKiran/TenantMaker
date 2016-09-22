package com.easy.make.tenantmaker.core.user.database;


import com.easy.make.tenantmaker.core.user.data.model.User;
import com.easy.make.tenantmaker.core.user.data.model.Users;

import rx.Observable;

public interface UserDatabase {

    Observable<Users> observeUsers();

    Observable<User> readUserFrom(String userId);

    void writeCurrentUser(User user);

    Observable<User> observeUser(String userId);

}
