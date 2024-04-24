package com.example.myandroidtest.imagePackage;

import androidx.annotation.Nullable;

import com.example.myandroidtest.BaseResultView;
import com.example.myandroidtest.model.ImageModel;

import java.util.ArrayList;


public class ImageResultView extends BaseResultView {

    @Nullable
    private Object error;
    @Nullable
    private ArrayList<ImageModel> imageModelList;

    ImageResultView(@Nullable Object error) {
        this.error = error;
    }

    ImageResultView(@Nullable ArrayList<ImageModel> imageModelList) {
        this.imageModelList = imageModelList;
    }

    @Nullable
    Object getError() {
        return error;
    }

    @Nullable
    ArrayList<ImageModel> getSuccessImageList(){
        return imageModelList;
    }

}
