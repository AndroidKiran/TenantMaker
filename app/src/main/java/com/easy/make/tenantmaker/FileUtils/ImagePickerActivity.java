package com.easy.make.tenantmaker.FileUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.easy.make.tenantmaker.TenantApplication;
import com.easy.make.tenantmaker.utils.AppPreferences;
import com.easy.make.tenantmaker.utils.UtilBundles;
import com.easy.make.tenantmaker.utils.Utils;
import com.kbeanie.multipicker.api.CameraImagePicker;
import com.kbeanie.multipicker.api.ImagePicker;
import com.kbeanie.multipicker.api.Picker;
import com.kbeanie.multipicker.api.callbacks.ImagePickerCallback;
import com.kbeanie.multipicker.api.entity.ChosenImage;

import java.util.List;

/**
 * Created by ravi on 12/08/16.
 */
public class ImagePickerActivity extends AppCompatActivity implements ImagePickerCallback {
    private String pickerPath;
    private ImagePicker imagePicker;
    protected AppPreferences appPreferences;
    private CameraImagePicker cameraPicker;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appPreferences = TenantApplication.getInstance().getAppPreferences();
    }

    protected void pickImageSingle() {
        imagePicker = new ImagePicker(this);
        imagePicker.setRequestId(1234);
        imagePicker.ensureMaxSize(5090, 5090);
        imagePicker.shouldGenerateMetadata(true);
        imagePicker.shouldGenerateThumbnails(true);
        imagePicker.setImagePickerCallback(this);
//        Bundle bundle = new Bundle();
//        bundle.putInt("android.intent.extras.CAMERA_FACING", 1);
        imagePicker.setCacheLocation(Utils.getSavedCacheLocation(this));
        imagePicker.pickImage();
    }

    protected void pickImageMultiple() {
        imagePicker = new ImagePicker(this);
        imagePicker.setImagePickerCallback(this);
        imagePicker.setCacheLocation(Utils.getSavedCacheLocation(this));
        imagePicker.allowMultiple();
        imagePicker.pickImage();
    }


    protected void takePicture() {
        cameraPicker = new CameraImagePicker(this);
        cameraPicker.setImagePickerCallback(this);
        cameraPicker.shouldGenerateMetadata(true);
        cameraPicker.shouldGenerateThumbnails(true);
        pickerPath = cameraPicker.pickImage();
        cameraPicker.setCacheLocation(Utils.getSavedCacheLocation(this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == Picker.PICK_IMAGE_DEVICE) {
                if (imagePicker == null) {
                    imagePicker = new ImagePicker(this);
                    imagePicker.setImagePickerCallback(this);
                }
                imagePicker.submit(data);
            } else if (requestCode == Picker.PICK_IMAGE_CAMERA) {
                if (cameraPicker == null) {
                    cameraPicker = new CameraImagePicker(this);
                    cameraPicker.setImagePickerCallback(this);
                    cameraPicker.reinitialize(pickerPath);
                }
                cameraPicker.submit(data);
            }
        }
    }

    @Override
    public void onImagesChosen(List<ChosenImage> images) {

    }

    @Override
    public void onError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // You have to save path in case your activity is killed.
        // In such a scenario, you will need to re-initialize the CameraImagePicker
        outState.putString(UtilBundles.PICKER_PATH, pickerPath);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        // After Activity recreate, you need to re-intialize these
        // two values to be able to re-intialize CameraImagePicker
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(UtilBundles.PICKER_PATH)) {
                pickerPath = savedInstanceState.getString(UtilBundles.PICKER_PATH);
            }
        }
        super.onRestoreInstanceState(savedInstanceState);
    }
}
