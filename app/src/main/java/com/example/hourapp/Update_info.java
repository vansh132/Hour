package com.example.hourapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.hourapp.model.Hour;
import com.example.hourapp.model.ViewModel;

public class Update_info extends AppCompatActivity {

    private EditText date, no_of_hours, desc;
    private Button update;
    private ViewModel viewModel;
    private int recordID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_info);

        date = findViewById(R.id.date_update);
        no_of_hours = findViewById(R.id.noOfHours_update);
        desc = findViewById(R.id.desc_update);

        String dateS = getIntent().getStringExtra("d_update_date");
        int noOfHours_s = Integer.parseInt(getIntent().getStringExtra("d_update_noOfHours"));
        String descS = getIntent().getStringExtra("d_update_descS");

        viewModel = new ViewModelProvider.AndroidViewModelFactory(Update_info.this
                .getApplication())
                .create(ViewModel.class);

        desc = findViewById(R.id.desc_update);
        update = findViewById(R.id.update_button_updateXML);
        recordID = getIntent().getIntExtra("d_update", 0);

        date.setText(dateS);
        no_of_hours.setText(noOfHours_s);
        desc.setText(descS);
    }

}