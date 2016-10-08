package com.easy.make.tenantmaker.base.country.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.easy.make.tenantmaker.core.country.displayer.CountryDisplayer;
import com.easy.make.tenantmaker.core.country.model.Country;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;

/**
 * Created by ravi on 01/10/16.
 */

public class CountriesAdapter extends FirebaseRecyclerAdapter<Country, CountriesAdapter.CountryViewHolder> {

    private CountryDisplayer.CountryInteractionListener countryInteractionListener;


    public CountriesAdapter(Class<Country> modelClass, int modelLayout, Class<CountriesAdapter.CountryViewHolder> viewHolderClass, Query ref) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        setHasStableIds(true);
    }

    @Override
    protected void populateViewHolder(CountryViewHolder countryViewHolder, Country country, int position) {
        countryViewHolder.bind(country, countrySelectionListener);
    }

    public void attach(CountryDisplayer.CountryInteractionListener countryInteractionListener) {
        this.countryInteractionListener = countryInteractionListener;
    }

    public void detach(CountryDisplayer.CountryInteractionListener countryInteractionListener) {
        this.countryInteractionListener = countryInteractionListener;
    }

    CountryViewHolder.CountrySelectionListener countrySelectionListener = new CountryViewHolder.CountrySelectionListener() {
        @Override
        public void onCountrySelected(Country country) {
            countryInteractionListener.onCountrySelected(country);
        }
    };

    public static class CountryViewHolder extends RecyclerView.ViewHolder {

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
}
