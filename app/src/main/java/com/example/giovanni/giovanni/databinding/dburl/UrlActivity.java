package com.example.giovanni.giovanni.databinding.dburl;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.giovanni.giovanni.R;
import com.example.giovanni.giovanni.databinding.IDataBinding;
import com.example.giovanni.giovanni.bean.DataBindingModel;

import java.util.Arrays;
import java.util.List;

public class UrlActivity extends AppCompatActivity implements IDataBinding.View {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_binding_list);

        RecyclerView recyclerView = findViewById(R.id.list_meteo);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        List<DataBindingModel> list = Arrays.asList(
                new DataBindingModel("Napoli", "30°", getResources().getString(R.string.url_1)),
                new DataBindingModel("Milano", "25°", getResources().getString(R.string.url_2)),
                new DataBindingModel("Roma", "28°", getResources().getString(R.string.url_3)),
                new DataBindingModel("Torino", "20°", getResources().getString(R.string.url_4)),
                new DataBindingModel("Palermo", "35°", ""),
                new DataBindingModel("Firenze", "28°", "")
        );

        RecyclerView.Adapter adapter = new UrlAdapter(list);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onShowDataModel(DataBindingModel model) {}

    @Override
    public void onSetLayout(DataBindingModel model) {}
}