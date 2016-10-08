package com.easy.make.tenantmaker.base.country.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.easy.make.tenantmaker.R;
import com.easy.make.tenantmaker.core.country.displayer.CountryDisplayer;
import com.easy.make.tenantmaker.core.country.model.Countries;
import com.easy.make.tenantmaker.core.country.model.Country;

import java.util.ArrayList;

/**
 * Created by ravi on 01/10/16.
 */

public class CountryAdapter extends RecyclerView.Adapter<CountryViewHolder> implements Filterable {


    private final LayoutInflater inflater;
    private final Context context;
    private Countries countriesFilterList;
    private Countries countries;
    private CountryDisplayer.CountryInteractionListener countryInteractionListener;
    private CountryFilter countryFilter;

    public CountryAdapter(LayoutInflater inflater, Context context) {
        this.inflater = inflater;
        this.context = context;
        this.countries = new Countries(new ArrayList<Country>());
        this.countriesFilterList = countries;
//        setHasStableIds(true);
    }

    public void setData(Countries countries) {
        this.countries = countries;
        this.countries.sort(context);
        this.countriesFilterList = countries;
        this.countriesFilterList.sort(context);
        notifyItemRangeInserted(getItemCount(), countries.size());
    }

    @Override
    public CountryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CountryView countryView = (CountryView) inflater.inflate(R.layout.country_item_layout, parent, false);
        return new CountryViewHolder(countryView);
    }

    @Override
    public void onBindViewHolder(CountryViewHolder holder, int position) {
        System.out.println("postion ====" + position + "        " + this.countries.size());
        holder.bind(this.countries.getCountry(position), countrySelectionListener);
    }

    @Override
    public int getItemCount() {
        return this.countries.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public void attach(CountryDisplayer.CountryInteractionListener countryInteractionListener) {
        this.countryInteractionListener = countryInteractionListener;
    }

    public void detach(CountryDisplayer.CountryInteractionListener countryInteractionListener) {
        this.countryInteractionListener = countryInteractionListener;
    }

    public void onCountryFilter(String query){
        getFilter().filter(query);
    }

    CountryViewHolder.CountrySelectionListener countrySelectionListener = new CountryViewHolder.CountrySelectionListener() {
        @Override
        public void onCountrySelected(Country country) {
            for (int i = 0; i < countries.size(); i++) {
                Country country1 = countries.getCountry(i);
                System.out.println(country1.getIsoCode() + ":" + country1.getDialingCode() + "\n");
            }
            countryInteractionListener.onCountrySelected(country);
        }
    };

    @Override
    public Filter getFilter() {
        if (countryFilter == null) {
            countryFilter = new CountryFilter();
        }
        return countryFilter;
    }

    public class CountryFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            constraint = constraint.toString().toLowerCase();
            FilterResults result = new FilterResults();
            if (constraint != null && constraint.toString().length() > 0) {
                Countries countries = new Countries(new ArrayList<Country>());

                for (Country country : countriesFilterList.getCountriesList()) {
                    if (country.getCountryName(context).toLowerCase().contains(constraint))
                        countries.addCountry(country);
                }
                result.count = countries.size();
                result.values = countries;
            } else {
                synchronized (this) {
                    result.values = countriesFilterList;
                    result.count = countriesFilterList.size();
                }
            }
            return result;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            countries = (Countries) filterResults.values;
            notifyDataSetChanged();
        }
    }
}
