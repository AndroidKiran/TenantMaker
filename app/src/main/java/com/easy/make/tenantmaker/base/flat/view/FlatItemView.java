package com.easy.make.tenantmaker.base.flat.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.easy.make.tenantmaker.R;
import com.easy.make.tenantmaker.core.flat.model.Flat;
import com.novoda.notils.caster.Views;

/**
 * Created by ravi on 03/09/16.
 */
public class FlatItemView extends RelativeLayout {

    private int layoutResId;
    private AppCompatTextView flatName;
    private AppCompatTextView flatAddress;

    public FlatItemView(Context context) {
        super(context);
    }

    public FlatItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs != null) {
            int[] attrsArray = {
                    android.R.attr.layout
            };
            TypedArray array = context.obtainStyledAttributes(attrs, attrsArray);
            layoutResId = array.getResourceId(0, R.layout.merge_flat_item);
            array.recycle();
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        View.inflate(getContext(), layoutResId, this);
        this.flatName = Views.findById(this, R.id.flat_name);
        this.flatAddress = Views.findById(this, R.id.flat_address);
    }

    public void display(Flat flat){
        flatName.setText(flat.getName());
        flatAddress.setText(flat.getAddress());
    }
}
