package com.example.giovanni.giovanni.mvpaddtextchangedlistener;

public class AddTextPresenter {

    private User user;
    private View view;

    AddTextPresenter(View view) {
        this.user = new User();
        this.view = view;
    }

    void updateUsername(String username) {
        user.setUsername(username);
        view.updateUserInfoTextView(user.toString());
    }

    void updateEmail(String email) {
        user.setEmail(email);
        view.updateUserInfoTextView(user.toString());
    }

    public interface View {
        void updateUserInfoTextView(String info);
        void showProgressBar();
        void hideProgressBar();
    }
}