package com.easy.make.tenantmaker.core.navigation;


import com.easy.make.tenantmaker.core.tenant.data.model.Tenant;

public interface Navigator {

    void toHome();

    void toTenants();

    void toTenants(String query);

    void toChatWithTenant(Tenant tenant);

    void toCreateTenant();

    void toLogin();

    void toParent();

    void toNewFlat();

    void toMain();
}
