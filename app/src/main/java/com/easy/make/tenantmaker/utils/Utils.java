package com.easy.make.tenantmaker.utils;

import com.easy.make.tenantmaker.modules.BottomSheetItem;

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
}
