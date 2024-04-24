package com.example.myandroidtest.imagePackage;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.myandroidtest.BaseViewModel;
import com.example.myandroidtest.R;
import com.example.myandroidtest.Utils.APIClient;
import com.example.myandroidtest.Utils.UserApi;
import com.example.myandroidtest.model.ImageModel;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImageViewModel extends BaseViewModel {

    private String TAG = getClass().getSimpleName();
    private UserApi userApi;
    private MutableLiveData<ImageResultView> imageResultView = new MutableLiveData<>();

    public LiveData<ImageResultView> getImageResultView() {
        return imageResultView;
    }

    public void getImageList() {
        try {
            userApi = APIClient.getRetrofit().create(UserApi.class);

            Call<List<ImageModel>> call = userApi.getImageList();
            call.enqueue(new Callback<List<ImageModel>>() {
                @Override
                public void onResponse(Call<List<ImageModel>> call, Response<List<ImageModel>> response) {
                    try {

                        if (response.isSuccessful()) {
                            ArrayList<ImageModel> imageModels = (ArrayList<ImageModel>) response.body();

                            if (imageModels != null) {
                                imageResultView.setValue(new ImageResultView(imageModels));
                            } else {
                                imageResultView.setValue(new ImageResultView(R.string.strSomethingWrong));
                            }


                        } else if (response.errorBody() != null) {

                            String string = response.errorBody().string();
                            if (!string.equals("")) {
                                imageResultView.setValue(new ImageResultView(getErrorMessage(string)));

                            } else
                                imageResultView.setValue(new ImageResultView(getErrorMessage(string)));

                        } else
                            imageResultView.setValue(new ImageResultView(R.string.strListFailed));

                    } catch (Exception e) {
                        Log.e(TAG, "imageList Success Response Ex : " + e.getMessage());
                        imageResultView.setValue(new ImageResultView(R.string.strSomethingWrong));
                    }
                }

                @Override
                public void onFailure(Call<List<ImageModel>> call, Throwable t) {
                    imageResultView.setValue(new ImageResultView(R.string.strListFailed));
                }
            });

        } catch (Exception ex) {
            Log.e(TAG, "imageList Ex :" + ex.getMessage());
            imageResultView.setValue(new ImageResultView(R.string.strSomethingWrong));
        }
    }

}
