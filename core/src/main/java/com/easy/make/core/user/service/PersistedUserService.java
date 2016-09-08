package com.easy.make.core.user.service;


import com.easy.make.core.user.data.model.User;
import com.easy.make.core.user.data.model.Users;
import com.easy.make.core.user.database.UserDatabase;

import rx.Observable;


public class PersistedUserService implements UserService {

    private final UserDatabase userDatabase;

    public PersistedUserService(UserDatabase userDatabase) {
        this.userDatabase = userDatabase;
    }

    @Override
    public Observable<Users> getAllUsers() {
        return userDatabase.observeUsers();
    }

    @Override
    public Observable<User> getUser(String userId) {
        return userDatabase.observeUser(userId);
    }

}
