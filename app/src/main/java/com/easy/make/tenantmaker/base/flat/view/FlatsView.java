package com.easy.make.tenantmaker.base.flat.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import com.easy.make.tenantmaker.R;
import com.easy.make.tenantmaker.base.component.DividerItemDecoration;
import com.easy.make.tenantmaker.core.flat.displayer.FlatsDisplayer;
import com.easy.make.tenantmaker.core.flat.model.Flats;
import com.novoda.notils.caster.Views;

/**
 * Created by ravi on 22/10/16.
 */

public class FlatsView extends CoordinatorLayout implements FlatsDisplayer {


    private final Context context;
    private final FlatAdapter flatsAdapter;
    private RecyclerView recyclerView;
    private FlatInteractionListener flatInteractionListener;

    public FlatsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        setFitsSystemWindows(true);
        flatsAdapter = new FlatAdapter(LayoutInflater.from(context));
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        View.inflate(getContext(), R.layout.merge_flats_view, this);
        setRecyclerView();
    }

    private void setRecyclerView() {
        recyclerView = Views.findById(this, R.id.recycler_view);
        Drawable dividerDrawable = ContextCompat.getDrawable(getContext(), R.drawable.seperator_72);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(dividerDrawable);
        recyclerView.addItemDecoration(dividerItemDecoration);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(flatsAdapter);
    }

    @Override
    public void display(Flats flats) {
        flatsAdapter.setData(flats);
    }

    @Override
    public void attach(FlatInteractionListener flatInteractionListener) {
        this.flatInteractionListener = flatInteractionListener;
        flatsAdapter.attach(flatInteractionListener);
    }

    @Override
    public void detach(FlatInteractionListener flatInteractionListener) {
        this.flatInteractionListener = flatInteractionListener;
        flatsAdapter.detach(flatInteractionListener);
    }
}
