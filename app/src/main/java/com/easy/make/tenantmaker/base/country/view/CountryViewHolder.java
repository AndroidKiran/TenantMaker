package com.easy.make.tenantmaker.base.country.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.easy.make.tenantmaker.core.country.model.Country;

/**
 * Created by ravi on 01/10/16.
 */

public class CountryViewHolder extends RecyclerView.ViewHolder {

    public CountryView countryView;

    public CountryViewHolder(CountryView itemView) {
        super(itemView);
        this.countryView = itemView;
    }

    public void bind(final Country country, final CountrySelectionListener countrySelectionListener){
        countryView.display(country);
        countryView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countrySelectionListener.onCountrySelected(country);
            }
        });
    }

    public interface CountrySelectionListener {
        void onCountrySelected(Country country);
    }
}
