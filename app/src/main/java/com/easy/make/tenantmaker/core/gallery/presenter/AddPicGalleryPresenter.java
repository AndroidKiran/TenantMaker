package com.easy.make.tenantmaker.core.gallery.presenter;

import com.easy.make.tenantmaker.core.gallery.displayer.AddPicsDisplayer;
import com.easy.make.tenantmaker.core.gallery.model.GalleryPic;
import com.easy.make.tenantmaker.core.gallery.model.GalleryPics;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ravi on 12/11/16.
 */

public class AddPicGalleryPresenter {

    private final AddPicsDisplayer addPicsDisplayer;

    public AddPicGalleryPresenter(AddPicsDisplayer addPicsDisplayer){
        this.addPicsDisplayer = addPicsDisplayer;
    }

    public void startPresenting(){
        addPicsDisplayer.attach(addPicInteractionListener);
        addPicsDisplayer.display(addDefaultPicList());
    }

    public void stopPresenting(){
        addPicsDisplayer.detach(addPicInteractionListener);
    }


    AddPicsDisplayer.AddPicInteractionListener addPicInteractionListener = new AddPicsDisplayer.AddPicInteractionListener() {
        @Override
        public void onPicSelected(GalleryPic galleryPic) {

        }
    };


    private GalleryPics addDefaultPicList(){
        List<GalleryPic> galleryPics = new ArrayList<GalleryPic>();
        for (int i = 0; i < 3; i++){
            galleryPics.add(new GalleryPic());
        }
        return new GalleryPics(galleryPics);
    }

}
