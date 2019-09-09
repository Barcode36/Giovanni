package com.example.giovanni.giovanni.databinding.dbmeteo;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.giovanni.giovanni.R;
import com.example.giovanni.giovanni.databinding.ActivityMeteoBinding;
import com.example.giovanni.giovanni.databinding.DataBindingPresenter;
import com.example.giovanni.giovanni.databinding.IDataBinding;
import com.example.giovanni.giovanni.model.DataBindingModel;

public class MeteoActivity extends AppCompatActivity implements IDataBinding.View {

    private ActivityMeteoBinding binding;
    private DataBindingPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_meteo);
        presenter = new DataBindingPresenter(this, this);
        DataBindingModel model = new DataBindingModel("Napoli", "30", true);

        binding.setTemp(model);
        binding.setPresenter(presenter);
    }

    @Override
    public void onShowDataModel(DataBindingModel model) {

        String luogo = model.getLuogo();
        String temperatura = model.getTemperatura();

        if (luogo != null && temperatura != null) {
            if (luogo.equals("") || temperatura.equals(""))
                Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "Meteo: " + luogo + ", " + temperatura + "°", Toast.LENGTH_SHORT).show();

            if (model.isSfondo()) {
                model = new DataBindingModel(luogo, temperatura, false);
                binding.setTemp(model);
                binding.setPresenter(presenter);
                model.setSfondo(false);
            } else {
                model = new DataBindingModel(luogo, temperatura, true);
                binding.setTemp(model);
                binding.setPresenter(presenter);
                model.setSfondo(true);
            }
        }
    }

    @Override
    public void onSetLayout(DataBindingModel model) {}
}