package com.easy.make.core.flat.service;

import com.easy.make.core.database.DatabaseResult;
import com.easy.make.core.flat.model.Flat;
import com.easy.make.core.flat.model.Flats;
import com.easy.make.core.user.data.model.User;

import rx.Observable;

/**
 * Created by ravi on 11/09/16.
 */
public interface FlatService {

    Observable<Flats> getFlats(User user);

    Observable<DatabaseResult<Flat>> createNewFlat(Flat newFlat, User user);
}
