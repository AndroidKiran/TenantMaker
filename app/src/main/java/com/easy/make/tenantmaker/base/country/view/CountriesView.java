package com.easy.make.tenantmaker.base.country.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.easy.make.tenantmaker.R;
import com.easy.make.tenantmaker.base.component.DividerItemDecoration;
import com.easy.make.tenantmaker.base.component.TextWatcherAdapter;
import com.easy.make.tenantmaker.base.component.text.ClearAbleEditText;
import com.easy.make.tenantmaker.core.country.displayer.CountryDisplayer;
import com.easy.make.tenantmaker.core.country.model.Countries;
import com.novoda.notils.caster.Views;

/**
 * Created by ravi on 01/10/16.
 */

public class CountriesView extends LinearLayout implements CountryDisplayer {

    private CountryAdapter countryAdapter;
    private RecyclerView recyclerView;
    private CountryInteractionListener countryInteractionListener;
    private ClearAbleEditText searchView;
//    private CountriesAdapter countriesAdapter;

    public CountriesView(Context context, AttributeSet attrs) {
        super(context, attrs);
        countryAdapter = new CountryAdapter(LayoutInflater.from(getContext()), getContext());
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        View.inflate(getContext(), R.layout.merge_countries_view, this);
        initControls();
        setRecyclerView();
        setAdpater();
    }

    private void initControls(){
        searchView = Views.findById(this, R.id.search_view);
    }

    private void setRecyclerView() {
        recyclerView = Views.findById(this, R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        Drawable dividerDrawable = ContextCompat.getDrawable(getContext(), R.drawable.seperator_72);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(dividerDrawable);
        recyclerView.addItemDecoration(dividerItemDecoration);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
    }

    public void setAdpater(){
        recyclerView.setAdapter(countryAdapter);

    }

//    public void setAdpater(FirebaseDatabase firebaseDatabase){
//        countriesAdapter = new CountriesAdapter(Country.class, R.layout.country_item_layout, CountriesAdapter.CountryViewHolder.class, firebaseDatabase.getReference("countries"));
//        recyclerView.setAdapter(countriesAdapter);
//
//    }

    @Override
    public void attach(CountryInteractionListener countryInteractionListener) {
        this.countryInteractionListener = countryInteractionListener;
        countryAdapter.attach(countryInteractionListener);
        searchView.addTextChangedListener(textWatcher);
        this.countryInteractionListener = countryInteractionListener;
    }

    @Override
    public void detach(CountryInteractionListener countryInteractionListener) {
        this.countryInteractionListener = null;
        countryAdapter.detach(countryInteractionListener);
        searchView.addTextChangedListener(null);
    }

    @Override
    public void display(Countries countries) {
        countryAdapter.setData(countries);
    }

   TextWatcherAdapter.TextWatcherListener onQueryTextListener = new TextWatcherAdapter.TextWatcherListener() {
       @Override
       public void onTextChanged(EditText view, String text) {

       }
   };

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            countryAdapter.onCountryFilter(charSequence.toString());
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };
}
