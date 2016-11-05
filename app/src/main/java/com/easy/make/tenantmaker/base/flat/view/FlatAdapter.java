package com.easy.make.tenantmaker.base.flat.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.easy.make.tenantmaker.R;
import com.easy.make.tenantmaker.core.flat.displayer.FlatsDisplayer;
import com.easy.make.tenantmaker.core.flat.model.Flat;
import com.easy.make.tenantmaker.core.flat.model.Flats;

import java.util.ArrayList;

/**
 * Created by ravi on 14/10/16.
 */

public class FlatAdapter extends RecyclerView.Adapter<FlatViewHolder> {

    private final LayoutInflater inflater;
    private Flats flats;
    private FlatItemView flatItemView;
    private FlatsDisplayer.FlatInteractionListener flatInteractionListener;

    public FlatAdapter(LayoutInflater inflater) {
        this.inflater = inflater;
        flats = new Flats(new ArrayList<Flat>());
        setHasStableIds(true);
    }

    public void setData(Flats flats){
        this.flats = flats;
        notifyItemRangeInserted(getItemCount(), flats.size());
    }

    @Override
    public FlatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        flatItemView = (FlatItemView) inflater.inflate(R.layout.flat_item_layout, parent, false);
        return new FlatViewHolder(flatItemView);
    }

    @Override
    public void onBindViewHolder(FlatViewHolder holder, int position) {
        holder.bind(flats.get(position), selectionListener);
    }

    @Override
    public int getItemCount() {
        return flats.size();
    }

    public void attach(FlatsDisplayer.FlatInteractionListener flatInteractionListener) {
        this.flatInteractionListener = flatInteractionListener;
    }

    public void detach(FlatsDisplayer.FlatInteractionListener flatInteractionListener) {
        this.flatInteractionListener = null;
    }

    FlatViewHolder.FlatSelectionListener selectionListener = new FlatViewHolder.FlatSelectionListener() {
        @Override
        public void onFlatSelected(Flat flat) {
            flatInteractionListener.onFlatSelected(flat);
        }
    };
}
