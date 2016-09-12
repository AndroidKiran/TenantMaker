package com.easy.make.core.navigation;

import com.easy.make.core.tenant.data.model.Tenant;

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
