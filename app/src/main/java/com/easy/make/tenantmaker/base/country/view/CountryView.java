package com.easy.make.tenantmaker.base.country.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.easy.make.tenantmaker.R;
import com.easy.make.tenantmaker.core.country.model.Country;
import com.novoda.notils.caster.Views;

import java.util.Locale;

/**
 * Created by ravi on 01/10/16.
 */

public class CountryView extends RelativeLayout {

    private int layoutResId;
    private AppCompatImageView flag;
    private AppCompatTextView name;

    public CountryView(Context context) {
        super(context);
    }

    public CountryView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs != null) {
            int[] attrsArray = {
                    android.R.attr.layout
            };
            TypedArray array = context.obtainStyledAttributes(attrs, attrsArray);
            layoutResId = array.getResourceId(0, R.layout.merge_country_item);
            array.recycle();
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        View.inflate(getContext(), layoutResId, this);
        this.flag = Views.findById(this, R.id.country_flag);
        this.name = Views.findById(this, R.id.country_name);
    }

    public void display(Country country){
//        System.out.println(country.getIsoCode()+":"+country.getDialingCode());
        flag.setImageResource(getFlagDrawable(country));
        name.setText(getCountryName(country));
    }

    private String getCountryName(Country country){
        return new Locale(getResources().getConfiguration().locale.getLanguage(),
                country.getIsoCode()).getDisplayCountry();
    }

    private int getFlagDrawable(Country country){
        String drawableName = country.getIsoCode().toLowerCase(Locale.ENGLISH) + "_flag";
        return getMipmapResId(getContext(), drawableName);
    }

    public static int getMipmapResId(Context context, String drawableName) {
        return context.getResources().getIdentifier(
                drawableName.toLowerCase(Locale.ENGLISH), "mipmap", context.getPackageName());
    }
}
