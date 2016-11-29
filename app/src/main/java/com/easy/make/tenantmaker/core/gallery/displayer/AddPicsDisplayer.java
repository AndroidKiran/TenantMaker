package com.easy.make.tenantmaker.core.gallery.displayer;

import com.easy.make.tenantmaker.core.gallery.model.GalleryPic;
import com.easy.make.tenantmaker.core.gallery.model.GalleryPics;

/**
 * Created by ravi on 12/11/16.
 */

public interface AddPicsDisplayer {

    void attach(AddPicInteractionListener addPicInteractionListener);

    void detach(AddPicInteractionListener addPicInteractionListener);

    void display(GalleryPics galleryPics);

    interface AddPicInteractionListener {
        void onPicSelected(GalleryPic galleryPic);
    }
}
