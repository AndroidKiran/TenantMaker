package com.easy.make.tenantmaker.base.Gallery.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.easy.make.tenantmaker.R;
import com.easy.make.tenantmaker.core.gallery.displayer.AddPicsDisplayer;
import com.easy.make.tenantmaker.core.gallery.model.GalleryPic;
import com.easy.make.tenantmaker.core.gallery.model.GalleryPics;

import java.util.ArrayList;

/**
 * Created by ravi on 14/10/16.
 */

public class AddPicsAdapter extends RecyclerView.Adapter<AddPicViewHolder> {

    private final LayoutInflater inflater;
    private GalleryPics galleryPics;
    private AddPicItemView addPicItemView;
    private AddPicsDisplayer.AddPicInteractionListener addPicInteractionListener;

    public AddPicsAdapter(LayoutInflater inflater) {
        this.inflater = inflater;
        galleryPics = new GalleryPics(new ArrayList<GalleryPic>());
        setHasStableIds(true);
    }

    public void setData(GalleryPics galleryPics){
        this.galleryPics = galleryPics;
        notifyItemRangeInserted(getItemCount(), galleryPics.size());
    }

    @Override
    public AddPicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        addPicItemView = (AddPicItemView) inflater.inflate(R.layout.add_pic_item_layout, parent, false);
        return new AddPicViewHolder(addPicItemView);
    }

    @Override
    public void onBindViewHolder(AddPicViewHolder holder, int position) {
        holder.bind(galleryPics.get(position), addListener);
    }

    @Override
    public int getItemCount() {
        return galleryPics.size();
    }

    public void attach(AddPicsDisplayer.AddPicInteractionListener addPicInteractionListener) {
        this.addPicInteractionListener = addPicInteractionListener;
    }

    public void detach(AddPicsDisplayer.AddPicInteractionListener addPicInteractionListener) {
        this.addPicInteractionListener = null;
    }

   AddPicViewHolder.AddListener addListener = new AddPicViewHolder.AddListener() {
       @Override
       public void onPicSelected(GalleryPic galleryPic) {
           addPicInteractionListener.onPicSelected(galleryPic);
       }
   };

}
