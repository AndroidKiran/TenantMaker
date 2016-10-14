package com.easy.make.tenantmaker.base.flat.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.easy.make.tenantmaker.core.flat.model.Flat;

/**
 * Created by ravi on 14/10/16.
 */

public class FlatViewHolder extends RecyclerView.ViewHolder {

    private final FlatItemView flatItemView;

    public FlatViewHolder(FlatItemView itemView) {
        super(itemView);
        this.flatItemView = itemView;
    }

    public void bind(final Flat flat, final FlatSelectionListener flatSelectionListener) {
        flatItemView.display(flat);
        flatItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flatSelectionListener.onFlatSelected(flat);
            }
        });
    }

    public interface FlatSelectionListener {
        void onFlatSelected(Flat flat);
    }
}
