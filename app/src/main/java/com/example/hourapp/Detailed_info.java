package com.example.hourapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.hourapp.model.Hour;
import com.example.hourapp.model.ViewModel;

import java.util.List;
import java.util.Objects;

public class Detailed_info extends AppCompatActivity {

    public static final int REQUEST_CODE_UPDATE = 2;
    private TextView date, hr, desc;
    private Button update, delete;
    private ViewModel v;
    private int recordId = 0;
    public final static String Update_KEY = "update_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_info);

        date = findViewById(R.id.date_detail);
        hr = findViewById(R.id.noOfHours_detail);
        desc = findViewById(R.id.desc_detail);
        update = findViewById(R.id.updateButton);
        delete = findViewById(R.id.deleteButton);

        v = new ViewModelProvider.AndroidViewModelFactory(Detailed_info.this
            .getApplication())
                .create(ViewModel.class);

        String dateS = getIntent().getStringExtra(MainActivity.DATE_ID);
        String descS = getIntent().getStringExtra(MainActivity.DESC_ID);
        recordId =getIntent().getIntExtra(MainActivity.RECORD_ID, 0);
        int no_of_hours_S = getIntent().getIntExtra(MainActivity.Hour_ID, 0);

        if (getIntent().hasExtra(MainActivity.RECORD_ID)) {

            v.get(recordId).observe(this, new Observer<Hour>() {
                @Override
                public void onChanged(Hour hour) {
                    if (hour != null) {
                        String hoursT = String.valueOf(hour.getNoOfHours());
                        date.setText(dateS);
                        hr.setText(hoursT);
                        desc.setText(descS);
                    }
                }
            });
        }

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Hour hour = new Hour();
                hour.setId(recordId);
                hour.setDescription(descS);
                hour.setNoOfHours(no_of_hours_S);
                hour.setDate(dateS);
                ViewModel.delete(hour);
                finish();
            }
        });
    }
}