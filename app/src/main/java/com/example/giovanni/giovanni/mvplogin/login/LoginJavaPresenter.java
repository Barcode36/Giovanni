package com.example.giovanni.giovanni.mvplogin.login;

public class LoginJavaPresenter implements LoginJavaInteractor.OnLoginFinishedListener {

    private LoginView loginView;
    private LoginJavaInteractor loginInteractor;

    LoginJavaPresenter(LoginView loginView, LoginJavaInteractor loginInteractor) {
        this.loginView = loginView;
        this.loginInteractor = loginInteractor;
    }

    public void validateCredentials(String username, String password) {
        if (loginView != null) {
            loginView.showProgress();
        }
        loginInteractor.login(username, password, this);
    }

    public void onDestroy() {
        loginView = null;
    }

    @Override
    public void onUsernameError() {
        if (loginView != null) {
            loginView.setUsernameError();
            loginView.hideProgress();
        }
    }

    @Override
    public void onPasswordError() {
        if (loginView != null) {
            loginView.setPasswordError();
            loginView.hideProgress();
        }
    }

    @Override
    public void onSuccess() {
        if (loginView != null) {
            loginView.navigateToHome();
        }
    }

    public interface LoginView {

        void showProgress();
        void hideProgress();
        void setUsernameError();
        void setPasswordError();
        void navigateToHome();
    }
}