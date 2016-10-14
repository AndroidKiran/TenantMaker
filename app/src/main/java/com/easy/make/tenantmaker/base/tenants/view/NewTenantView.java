package com.easy.make.tenantmaker.base.tenants.view;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;

import com.easy.make.tenantmaker.R;
import com.easy.make.tenantmaker.base.component.materialcomponent.MaterialProgressDialog;
import com.easy.make.tenantmaker.base.flat.view.FlatSpinnerAdapter;
import com.easy.make.tenantmaker.base.utils.DateTimeUtils;
import com.easy.make.tenantmaker.base.utils.DialogUtils;
import com.easy.make.tenantmaker.core.flat.model.Flat;
import com.easy.make.tenantmaker.core.flat.model.Flats;
import com.easy.make.tenantmaker.core.tenant.data.model.Tenant;
import com.easy.make.tenantmaker.core.tenant.displayer.NewTenantDisplayer;
import com.novoda.notils.caster.Views;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import fr.ganfra.materialspinner.MaterialSpinner;

/**
 * Created by ravi on 05/09/16.
 */
public class NewTenantView extends CoordinatorLayout implements NewTenantDisplayer, DatePickerDialog.OnDateSetListener {


    private final Context context;
    private FlatSpinnerAdapter flatSpinnerAdapter;
    private Toolbar toolbar;
    private TenantCreationListener tenantCreationListener;
    private AppCompatButton createTenantBtn;
    private TextInputLayout emailTextInputLayout;
    private EditText emailEditText;
    private TextInputLayout firstNameTextInputLayout;
    private EditText firtNameEditText;
    private TextInputLayout lastNameTextInputLayout;
    private EditText lastNameEditText;
    private TextInputLayout mobileTextInputLayout;
    private EditText mobileEditText;
    private TextInputLayout addressTextInputLayout;
    private EditText addressEditText;
    private TextInputLayout roomNumTextInputLayout;
    private EditText roomNumEditText;
    private MaterialSpinner pgSpinner;
    public AppCompatTextView rentDateTextView;
    private AppCompatTextView errEmailText;
    private AppCompatTextView errFirstNameText;
    private AppCompatTextView errLastNameText;
    private AppCompatTextView errMobileText;
    private AppCompatTextView errAddressText;
    private AppCompatTextView errRoomText;
    private AppCompatTextView errPgText;
    public AppCompatTextView errRentDateText;
    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private AppCompatActivity appCompatActivity;
    private MaterialProgressDialog materialProgressDialog;

    public NewTenantView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        flatSpinnerAdapter = new FlatSpinnerAdapter(getContext(), R.layout.spinner_flat_item_layout);
        setFitsSystemWindows(true);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        View.inflate(getContext(), R.layout.merge_new_tenant_view, this);
        setToolbar();
        initControls();
        setAdapter();
    }

    void setToolbar() {
        toolbar = Views.findById(this, R.id.toolbar);
        toolbar.setTitle(R.string.str_add_tenant);
//        toolbar.inflateMenu(R.menu.new_channel_menu);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
    }

    void initControls() {
        createTenantBtn = Views.findById(this, R.id.save_tenant_btn);

        emailTextInputLayout = Views.findById(this, R.id.tenant_email);
        emailEditText = emailTextInputLayout.getEditText();
        errEmailText = Views.findById(this, R.id.err_email);

        firstNameTextInputLayout = Views.findById(this, R.id.tenant_first_name);
        firtNameEditText = firstNameTextInputLayout.getEditText();
        errFirstNameText = Views.findById(this, R.id.err_first_name);

        lastNameTextInputLayout = Views.findById(this, R.id.tenant_last_name);
        lastNameEditText = lastNameTextInputLayout.getEditText();
        errLastNameText = Views.findById(this, R.id.err_last_name);

        mobileTextInputLayout = Views.findById(this, R.id.tenant_mob_number);
        mobileEditText = mobileTextInputLayout.getEditText();
        errMobileText = Views.findById(this, R.id.err_mobile);

        addressTextInputLayout = Views.findById(this, R.id.tenant_address);
        addressEditText = addressTextInputLayout.getEditText();
        errAddressText = Views.findById(this, R.id.err_address);

        roomNumTextInputLayout = Views.findById(this, R.id.tenant_room_num);
        roomNumEditText = roomNumTextInputLayout.getEditText();
        errRoomText = Views.findById(this, R.id.err_room_num);

        pgSpinner = Views.findById(this, R.id.pg_spinner);
        errPgText = Views.findById(this, R.id.err_pg);

        rentDateTextView = Views.findById(this, R.id.tenant_rent_date);
        errRentDateText = Views.findById(this, R.id.err_rent_date);

    }

    public void setAdapter(){
        pgSpinner.setAdapter(flatSpinnerAdapter);
    }

    @Override
    public void attach(TenantCreationListener tenantCreationListener) {
        toolbar.setNavigationOnClickListener(onNavigationBackClickListener);
        emailEditText.addTextChangedListener(textWatcher);
        firtNameEditText.addTextChangedListener(textWatcher);
        lastNameEditText.addTextChangedListener(textWatcher);
        mobileEditText.addTextChangedListener(textWatcher);
        addressEditText.addTextChangedListener(textWatcher);
        roomNumEditText.addTextChangedListener(textWatcher);
        rentDateTextView.addTextChangedListener(textWatcher);
        createTenantBtn.setOnClickListener(onClickListener);
        rentDateTextView.setOnClickListener(onClickListener);
        pgSpinner.setOnItemSelectedListener(onItemSelectedListener);
        this.tenantCreationListener = tenantCreationListener;
    }

    @Override
    public void detach(TenantCreationListener tenantCreationListener) {
        toolbar.setNavigationOnClickListener(null);
        createTenantBtn.setOnClickListener(null);
        emailEditText.addTextChangedListener(null);
        firtNameEditText.addTextChangedListener(null);
        lastNameEditText.addTextChangedListener(null);
        mobileEditText.addTextChangedListener(null);
        addressEditText.addTextChangedListener(null);
        roomNumEditText.addTextChangedListener(null);
        rentDateTextView.addTextChangedListener(null);
        rentDateTextView.setOnClickListener(null);
        pgSpinner.setOnItemSelectedListener(null);
        rentDateTextView.setOnClickListener(null);
        this.tenantCreationListener = null;
    }

    @Override
    public void displayFlats(Flats flats) {
        flatSpinnerAdapter.setData(flats);
    }

    @Override
    public void showProgress() {
        materialProgressDialog =  new MaterialProgressDialog(getAppCompatActivity());
        DialogUtils.showMaterialProgressDialog(materialProgressDialog, getAppCompatActivity().getString(R.string.str_progress_tenant_title), getAppCompatActivity().getString(R.string.str_progress_wait), getAppCompatActivity());
    }

    @Override
    public void dismissProgress() {
        if (materialProgressDialog != null){
            materialProgressDialog.dismiss();
        }
    }

    @Override
    public void clear() {

    }

    private boolean validateForm() {

        boolean validate = true;
        String email = emailEditText.getText().toString();
        if (TextUtils.isEmpty(email)) {
            errEmailText.setVisibility(VISIBLE);
            validate = false;
        } else {
            errEmailText.setVisibility(GONE);
        }

        String firstName = firtNameEditText.getText().toString();
        if (TextUtils.isEmpty(firstName)) {
            errFirstNameText.setVisibility(VISIBLE);
            validate = false;
        } else {
            errFirstNameText.setVisibility(GONE);
        }

        String lastName = lastNameEditText.getText().toString();
        if (TextUtils.isEmpty(lastName)) {
            errLastNameText.setVisibility(VISIBLE);
            validate = false;
        } else {
            errLastNameText.setVisibility(GONE);
        }

        String mobile = mobileEditText.getText().toString();
        if (TextUtils.isEmpty(mobile)) {
            errMobileText.setVisibility(VISIBLE);
            validate = false;
        } else {
            errMobileText.setVisibility(GONE);
        }

        String address = addressEditText.getText().toString();
        if (TextUtils.isEmpty(address)) {
            errAddressText.setVisibility(VISIBLE);
            validate = false;
        } else {
            errAddressText.setVisibility(GONE);
        }

        String rendDate = rentDateTextView.getText().toString();
        if (TextUtils.isEmpty(rendDate)) {
            errRentDateText.setVisibility(VISIBLE);
            validate = false;
        } else {
            errRentDateText.setVisibility(GONE);
        }

        String roomNum = roomNumEditText.getText().toString();
        if (TextUtils.isEmpty(roomNum)) {
            errRoomText.setVisibility(VISIBLE);
            validate = false;
        } else {
            errRoomText.setVisibility(GONE);
        }

        int selectedPosition =  pgSpinner.getSelectedItemPosition();
        if (selectedPosition == 0) {
            errPgText.setVisibility(VISIBLE);
            validate = false;
        } else {
            errPgText.setVisibility(GONE);
        }

        return validate;
    }

    private Tenant formToTenant(){

        Tenant.BasicInfo basicInfo = new Tenant.BasicInfo();
        basicInfo.setFirstName(firtNameEditText.getText().toString());
        basicInfo.setLastName(lastNameEditText.getText().toString());
        basicInfo.setEmail(emailEditText.getText().toString());
        basicInfo.setMobile(mobileEditText.getText().toString());
        basicInfo.setAddress(addressEditText.getText().toString());

        Tenant.PaymentInfo paymentInfo = new Tenant.PaymentInfo();
        paymentInfo.setRentDate(rentDateTextView.getText().toString());

        Tenant.PgFlatInfo pgFlatInfo = new Tenant.PgFlatInfo();
        pgFlatInfo.setPgOrFlatId(((Flat) pgSpinner.getSelectedItem()).getId());
        pgFlatInfo.setPgOrFlatNum(roomNumEditText.getText().toString());

        Tenant tenant = new Tenant();
        tenant.setId(mobileEditText.getText().toString());
        tenant.setBasicInfo(basicInfo);
        tenant.setPaymentInfo(paymentInfo);
        tenant.setPgFlatInfo(pgFlatInfo);

        return tenant;
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
            if (emailEditText.isFocused()){
                errEmailText.setVisibility(s.length() > 0 && !s.toString().matches(emailPattern) ? VISIBLE : GONE);
            } else if (firtNameEditText.isFocused()){
                errFirstNameText.setVisibility(s.length() == 0  ? VISIBLE : GONE);
            } else if (lastNameEditText.isFocused()){
                errLastNameText.setVisibility(s.length() == 0 ? VISIBLE : GONE);
            } else if (mobileEditText.isFocused()){
                errMobileText.setVisibility(s.length() == 0 ? VISIBLE : GONE);
            } else if (addressEditText.isFocused()){
                errAddressText.setVisibility(s.length() == 0 ? VISIBLE : GONE);
            } else if (roomNumEditText.isFocused()){
                errRoomText.setVisibility(s.length() == 0 ? VISIBLE : GONE);
            } else if (rentDateTextView.isFocused()){
                errRentDateText.setVisibility(s.length() == 0 ? VISIBLE : GONE);
            } else if (pgSpinner.isFocused()){
                errPgText.setVisibility(s.length() == 0 ? VISIBLE : GONE);
            }
        }
    };


    final OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.save_tenant_btn:
                    System.out.println("saved click");
                    if (validateForm()) {
                        tenantCreationListener.onCreateTenantClicked(formToTenant());
                    }
                    break;

                case R.id.tenant_rent_date:
                    showDatePicker();
                    break;
            }
        }
    };

    final OnClickListener onNavigationBackClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            tenantCreationListener.onCancel();
        }
    };

    final AdapterView.OnItemSelectedListener onItemSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
    };


    public AppCompatActivity getAppCompatActivity() {
        return appCompatActivity;
    }

    public void setAppCompatActivity(AppCompatActivity appCompatActivity) {
        this.appCompatActivity = appCompatActivity;
    }

    private void showDatePicker() {
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        dpd.setThemeDark(true);
        dpd.dismissOnPause(true);
        dpd.showYearPickerFirst(true);
        dpd.setAccentColor(ActivityCompat.getColor(getAppCompatActivity(), R.color.material_login_background));
        dpd.show(getAppCompatActivity().getFragmentManager(), "Datepickerdialog");

    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = year+"-"+monthOfYear+"-"+dayOfMonth;
        try {
            Date formatedDate = DateTimeUtils.simpleDateFormat.parse(date);
            String formatedDateStr = DateTimeUtils.dateFormatddMMMMyyyy.format(formatedDate);
            rentDateTextView.setText(formatedDateStr);
            errRentDateText.setVisibility(View.GONE);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
