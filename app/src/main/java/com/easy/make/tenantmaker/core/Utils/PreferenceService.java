package com.easy.make.tenantmaker.core.Utils;

/**
 * Created by ravi on 12/09/16.
 */
public interface PreferenceService {

    void setLoginUserPreference(String loginUser);

    String getLoginUserPreference();

    void setFirstFlowPreference(boolean firstFlowCompleted);

    boolean getFirstFlowValue();

}
