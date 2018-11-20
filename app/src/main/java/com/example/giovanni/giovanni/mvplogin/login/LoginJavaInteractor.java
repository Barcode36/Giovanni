package com.example.giovanni.giovanni.mvplogin.login;

import android.os.Handler;
import android.text.TextUtils;

public class LoginJavaInteractor {

    public void login(final String username, final String password, final OnLoginFinishedListener listener) {
        // Mock login. I'm creating a handler to delay the answer one second.
        new Handler().postDelayed(() -> {
            if (TextUtils.isEmpty(username)) {
                listener.onUsernameError();
                return;
            }
            if (TextUtils.isEmpty(password)) {
                listener.onPasswordError();
                return;
            }
            listener.onSuccess();
        }, 1000);
    }

    interface OnLoginFinishedListener {

        void onUsernameError();
        void onPasswordError();
        void onSuccess();
    }
}