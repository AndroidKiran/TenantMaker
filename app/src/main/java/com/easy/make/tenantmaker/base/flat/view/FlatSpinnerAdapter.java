package com.easy.make.tenantmaker.base.flat.view;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.easy.make.tenantmaker.core.flat.model.Flat;
import com.easy.make.tenantmaker.core.flat.model.Flats;

import java.util.ArrayList;

/**
 * Created by ravi on 11/10/16.
 */

public class FlatSpinnerAdapter extends ArrayAdapter<Flat> {

    private final Context context;
    private final int layoutId;
    private Flats flats;

    public FlatSpinnerAdapter(Context context, int resource) {
        super(context, resource);
        this.context = context;
        this.layoutId = resource;
        this.flats = new Flats(new ArrayList<Flat>());
    }

    public void setData(Flats flats){
        this.flats = flats;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return flats.size();
    }

    @Nullable
    @Override
    public Flat getItem(int position) {
        return flats.get(position);
    }

//    @Override
//    public long getItemId(int i) {
//        return 0;
//    }

    public View getCustomView(int position, View convertView, ViewGroup parent) {
        FlatViewHolder flatViewHolder = null;
        FlatSpinnerItemView flatItemView = null;
        if (flatItemView == null){
            LayoutInflater layoutInflater = ((Activity) context).getLayoutInflater();
            flatItemView = (FlatSpinnerItemView) layoutInflater.inflate(layoutId, parent, false);
            flatViewHolder = new FlatViewHolder(flatItemView);
            flatItemView.setTag(flatViewHolder);
        } else {
            flatViewHolder = (FlatViewHolder) flatItemView.getTag();
        }

        Flat flat = getItem(position);
        flatViewHolder.bind(flat);
        return flatItemView;
    }

    @Override
    public View getDropDownView(int position, View convertView,ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public static class FlatViewHolder {

        private final FlatSpinnerItemView flatItemView;

        public FlatViewHolder(FlatSpinnerItemView flatItemView){
            this.flatItemView = flatItemView;
        }

        public void bind(Flat flat) {
            flatItemView.display(flat);
        }
    }
}
