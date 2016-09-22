package com.easy.make.tenantmaker.base.flat.database;

import com.easy.make.tenantmaker.base.rx.FirebaseObservableListeners;
import com.easy.make.tenantmaker.core.flat.database.FlatDatabase;
import com.easy.make.tenantmaker.core.flat.model.Flat;
import com.easy.make.tenantmaker.core.flat.model.Flats;
import com.easy.make.tenantmaker.core.user.data.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by ravi on 11/09/16.
 */
public class FirebaseFlatDatabase implements FlatDatabase {

    private static final int DEFAULT_LIMIT = 1;
    private final FirebaseObservableListeners firebaseObservableListeners;
    private final DatabaseReference flatDb;

    public FirebaseFlatDatabase(FirebaseDatabase firebaseDatabase, FirebaseObservableListeners firebaseObservableListeners){
        this.flatDb = firebaseDatabase.getReference("flats");
        this.firebaseObservableListeners = firebaseObservableListeners;
    }


    @Override
    public Observable<Flats> observeFlatsFor(User user) {
        return firebaseObservableListeners.listenToValueEvents(flatDb.orderByChild(Flat.OWNER_ID).equalTo(user.getId()).limitToFirst(DEFAULT_LIMIT), getFlats());
    }

    @Override
    public Observable<Flat> writeFlat(Flat newFlat) {
        return firebaseObservableListeners.setValue(newFlat, flatDb.push(), newFlat);
    }

    private static Func1<DataSnapshot, Flats> getFlats(){
        return new Func1<DataSnapshot, Flats>() {
            @Override
            public Flats call(DataSnapshot dataSnapshot) {
                List<Flat> flats = new ArrayList<Flat>();
                if (dataSnapshot.hasChildren()){
                    Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                    for (DataSnapshot child : children){
                        Flat flat = (Flat) child.getValue(Flat.class);
                        flat.setId(child.getKey());
                        flats.add(flat);
                    }
                }
                return new Flats(flats);
            }
        };
    }
}
