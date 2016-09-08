package com.easy.make.tenantmaker.tenants.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.easy.make.core.tenant.data.model.Tenant;

/**
 * Created by ravi on 04/09/16.
 */
public class TenantViewHolder extends RecyclerView.ViewHolder {

    private final TenantView tenantView;

    public TenantViewHolder(TenantView itemView) {
        super(itemView);
        this.tenantView = itemView;
    }

    public void bind(final Tenant tenant, final TenantSelectionListener tenantSelectionListener) {
        tenantView.display(tenant);
        tenantView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tenantSelectionListener.onTenantSelected(tenant);
            }
        });
    }

    public interface TenantSelectionListener {
        void onTenantSelected(Tenant tenant);
    }

}
