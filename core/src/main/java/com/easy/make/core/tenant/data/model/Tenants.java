package com.easy.make.core.tenant.data.model;

import java.util.List;

/**
 * Created by ravi on 23/08/16.
 */
public class Tenants {
    private List<Tenant> tenants;

    public Tenants(List<Tenant> tenants){
        this.tenants = tenants;
    }

    public int size() {
        return tenants.size();
    }

    public Tenant get(int position) {
        return tenants.get(position);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tenants tenants1 = (Tenants) o;

        return tenants != null ? tenants.equals(tenants1.tenants) : tenants1.tenants == null;

    }

    @Override
    public int hashCode() {
        return tenants != null ? tenants.hashCode() : 0;
    }
}
