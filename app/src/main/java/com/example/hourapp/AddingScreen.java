package com.example.hourapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.hourapp.model.Hour;
import com.example.hourapp.model.ViewModel;

public class AddingScreen extends AppCompatActivity {

    public static final String DESC_KEY = "desc_KEY";
    private EditText date;
    private EditText noOfHours;
    private EditText descEt;
    private Button saveButton;
    private ViewModel viewModel;
    private int recordId = 0;
    private String HoursId = "hours_id";
    public static final String DATE_KEY = "date_key";
    public static final String NOOFHOURS_KEY = "noOfHours_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_screen);

        date = findViewById(R.id.etDate);
        noOfHours = findViewById(R.id.etNumberOfHours);
        descEt = findViewById(R.id.etDesc);
        saveButton = findViewById(R.id.saveButton);

        viewModel = new ViewModelProvider.AndroidViewModelFactory(AddingScreen.this
                .getApplication())
                .create(ViewModel.class);

        recordId = getIntent().getIntExtra(MainActivity.Hour_ID, 0);

        viewModel.get(recordId).observe(this, hour -> {
            if (hour != null) {
                date.setText(hour.getDate());
                noOfHours.setText(hour.getNoOfHours());
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();

                if (!TextUtils.isEmpty(date.getText())
                && !TextUtils.isEmpty(noOfHours.getText())) {
                    String date_s = date.getText().toString();
                    String noOfHours_s = noOfHours.getText().toString();
                    String desc_s = descEt.getText().toString();
                    Log.d("TAG-V", "onClick: " + date_s + " " + noOfHours_s);
                    intent.putExtra(DATE_KEY, date_s);
                    intent.putExtra(NOOFHOURS_KEY, noOfHours_s);
                    intent.putExtra(DESC_KEY, desc_s);
                    setResult(RESULT_OK, intent);
                } else {
                    Log.d("TAG-V", "onClick: Cancelled");
                    setResult(RESULT_CANCELED, intent);
                }
                finish();
            }
        });
    }
}