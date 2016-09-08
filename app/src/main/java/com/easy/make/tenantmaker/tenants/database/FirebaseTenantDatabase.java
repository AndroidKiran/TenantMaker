package com.easy.make.tenantmaker.tenants.database;

import com.easy.make.core.tenant.data.model.Tenant;
import com.easy.make.core.tenant.data.model.Tenants;
import com.easy.make.core.tenant.database.TenantDatabase;
import com.easy.make.core.user.data.model.User;
import com.easy.make.tenantmaker.rx.FirebaseObservableListeners;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by ravi on 29/08/16.
 */
public class FirebaseTenantDatabase implements TenantDatabase {

    private static final int DEFAULT_LIMIT = 1000;

    private final DatabaseReference tenantDB;
    private final FirebaseObservableListeners firebaseObservableListeners;

    public FirebaseTenantDatabase(FirebaseDatabase firebaseDatabase, FirebaseObservableListeners firebaseObservableListeners) {
        tenantDB = firebaseDatabase.getReference("tenants");
        this.firebaseObservableListeners = firebaseObservableListeners;
    }

    @Override
    public Observable<List<String>> observeTenantIdsFor(User user) {
        return null;
//        return firebaseObservableListeners.listenToValueEvents(tenantDB.child(user.getId()).limitToLast(DEFAULT_LIMIT), getKeys());
    }

    @Override
    public Observable<Tenant> readTenantFrom(String uid, User user) {
        return null;
//        return firebaseObservableListeners.listenToSingleValueEvents(tenantDB.child(user.getId()).child(uid), as(Tenant.class));
    }

//    @Override
//    public Observable<Tenants> observeTenantsFor(User user) {
//        return firebaseObservableListeners.listenToValueEvents(tenantDB.child(user.getId()), getTenants());
//    }

    @Override
    public Observable<Tenants> observeTenantsFor(User user) {
        return firebaseObservableListeners.listenToValueEvents(tenantDB.limitToLast(DEFAULT_LIMIT), getTenants(user));
    }

    @Override
    public Observable<Tenant> writeTenant(Tenant newTenant) {
        return firebaseObservableListeners.setValue(newTenant, tenantDB.push(), newTenant);
    }


//    private <T> Func1<DataSnapshot, T> as(final Class<T> tClass) {
//        return new Func1<DataSnapshot, T>() {
//            @Override
//            public T call(DataSnapshot dataSnapshot) {
//                return dataSnapshot.getValue(tClass);
//            }
//        };
//    }
//
//    private static Func1<DataSnapshot, List<String>> getKeys() {
//        return new Func1<DataSnapshot, List<String>>() {
//            @Override
//            public List<String> call(DataSnapshot dataSnapshot) {
//                List<String> keys = new ArrayList<>();
//                if (dataSnapshot.hasChildren()) {
//                    Iterable<DataSnapshot> children = dataSnapshot.getChildren();
//                    for (DataSnapshot child : children) {
//                        keys.add(child.getKey());
//                    }
//                }
//                return keys;
//            }
//        };
//    }

//    private static Func1<DataSnapshot, Tenants> getTenants() {
//        return new Func1<DataSnapshot, Tenants>() {
//            @Override
//            public Tenants call(DataSnapshot dataSnapshot) {
//                List<Tenant> tenants = new ArrayList<Tenant>();
//                if (dataSnapshot.hasChildren()) {
//                    Iterable<DataSnapshot> children = dataSnapshot.getChildren();
//                    for (DataSnapshot child : children) {
//                        tenants.add(child.getValue(Tenant.class));
//                    }
//                }
//                return new Tenants(tenants);
//            }
//        };
//    }

    private static Func1<DataSnapshot, Tenants> getTenants(final User user) {
        return new Func1<DataSnapshot, Tenants>() {
            @Override
            public Tenants call(DataSnapshot dataSnapshot) {
                List<Tenant> tenants = new ArrayList<Tenant>();
                if (dataSnapshot.hasChildren()) {
                    Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                    for (DataSnapshot child : children) {
                        Tenant tenant = (Tenant) child.getValue(Tenant.class);
                        if (tenant.getOwnId().equals(user.getId())){
                            tenants.add(tenant);
                        }
                    }
                }
                return new Tenants(tenants);
            }
        };
    }


}



