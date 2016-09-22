package com.easy.make.tenantmaker.base.tenants.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.easy.make.tenantmaker.R;
import com.easy.make.tenantmaker.core.tenant.data.model.Tenant;
import com.easy.make.tenantmaker.core.tenant.data.model.Tenants;
import com.easy.make.tenantmaker.core.tenant.displayer.TenantsDisplayer;

/**
 * Created by ravi on 04/09/16.
 */
public class TenantAdapter extends RecyclerView.Adapter<TenantViewHolder>{

    private final LayoutInflater inflater;
    private Tenants tenants;
    private TenantView tenantView;
    private TenantsDisplayer.TenantInteractionListener tenantInteractionListener;

    public TenantAdapter(LayoutInflater inflater) {
        this.inflater = inflater;
        setHasStableIds(true);
    }

    public void setData(Tenants tenants){
        this.tenants = tenants;
        notifyItemRangeInserted(getItemCount(), tenants.size());
    }


    @Override
    public TenantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        tenantView = (TenantView) inflater.inflate(R.layout.tenant_item_layout, parent, false);
        return new TenantViewHolder(tenantView);
    }

    @Override
    public void onBindViewHolder(TenantViewHolder holder, int position) {
        holder.bind(tenants.get(position), selectionListener);
    }

    @Override
    public int getItemCount() {
        if (tenants == null){
            return 0;
        }
        return tenants.size();
    }

    public void attach(TenantsDisplayer.TenantInteractionListener tenantInteractionListener) {
        this.tenantInteractionListener = tenantInteractionListener;
    }

    public void detach(TenantsDisplayer.TenantInteractionListener tenantInteractionListener) {
        this.tenantInteractionListener = null;
    }

    private final TenantViewHolder.TenantSelectionListener selectionListener = new TenantViewHolder.TenantSelectionListener() {
        @Override
        public void onTenantSelected(Tenant tenant) {
            TenantAdapter.this.tenantInteractionListener.onTenantSelected(tenant);
        }
    };
}
