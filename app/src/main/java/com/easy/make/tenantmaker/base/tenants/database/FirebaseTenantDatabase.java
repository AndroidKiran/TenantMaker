package com.easy.make.tenantmaker.base.tenants.database;

import com.easy.make.tenantmaker.base.rx.FirebaseObservableListeners;
import com.easy.make.tenantmaker.core.tenant.data.model.Tenant;
import com.easy.make.tenantmaker.core.tenant.data.model.Tenants;
import com.easy.make.tenantmaker.core.tenant.database.TenantDatabase;
import com.easy.make.tenantmaker.core.user.data.model.User;
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
    public Observable<Tenants> observeTenantsFor(User user) {
        return firebaseObservableListeners.listenToValueEvents(tenantDB.child(user.getId()).orderByChild(Tenant.FIRST_NAME).limitToFirst(DEFAULT_LIMIT), getTenants());
    }

    @Override
    public Observable<Tenants> observeTenantsFor(User user, String query) {
        return firebaseObservableListeners.listenToValueEvents(tenantDB.child(user.getId()).orderByChild(Tenant.FIRST_NAME).equalTo(query).limitToLast(DEFAULT_LIMIT), getTenants());
    }

    @Override
    public Observable<Tenant> writeTenant(Tenant newTenant, User user) {
        return firebaseObservableListeners.setValue(newTenant, tenantDB.child(user.getId()).child(newTenant.getId()), newTenant);
    }

    private static Func1<DataSnapshot, Tenants> getTenants() {
        return new Func1<DataSnapshot, Tenants>() {
            @Override
            public Tenants call(DataSnapshot dataSnapshot) {
                List<Tenant> tenants = new ArrayList<Tenant>();
                if (dataSnapshot.hasChildren()) {
                    Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                    for (DataSnapshot child : children) {
                        Tenant tenant = (Tenant) child.getValue(Tenant.class);
                        tenant.setId(child.getKey());
                        tenants.add(tenant);
                    }
                }
                return new Tenants(tenants);
            }
        };
    }


}



