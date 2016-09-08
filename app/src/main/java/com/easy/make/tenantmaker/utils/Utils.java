package com.easy.make.tenantmaker.utils;

import android.content.Context;

import com.easy.make.tenantmaker.modules.BottomSheetItem;
import com.kbeanie.multipicker.api.CacheLocation;

import java.util.ArrayList;

/**
 * Created by ravi on 18/07/16.
 */
public class Utils {

    public static boolean isEmptyList(ArrayList list){
        if (list != null && list.size() != 0){
            return false;
        }

        return true;
    }

    public interface ItemListener {
        void onItemListener(BottomSheetItem bottomSheetItem);
    }

    public static int getSavedCacheLocation(Context context) {
        AppPreferences preferences = new AppPreferences(context);
        switch (preferences.getCacheLocation()) {
            case 0:
                return CacheLocation.EXTERNAL_STORAGE_APP_DIR;
            case 1:
                return CacheLocation.EXTERNAL_STORAGE_PUBLIC_DIR;
            case 2:
                return CacheLocation.EXTERNAL_CACHE_DIR;
            case 3:
                return CacheLocation.INTERNAL_APP_DIR;
        }
        return CacheLocation.EXTERNAL_STORAGE_APP_DIR;
    }
}
