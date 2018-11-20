package com.example.giovanni.giovanni.mvplogin.main;

import java.util.List;

public class MainJavaPresenter {

    private MainView mainView;
    private FindItemsInteractorJava findItemsInteractor;

    MainJavaPresenter(MainView mainView, FindItemsInteractorJava findItemsInteractor) {
        this.mainView = mainView;
        this.findItemsInteractor = findItemsInteractor;
    }

    void onResume() {
        if (mainView != null) {
            mainView.showProgress();
        }
        findItemsInteractor.findItems(this::onFinished);
    }

    void onItemClicked(String item) {
        if (mainView != null) {
            mainView.showMessage(String.format("%s clicked", item));
        }
    }

    void onDestroy() {
        mainView = null;
    }

    public void onFinished(List<String> items) {
        if (mainView != null) {
            mainView.setItems(items);
            mainView.hideProgress();
        }
    }

    public MainView getMainView() {
        return mainView;
    }

    public interface MainView {

        void showProgress();
        void hideProgress();
        void setItems(List<String> items);
        void showMessage(String message);
    }
}