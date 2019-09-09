package com.example.giovanni.giovanni.databinding.dblayout;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.giovanni.giovanni.R;
import com.example.giovanni.giovanni.databinding.DataBindingPresenter;
import com.example.giovanni.giovanni.databinding.FragmentLayoutBinding;
import com.example.giovanni.giovanni.databinding.IDataBinding;
import com.example.giovanni.giovanni.model.DataBindingModel;

public class LayoutFragment extends Fragment implements IDataBinding.View {

    private FragmentLayoutBinding binding;
    private DataBindingPresenter presenter;
    private SharedPreferences preferences;
    boolean isGreen = true;

    public static LayoutFragment newInstance() {
        return new LayoutFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        preferences = PreferenceManager.getDefaultSharedPreferences(getContext());

        saveStateToPreferences(isGreen);

        // ----- DIRECT ----- //
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_layout, container, false);
        View view = binding.getRoot();
        presenter = new DataBindingPresenter(this, getContext());
        DataBindingModel model = new DataBindingModel(getContext());
        binding.setTemp(model);
        binding.setPresenter(presenter);
        // ------------------ //

        return view;
    }

    @Override
    public void onShowDataModel(DataBindingModel model) {}

    @Override
    public void onSetLayout(DataBindingModel model) {

        if (isGreen) {
            isGreen = false;
            saveStateToPreferences(false);
        } else {
            isGreen = true;
            saveStateToPreferences(true);
        }
        model = new DataBindingModel(getContext());
        binding.setTemp(model);
        binding.setPresenter(presenter);
    }

    private void saveStateToPreferences(boolean isGreen) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("GREEN", isGreen);
        editor.apply();
    }
}