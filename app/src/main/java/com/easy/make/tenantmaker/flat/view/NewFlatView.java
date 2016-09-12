package com.easy.make.tenantmaker.flat.view;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
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

import com.easy.make.core.flat.displayer.NewFlatDisplayer;
import com.easy.make.core.flat.model.Flat;
import com.easy.make.tenantmaker.R;
import com.easy.make.tenantmaker.component.materialcomponent.MaterialProgressDialog;
import com.easy.make.tenantmaker.utils.DialogUtils;
import com.novoda.notils.caster.Views;

/**
 * Created by ravi on 05/09/16.
 */
public class NewFlatView extends CoordinatorLayout implements NewFlatDisplayer {

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

    public NewFlatView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        setFitsSystemWindows(true);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        View.inflate(getContext(), R.layout.merge_new_flat_view, this);
        setToolbar();
        initControls();
    }

    void setToolbar() {
        toolbar = Views.findById(this, R.id.toolbar);
        toolbar.setTitle(R.string.str_flat);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
    }

    void initControls() {
        createFlatBtn = Views.findById(this, R.id.save_flat_btn);
        flatNameTextInputLayout = Views.findById(this, R.id.flat_name);
        flatNameEditText = flatNameTextInputLayout.getEditText();
        errFlatNameText = Views.findById(this, R.id.err_flat_name);

        addressTextInputLayout = Views.findById(this, R.id.flat_address);
        addressEditText = addressTextInputLayout.getEditText();
        errAddressText = Views.findById(this, R.id.err_flat_address);

    }

    @Override
    public void attach(FlatCreationListener flatCreationListener) {
        toolbar.setNavigationOnClickListener(onNavigationBackClickListener);
        flatNameEditText.addTextChangedListener(textWatcher);
        addressEditText.addTextChangedListener(textWatcher);
        createFlatBtn.setOnClickListener(onClickListener);
        this.flatCreationListener = flatCreationListener;
    }

    @Override
    public void detach(FlatCreationListener flatCreationListener) {
        toolbar.setNavigationOnClickListener(null);
        createFlatBtn.setOnClickListener(null);
        flatNameEditText.addTextChangedListener(null);
        addressEditText.addTextChangedListener(null);
        this.flatCreationListener = null;
    }

    @Override
    public void showProgress() {
        materialProgressDialog =  new MaterialProgressDialog(getAppCompatActivity());
        DialogUtils.showMaterialProgressDialog(materialProgressDialog, getAppCompatActivity().getString(R.string.str_progress_flat_title), getAppCompatActivity().getString(R.string.str_progress_wait), getAppCompatActivity());
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
        return validate;
    }

    private Flat formToFlat(){
        Flat flat = new Flat();
        flat.setName(flatNameEditText.getText().toString());
        flat.setAddress(addressEditText.getText().toString());
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
           if (flatNameEditText.isFocused()){
                errFlatNameText.setVisibility(s.length() == 0 ? VISIBLE : GONE);
            }  else if (addressEditText.isFocused()){
                errAddressText.setVisibility(s.length() == 0 ? VISIBLE : GONE);
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
                        flatCreationListener.onCreateFlatClicked(formToFlat());
                    }
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

}
