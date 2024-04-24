package com.example.myandroidtest.imagePackage;

import static android.os.Build.VERSION.SDK_INT;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.myandroidtest.R;
import com.example.myandroidtest.Utils.ConnectionDetector;
import com.example.myandroidtest.Utils.Content;
import com.example.myandroidtest.adapters.ImageListAdapter;
import com.example.myandroidtest.databinding.ActivityImageListBinding;
import com.example.myandroidtest.model.ImageModel;

import java.util.ArrayList;

public class ImageListActivity extends AppCompatActivity {

    private Activity context = ImageListActivity.this;
    ImageListAdapter imageListAdapter;
    ArrayList<ImageModel> imageModelArrayList = new ArrayList<>();
    ActivityImageListBinding binding;
    private ImageViewModel imageViewModel;
    boolean isListApiCalled = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_image_list);

        imageViewModel = new ViewModelProvider(this, new ImageViewModelFactory()).get(ImageViewModel.class);

        init();
        setObservers();

        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
    }

    private void init() {
        binding.imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        imageListAdapter = new ImageListAdapter(context, imageModelArrayList);
        binding.grImageList.setAdapter(imageListAdapter);
    }

    private void setObservers() {
        imageViewModel.getImageResultView().observe(this, new Observer<ImageResultView>() {
            @Override
            public void onChanged(ImageResultView resultView) {
                if (resultView == null) {
                    return;
                }
                if (resultView.getSuccessImageList() != null) {
                    imageModelArrayList.addAll(resultView.getSuccessImageList());
                    imageListAdapter.notifyDataSetChanged();
                }

                binding.progress.setVisibility(View.GONE);

                if (resultView.getError() != null) {
                    Content.showFailed(ImageListActivity.this,resultView.getError());
                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        if (!isListApiCalled) {
            callImageListApi(true);
        }
    }

    public void callImageListApi(boolean isShowProgress) {
        if (ConnectionDetector.isConnectedToInternet(context)) {
            if (isShowProgress) {
                binding.progress.setVisibility(View.VISIBLE);
            }
            imageViewModel.getImageList();
            isListApiCalled = true;
        }
    }

}