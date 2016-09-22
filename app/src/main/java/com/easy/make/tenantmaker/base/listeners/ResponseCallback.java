package com.easy.make.tenantmaker.base.listeners;


import com.google.firebase.database.DatabaseError;

/**
 * Created by ravi on 06/08/16.
 */
public interface ResponseCallback<BaseModule> {
    void onSuccess(BaseModule T);
    void onErrror(DatabaseError error);
}
