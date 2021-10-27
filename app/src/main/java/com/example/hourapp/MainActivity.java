package com.example.hourapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.hourapp.adapter.RecyclerViewAdapter;
import com.example.hourapp.model.Hour;
import com.example.hourapp.model.ViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.onRecordClickListener {

    public static final String Hour_ID = "hour_id";
    private static final int NEW_HOUR_ACTIVITY_RESULT_CODE = 1;
    public static final String RECORD_ID = "record_id";
    private static final int ID_REQUEST_CODE = 5;
    public static final String DESC_ID = "desc_ID";
    public static final String DATE_ID = "date_ID";
    public static final String NO_OF_HOURS_ID = "noOfhours_id";
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private ViewModel viewModel;
    FloatingActionButton addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        viewModel = new ViewModelProvider.AndroidViewModelFactory(MainActivity.this
                .getApplication())
                .create(ViewModel.class);

        viewModel.getAllRecords().observe(this, hours -> {
            recyclerViewAdapter = new RecyclerViewAdapter(hours, MainActivity.this, this);
            recyclerView.setAdapter(recyclerViewAdapter);
        });

        addButton = findViewById(R.id.addButton_home);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddingScreen.class);
                startActivityForResult(intent, NEW_HOUR_ACTIVITY_RESULT_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        assert data != null;
        String date = data.getStringExtra(AddingScreen.DATE_KEY);
            int noOfHours = Integer.parseInt(data.getStringExtra(AddingScreen.NOOFHOURS_KEY));
            String desc_S = data.getStringExtra(AddingScreen.DESC_KEY);
            Log.d("TAG-V", "onActivityResult: Date " + date);
            Log.d("TAG-V", "onActivityResult: Hours" + noOfHours);

            Hour hour = new Hour(date, noOfHours, desc_S);
            ViewModel.insert(hour);

            finish();

    }

    @Override
    public void onRecordClick(int position) {
        Hour hour = Objects.requireNonNull(viewModel.allRecords.getValue()).get(position);
        Intent intent = new Intent(MainActivity.this, Detailed_info.class);
        intent.putExtra(RECORD_ID, hour.getId());
        intent.putExtra(DESC_ID, hour.getDescription());
        intent.putExtra(DATE_ID, hour.getDate());
        intent.putExtra(NO_OF_HOURS_ID, hour.getNoOfHours());
        Log.d("TAG-V", "onRecordClick: " +  hour.getId());
        startActivity(intent);
    }
}   