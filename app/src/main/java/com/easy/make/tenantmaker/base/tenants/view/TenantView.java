package com.easy.make.tenantmaker.base.tenants.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.easy.make.tenantmaker.R;
import com.easy.make.tenantmaker.core.tenant.data.model.Tenant;
import com.novoda.notils.caster.Views;

/**
 * Created by ravi on 03/09/16.
 */
public class TenantView extends RelativeLayout {

    private int layoutResId;
    private AppCompatTextView tenantName;
    private AppCompatTextView tenantRoomNo;
    private AppCompatTextView tenantAddress;

    public TenantView(Context context) {
        super(context);
    }

    public TenantView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs != null) {
            int[] attrsArray = {
                    android.R.attr.layout
            };
            TypedArray array = context.obtainStyledAttributes(attrs, attrsArray);
            layoutResId = array.getResourceId(0, R.layout.merge_tenant_item);
            array.recycle();
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        View.inflate(getContext(), layoutResId, this);
        this.tenantName = Views.findById(this, R.id.tenant_name);
        this.tenantAddress = Views.findById(this, R.id.tenant_address);
        this.tenantRoomNo = Views.findById(this, R.id.tenant_room_no);
    }

    public void display(Tenant tenant){
        tenantName.setText(tenant.getBasicInfo().getFirstName()+" "+tenant.getBasicInfo().getLastName());
        tenantAddress.setText(tenant.getBasicInfo().getAddress());
        tenantRoomNo.setText(getContext().getString(R.string.str_room_num)+"-"+tenant.getPgFlatInfo().getPgOrFlatNum());
    }
}
