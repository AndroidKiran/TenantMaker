package com.easy.make.tenantmaker.base.modules;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ravi on 17/07/16.
 */
public class BottomSheetItem implements Parcelable {

    private int bottomSheetIcon;
    private String bottomSheetItem;

    protected BottomSheetItem(Parcel in) {
        bottomSheetIcon = in.readInt();
        bottomSheetItem = in.readString();
    }

    public BottomSheetItem(int icon, String value){
        this.bottomSheetIcon = icon;
        this.bottomSheetItem = value;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(bottomSheetIcon);
        dest.writeString(bottomSheetItem);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<BottomSheetItem> CREATOR = new Creator<BottomSheetItem>() {
        @Override
        public BottomSheetItem createFromParcel(Parcel in) {
            return new BottomSheetItem(in);
        }

        @Override
        public BottomSheetItem[] newArray(int size) {
            return new BottomSheetItem[size];
        }
    };

    public int getBottomSheetIcon() {
        return bottomSheetIcon;
    }

    public void setBottomSheetIcon(int bottomSheetIcon) {
        this.bottomSheetIcon = bottomSheetIcon;
    }

    public String getBottomSheetItem() {
        return bottomSheetItem;
    }

    public void setBottomSheetItem(String bottomSheetItem) {
        this.bottomSheetItem = bottomSheetItem;
    }
}
