package com.easy.make.tenantmaker.core.flat.model;

import java.util.List;

/**
 * Created by ravi on 11/09/16.
 */
public class Flats {
    private List<Flat> flats;

    public Flats(List<Flat> flats){
        this.flats = flats;
    }

    public int size(){
        return  flats.size();
    }

    public Flat get(int position){
        return flats.get(position);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Flats flats1 = (Flats) o;

        return flats != null ? flats.equals(flats1.flats) : flats1.flats == null;

    }

    @Override
    public int hashCode() {
        return flats != null ? flats.hashCode() : 0;
    }
}
