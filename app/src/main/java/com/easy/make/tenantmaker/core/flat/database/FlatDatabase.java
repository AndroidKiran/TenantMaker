package com.easy.make.tenantmaker.core.flat.database;

import com.easy.make.tenantmaker.core.flat.model.Flat;
import com.easy.make.tenantmaker.core.flat.model.Flats;
import com.easy.make.tenantmaker.core.user.data.model.User;

import rx.Observable;

/**
 * Created by ravi on 11/09/16.
 */
public interface FlatDatabase {

    Observable<Flats> observeFlatsFor(User user);

    Observable<Flat> writeFlat(Flat newFlat);
}
