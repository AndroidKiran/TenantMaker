package com.easy.make.tenantmaker.base.utils;

import com.easy.make.tenantmaker.core.Utils.GsonService;
import com.easy.make.tenantmaker.core.user.data.model.User;
import com.google.gson.Gson;

/**
 * Created by ravi on 12/09/16.
 */
public class GsonServiceImpl implements GsonService {

    private final Gson gson;

    public GsonServiceImpl(){
        gson = new Gson();
    }

    @Override
    public String toString(User user) {
        return gson.toJson(user);    }

    @Override
    public User toUser(String json) {
        return gson.fromJson(json, User.class);
    }
}
