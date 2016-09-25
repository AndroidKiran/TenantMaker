package com.easy.make.tenantmaker.base;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.google.android.gms.maps.SupportMapFragment;

/**
 * Created by ravi on 22/09/16.
 */
public class MapFragment extends SupportMapFragment {

    public static MapFragment newInstance(FragmentManager fm, int container){
        MapFragment mapFragment = new MapFragment();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(container, mapFragment);
        return mapFragment;
    }

}
