package com.easy.make.tenantmaker.listeners;

/**
 * Created by ravi on 10/08/16.
 */
public interface FileUploadListeners {
    void onProgressListener();
    void onPauseListener();
    void onFailureListener();
    void onSuccessListener();
}
