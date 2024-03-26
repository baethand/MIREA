package com.example.weightcalculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class InfoActivity extends AppCompatActivity {

    TextView resultText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);

        resultText = findViewById(R.id.resultText);
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("result")) {
            String result = intent.getStringExtra("result");
            resultText.setText(result);
        }
        Button goActivity1 = findViewById(R.id.btn_Main);
        goActivity1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
