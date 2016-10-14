package com.easy.make.tenantmaker.core.flat.displayer;

import com.easy.make.tenantmaker.core.flat.model.Flats;
import com.easy.make.tenantmaker.core.tenant.data.model.Tenant;

/**
 * Created by ravi on 14/10/16.
 */

public interface FlatsDisplayer {

    void display(Flats flats);

    void attach(FlatInteractionListener flatInteractionListener);

    void detach(FlatInteractionListener flatInteractionListener);

    interface FlatInteractionListener {
        void onFlatSelected(Tenant tenant);

        void onCreateFlat();
    }
}
