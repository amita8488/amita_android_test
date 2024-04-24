package com.example.myandroidtest.imagePackage;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;


public class ImageViewModelFactory implements ViewModelProvider.Factory {

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ImageViewModel.class)) {
            return (T) new ImageViewModel();
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}