package com.easy.make.core.flat.service;

import com.easy.make.core.database.DatabaseResult;
import com.easy.make.core.flat.database.FlatDatabase;
import com.easy.make.core.flat.model.Flat;
import com.easy.make.core.flat.model.Flats;
import com.easy.make.core.user.data.model.User;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by ravi on 11/09/16.
 */
public class PersistedFlatService implements FlatService {

    private final FlatDatabase flatDatabase;

    public PersistedFlatService(FlatDatabase flatDatabase) {
        this.flatDatabase = flatDatabase;
    }


    @Override
    public Observable<Flats> getFlats(User user) {
        return flatDatabase.observeFlatsFor(user);
    }

    @Override
    public Observable<DatabaseResult<Flat>> createNewFlat(Flat newFlat) {
        return flatDatabase.writeFlat(newFlat)
                .map(new Func1<Flat, DatabaseResult<Flat>>() {
                    @Override
                    public DatabaseResult<Flat> call(Flat flat) {
                        return new DatabaseResult<>(flat);
                    }
                });
    }
}
