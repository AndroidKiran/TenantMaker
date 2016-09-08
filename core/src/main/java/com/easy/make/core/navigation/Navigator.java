package com.easy.make.core.navigation;

import com.easy.make.core.tenant.data.model.Tenant;

public interface Navigator {


    void toTenants();

    void toChatWithTenant(Tenant tenant);

    void toCreateTenant();

    void toLogin();

    void toParent();
}
