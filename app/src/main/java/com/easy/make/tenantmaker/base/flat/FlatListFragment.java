package com.easy.make.tenantmaker.base.flat;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by ravi on 10/09/16.
 */
public class FlatListFragment extends Fragment {

    public static final String TAG = "FlatListFragment";


    public static FlatListFragment newInstance(Bundle bundle) {
        FlatListFragment instance = new FlatListFragment();
        instance.setArguments(bundle);
        return instance;
    }
}
