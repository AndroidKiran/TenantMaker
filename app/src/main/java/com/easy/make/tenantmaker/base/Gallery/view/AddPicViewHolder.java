package com.easy.make.tenantmaker.base.Gallery.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.easy.make.tenantmaker.core.gallery.model.GalleryPic;

/**
 * Created by ravi on 14/10/16.
 */

public class AddPicViewHolder extends RecyclerView.ViewHolder {

    private final AddPicItemView addPicItemView;

    public AddPicViewHolder(AddPicItemView itemView) {
        super(itemView);
        this.addPicItemView = itemView;
    }

    public void bind(final GalleryPic galleryPic, final AddListener addListener) {
        addPicItemView.display(galleryPic);
        addPicItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addListener.onPicSelected(galleryPic);
            }
        });
    }

    public interface AddListener {
        void onPicSelected(GalleryPic galleryPic);
    }
}
