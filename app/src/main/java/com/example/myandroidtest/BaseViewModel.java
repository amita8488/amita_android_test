package com.example.myandroidtest;


import android.util.Log;

import androidx.lifecycle.ViewModel;

import org.json.JSONException;
import org.json.JSONObject;


public class BaseViewModel extends ViewModel {

    public String tag = getClass().getSimpleName();

    public Object getErrorMessage(String string) {
        try {
            String str = "";
            if (!string.equals("")) {
                JSONObject jsonObject1 = null;

                jsonObject1 = new JSONObject(string);

                str = jsonObject1.optString("message");
            } else
                str = "Data get successfully";

            if (str.equals("")) {
                return R.string.strSomethingWrong;
            } else {
                return str;
            }
        } catch (JSONException e) {
            Log.e(tag, "Error Message : " + e.getMessage());
            return R.string.strSomethingWrong;
        }
    }
}