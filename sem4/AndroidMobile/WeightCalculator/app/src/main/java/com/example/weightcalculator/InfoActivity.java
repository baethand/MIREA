package com.example.weightcalculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.weightcalculator.databinding.ActivityMainBinding;
import com.example.weightcalculator.databinding.ActivitySecondBinding;

public class InfoActivity extends AppCompatActivity {

    private ActivitySecondBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivitySecondBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("result")) {
            String result = intent.getStringExtra("result");
            binding.resultText.setText(result);
        }

        binding.btnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
