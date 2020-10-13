package com.example.giovanni.giovanni.databinding;

import android.content.Context;
import android.content.Intent;

import com.example.giovanni.giovanni.databinding.dbmeteo.MeteoListActivity;
import com.example.giovanni.giovanni.bean.DataBindingModel;

public class DataBindingPresenter implements IDataBinding.Presenter {

    private IDataBinding.View view;
    private Context context;

    public DataBindingPresenter(IDataBinding.View view, Context context) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void onShowDataModel(DataBindingModel model) {
        view.onShowDataModel(model);
    }

    @Override
    public void onShowList() {
        Intent intent = new Intent(context, MeteoListActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onSetLayout(DataBindingModel model) {
        view.onSetLayout(model);
    }
}