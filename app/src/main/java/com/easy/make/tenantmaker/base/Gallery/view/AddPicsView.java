package com.easy.make.tenantmaker.base.Gallery.view;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.easy.make.tenantmaker.R;
import com.easy.make.tenantmaker.core.gallery.displayer.AddPicsDisplayer;
import com.easy.make.tenantmaker.core.gallery.model.GalleryPics;
import com.novoda.notils.caster.Views;

/**
 * Created by ravi on 12/11/16.
 */

public class AddPicsView extends LinearLayout implements AddPicsDisplayer{


    private final AddPicsAdapter addPicsAdapter;
    private RecyclerView recyclerView;
    private AddPicInteractionListener addPicInteractionListener;

    public AddPicsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);
        setFitsSystemWindows(true);
        addPicsAdapter = new AddPicsAdapter(LayoutInflater.from(context));
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        View.inflate(getContext(), R.layout.merge_add_pics, this);
        setRecyclerView();
    }

    private void setRecyclerView() {
        recyclerView = Views.findById(this, R.id.recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(addPicsAdapter);
    }

    @Override
    public void attach(AddPicInteractionListener addPicInteractionListener) {
        this.addPicInteractionListener =  addPicInteractionListener;
        addPicsAdapter.attach(addPicInteractionListener);
    }

    @Override
    public void detach(AddPicInteractionListener addPicInteractionListener) {
        addPicInteractionListener = null;
        addPicsAdapter.detach(addPicInteractionListener);
    }

    @Override
    public void display(GalleryPics galleryPics) {
        addPicsAdapter.setData(galleryPics);
    }
}
