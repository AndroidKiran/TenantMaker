package com.easy.make.tenantmaker.base.Gallery.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.easy.make.tenantmaker.R;
import com.easy.make.tenantmaker.core.gallery.model.GalleryPic;
import com.novoda.notils.caster.Views;

/**
 * Created by ravi on 03/09/16.
 */
public class AddPicItemView extends FrameLayout {


    private int layoutResId;
    private AppCompatImageView flatPic;
    private View flatPicOverlay;

    public AddPicItemView(Context context) {
        super(context);
    }

    public AddPicItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs != null) {
            int[] attrsArray = {
                    android.R.attr.layout
            };
            TypedArray array = context.obtainStyledAttributes(attrs, attrsArray);
            layoutResId = array.getResourceId(0, R.layout.merge_add_pic_item);
            array.recycle();
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        View.inflate(getContext(), layoutResId, this);
        this.flatPic = Views.findById(this, R.id.flat_pic);
        this.flatPicOverlay = Views.findById(this, R.id.add_pic_overlay);
    }

    public void display(GalleryPic galleryPic){
//        flatPic.setImageResource();
    }
}
