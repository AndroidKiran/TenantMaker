package com.easy.make.tenantmaker.core.country.model;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.Locale;

/**
 * Created by ravi on 30/09/16.
 */

public class Country implements Parcelable {

    private String isoCode;
    private String dialingCode;

    public Country() {

    }

    public Country(String isoCode, String dialingCode) {
        this.isoCode = isoCode;
        this.dialingCode = dialingCode;
    }

    protected Country(Parcel in) {
        isoCode = in.readString();
        dialingCode = in.readString();
    }

    public static final Creator<Country> CREATOR = new Creator<Country>() {
        @Override
        public Country createFromParcel(Parcel in) {
            return new Country(in);
        }

        @Override
        public Country[] newArray(int size) {
            return new Country[size];
        }
    };

    public String getIsoCode() {
        return isoCode;
    }

    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
    }

    public String getDialingCode() {
        return dialingCode;
    }

    public void setDialingCode(String dialingCode) {
        this.dialingCode = dialingCode;
    }

    public String getCountryName(Context con) {
        return new Locale(con.getResources().getConfiguration().locale.getLanguage(), isoCode).getDisplayCountry();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(isoCode);
        parcel.writeString(dialingCode);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Country)) return false;

        Country country = (Country) o;

        if (isoCode != null ? !isoCode.equals(country.isoCode) : country.isoCode != null)
            return false;
        return dialingCode != null ? dialingCode.equals(country.dialingCode) : country.dialingCode == null;

    }

    @Override
    public int hashCode() {
        int result = isoCode != null ? isoCode.hashCode() : 0;
        result = 31 * result + (dialingCode != null ? dialingCode.hashCode() : 0);
        return result;
    }
}
