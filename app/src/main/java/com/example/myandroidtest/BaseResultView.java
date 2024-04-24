package com.example.myandroidtest;

import androidx.annotation.Nullable;

public class BaseResultView {

    @Nullable
    String resMsg;

    public BaseResultView() {
    }

    public BaseResultView(@Nullable String resMsg) {
        this.resMsg = resMsg;
    }

    public String getResMsg() {
        return resMsg;
    }

    public void setResMsg(String resMsg) {
        this.resMsg = resMsg;
    }
}
