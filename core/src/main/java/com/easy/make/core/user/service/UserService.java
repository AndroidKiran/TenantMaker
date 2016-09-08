package com.easy.make.core.user.service;

import com.easy.make.core.user.data.model.User;
import com.easy.make.core.user.data.model.Users;

import rx.Observable;

public interface UserService {

    Observable<Users> getAllUsers();

    Observable<User> getUser(String userId);

}
