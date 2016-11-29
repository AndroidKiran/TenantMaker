package com.easy.make.tenantmaker.core.gallery.model;

import java.util.List;

/**
 * Created by ravi on 12/11/16.
 */

public class GalleryPics {

    private List<GalleryPic> galleryPicList;

    public GalleryPics(List<GalleryPic> galleryPics){
        this.galleryPicList = galleryPics;
    }

    public int size(){
        return this.galleryPicList.size();
    }

    public GalleryPic get(int position){
        return galleryPicList.get(position);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GalleryPics)) return false;

        GalleryPics that = (GalleryPics) o;

        return galleryPicList != null ? galleryPicList.equals(that.galleryPicList) : that.galleryPicList == null;

    }

    @Override
    public int hashCode() {
        return galleryPicList != null ? galleryPicList.hashCode() : 0;
    }
}
