package com.easy.make.core.analytics;

public interface ErrorLogger {

    void reportError(Throwable throwable, Object... args);

}
