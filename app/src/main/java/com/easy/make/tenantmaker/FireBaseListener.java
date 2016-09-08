package com.easy.make.tenantmaker;

import android.support.v4.util.ArrayMap;

import com.easy.make.tenantmaker.listeners.FileUploadListeners;
import com.easy.make.tenantmaker.listeners.ResponseCallback;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by ravi on 12/08/16.
 */
public class FireBaseListener {

    public static void fireBaseSetValueListener(final ResponseCallback responseCallback, ArrayMap map, DatabaseReference databaseReference){
        databaseReference.push().setValue(map, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError == null){
                    responseCallback.onSuccess("Success");
                } else {
                    responseCallback.onErrror(databaseError);
                }
            }
        });
    }

    public static void fireBaseUpdateListener(final ResponseCallback responseCallback, final ArrayMap map, DatabaseReference databaseReference, String queryItem){
        Query query = databaseReference.orderByChild(queryItem).equalTo((String) map.get(queryItem));
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child:dataSnapshot.getChildren()){
                    dataSnapshot.getRef().child(child.getKey()).updateChildren(map, new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                            if (databaseError == null){
                                responseCallback.onSuccess("Success");
                            } else {
                                responseCallback.onErrror(databaseError);
                            }
                        }
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                responseCallback.onErrror(databaseError);
            }
        });
    }

    public static void fireBaseUploadFile(final FileUploadListeners fileUploadListeners, String path, DatabaseReference databaseReference) {

    }

}
