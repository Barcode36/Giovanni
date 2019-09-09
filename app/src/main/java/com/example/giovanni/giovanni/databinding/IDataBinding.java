package com.example.giovanni.giovanni.databinding;

import com.example.giovanni.giovanni.model.DataBindingModel;

public interface IDataBinding {

    public interface Presenter {
        void onShowDataModel(DataBindingModel model);
        void onShowList();
        void onSetLayout(DataBindingModel model);
    }

    public interface View {
        void onShowDataModel(DataBindingModel model);
        void onSetLayout(DataBindingModel model);
    }
}