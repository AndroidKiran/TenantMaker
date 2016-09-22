package com.easy.make.tenantmaker.core.analytics;

public interface ErrorLogger {

    void reportError(Throwable throwable, Object... args);

}
