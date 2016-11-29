package com.easy.make.tenantmaker.base.flat.view;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.easy.make.tenantmaker.R;
import com.easy.make.tenantmaker.base.component.materialcomponent.MaterialProgressDialog;
import com.easy.make.tenantmaker.base.country.CountriesDialog;
import com.easy.make.tenantmaker.base.utils.DialogUtils;
import com.easy.make.tenantmaker.base.utils.UtilBundles;
import com.easy.make.tenantmaker.core.Utils.PreferenceService;
import com.easy.make.tenantmaker.core.country.model.Country;
import com.easy.make.tenantmaker.core.flat.displayer.NewFlatDisplayer;
import com.easy.make.tenantmaker.core.flat.model.Flat;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.novoda.notils.caster.Views;

/**
 * Created by ravi on 05/09/16.
 */
public class NewFlatView extends LinearLayout implements NewFlatDisplayer {

    private final Context context;
    private Toolbar toolbar;
    private FlatCreationListener flatCreationListener;
    private AppCompatButton createFlatBtn;
    private TextInputLayout flatNameTextInputLayout;
    private EditText flatNameEditText;
    private TextInputLayout addressTextInputLayout;
    private EditText addressEditText;
    private AppCompatTextView errFlatNameText;
    private AppCompatTextView errAddressText;
    private AppCompatActivity appCompatActivity;
    private MaterialProgressDialog materialProgressDialog;
    private GoogleMap mGoogleMap;
    private SupportMapFragment mapFragment;
    private TextInputLayout cityTextInputLayout;
    private EditText cityEditText;
    private AppCompatTextView errCityText;
    private TextInputLayout pinCodeTextInputLayout;
    private EditText pinCodeEditText;
    private AppCompatTextView errCountryText;
    private TextInputLayout countryTextInputLayout;
    private EditText countryEditText;
    private CountriesDialog countriesDialog;


    public NewFlatView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        setOrientation(VERTICAL);
        setFitsSystemWindows(true);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        View.inflate(getContext(), R.layout.merge_new_flat_view, this);
//        setToolbar();
        initControls();
    }

//    public void setMap() {
//        mapFragment = (SupportMapFragment) getAppCompatActivity().getSupportFragmentManager()
//                .findFragmentById(R.id.map);
//        mapFragment.getMapAsync(onMapReadyCallback);
//    }

//    void setToolbar() {
//        toolbar = Views.findById(this, R.id.toolbar);
//        toolbar.setTitle(R.string.str_flat);
//        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
//
//    }

    void initControls() {
        createFlatBtn = Views.findById(this, R.id.save_flat_btn);

        flatNameTextInputLayout = Views.findById(this, R.id.flat_name);
        flatNameEditText = flatNameTextInputLayout.getEditText();
        errFlatNameText = Views.findById(this, R.id.err_flat_name);

        addressTextInputLayout = Views.findById(this, R.id.street_address);
        addressEditText = addressTextInputLayout.getEditText();
        errAddressText = Views.findById(this, R.id.err_street_address);

        cityTextInputLayout = Views.findById(this, R.id.city);
        cityEditText = cityTextInputLayout.getEditText();
        errCityText = Views.findById(this, R.id.err_city);

        pinCodeTextInputLayout = Views.findById(this, R.id.pincode);
        pinCodeEditText = pinCodeTextInputLayout.getEditText();

        countryTextInputLayout = Views.findById(this, R.id.country);
        countryEditText = Views.findById(this, R.id.country_et);
        errCountryText = Views.findById(this, R.id.err_country);
    }

    @Override
    public void attach(FlatCreationListener flatCreationListener) {
//        toolbar.setNavigationOnClickListener(onNavigationBackClickListener);
        flatNameEditText.addTextChangedListener(textWatcher);
        addressEditText.addTextChangedListener(textWatcher);
        cityEditText.addTextChangedListener(textWatcher);
        pinCodeEditText.addTextChangedListener(textWatcher);
        countryEditText.addTextChangedListener(textWatcher);
        countryEditText.setOnClickListener(onClickListener);
        countryEditText.setOnFocusChangeListener(onFocusChangeListener);
        countryEditText.setKeyListener(null);
        createFlatBtn.setOnClickListener(onClickListener);
        this.flatCreationListener = flatCreationListener;
    }

    @Override
    public void detach(FlatCreationListener flatCreationListener) {
//        toolbar.setNavigationOnClickListener(null);
        createFlatBtn.setOnClickListener(null);
        flatNameEditText.addTextChangedListener(null);
        addressEditText.addTextChangedListener(null);
        cityEditText.addTextChangedListener(null);
        pinCodeEditText.addTextChangedListener(null);
        countryEditText.addTextChangedListener(null);
        countryEditText.setOnClickListener(null);
        countryEditText.setOnFocusChangeListener(null);
        this.flatCreationListener = null;
    }

    @Override
    public void showProgress() {
        materialProgressDialog = new MaterialProgressDialog(getAppCompatActivity());
        DialogUtils.showMaterialProgressDialog(materialProgressDialog, getAppCompatActivity().getString(R.string.str_progress_flat_title), getAppCompatActivity().getString(R.string.str_progress_wait), getAppCompatActivity());
    }

    @Override
    public void dismissProgress() {
        if (materialProgressDialog != null) {
            materialProgressDialog.dismiss();
        }
    }

    @Override
    public void clear() {

    }

    @Override
    public void onFragmentInteraction(Bundle bundle) {
        display((Country) bundle.getParcelable(UtilBundles.EXTRA_BUNDLE));
    }

    private boolean validateForm() {

        boolean validate = true;

        String lastName = flatNameEditText.getText().toString();
        if (TextUtils.isEmpty(lastName)) {
            errFlatNameText.setVisibility(VISIBLE);
            validate = false;
        } else {
            errFlatNameText.setVisibility(GONE);
        }

        String address = addressEditText.getText().toString();
        if (TextUtils.isEmpty(address)) {
            errAddressText.setVisibility(VISIBLE);
            validate = false;
        } else {
            errAddressText.setVisibility(GONE);
        }

        String city = cityEditText.getText().toString();
        if (TextUtils.isEmpty(city)) {
            errCityText.setVisibility(VISIBLE);
            validate = false;
        } else {
            errCityText.setVisibility(GONE);
        }


        String country = countryEditText.getText().toString();
        if (TextUtils.isEmpty(country)) {
            errCountryText.setVisibility(VISIBLE);
            validate = false;
        } else {
            errCountryText.setVisibility(GONE);
        }


        return validate;
    }


    private Flat formToFlat() {
        Flat flat = new Flat();
        flat.setName(flatNameEditText.getText().toString());
        flat.setAddress(toAddress());
        return flat;
    }

    final TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (flatNameEditText.isFocused()) {
                errFlatNameText.setVisibility(s.length() == 0 ? VISIBLE : GONE);
            } else if (addressEditText.isFocused()) {
                errAddressText.setVisibility(s.length() == 10 ? VISIBLE : GONE);
            } else if (cityEditText.isFocused()) {
                errCityText.setVisibility(s.length() < 2 ? VISIBLE : GONE);
            } else if (countryEditText.isFocused()){
                errCountryText.setVisibility(s.length() == 0 ? VISIBLE : GONE);
            }
        }
    };


    private String toAddress(){
        StringBuilder addressStringBuilder = new StringBuilder();

        String address = addressEditText.getText().toString();
        addressStringBuilder.append(!TextUtils.isEmpty(address) ? address : "");

        String city = cityEditText.getText().toString();
        addressStringBuilder.append(!TextUtils.isEmpty(city) ? ","+city : "");

        String country = countryEditText.getText().toString();
        addressStringBuilder.append(!TextUtils.isEmpty(country) ? ","+country : "");

        String pinCode = pinCodeEditText.getText().toString();
        addressStringBuilder.append(!TextUtils.isEmpty(pinCode) ? "Pincode - "+pinCode : "");

        return addressStringBuilder.toString();
    }


    final OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.save_flat_btn:
                    if (validateForm()) {
                        flatCreationListener.onCreateFlatClicked(formToFlat());
                    }
                    break;

                case R.id.country_et:
                    showDialog();
                    break;
            }
        }
    };

    final OnClickListener onNavigationBackClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            flatCreationListener.onCancel();
        }
    };

    public AppCompatActivity getAppCompatActivity() {
        return appCompatActivity;
    }

    public void setAppCompatActivity(AppCompatActivity appCompatActivity) {
        this.appCompatActivity = appCompatActivity;
    }

//    OnMapReadyCallback onMapReadyCallback = new OnMapReadyCallback() {
//        @Override
//        public void onMapReady(GoogleMap googleMap) {
//            mGoogleMap = googleMap;
//        }
//    };

//    @Override
//    public void setMarker(Address address) {
//        if (mGoogleMap != null) {
//            mGoogleMap.clear();
//            LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
//            mGoogleMap.addMarker(new MarkerOptions().position(latLng));
//            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
//        }
//
//    }

    @Override
    public void toggleViewVisibility(PreferenceService preferenceService) {
        if (preferenceService.getFirstFlowValue()) {
            toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        }
    }

    @Override
    public void showDialog() {
        countriesDialog = CountriesDialog.newInstance(getAppCompatActivity().getSupportFragmentManager());
    }

    @Override
    public void dismissDialog() {
        if (countriesDialog != null){
            countriesDialog.dismiss();
        }
    }

    public void display(Country country) {
        dismissDialog();
        countryEditText.setText(country.getCountryName(getAppCompatActivity()));
    }

    OnFocusChangeListener onFocusChangeListener = new OnFocusChangeListener() {
        @Override
        public void onFocusChange(View view, boolean isFocused) {
            switch (view.getId()){
                case R.id.country_et:
                    if (isFocused){
                        showDialog();
                    }
                    break;
            }

        }
    };
}
