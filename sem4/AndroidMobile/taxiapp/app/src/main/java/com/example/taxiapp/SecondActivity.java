package com.example.taxiapp;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.activity.result.contract.ActivityResultContracts;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.util.Log;

import com.example.taxiapp.databinding.ActivitySecondBinding;

public class SecondActivity extends AppCompatActivity {

    private ActivitySecondBinding binding;
    private static final String TAG = "SecondActivity";


    private final ActivityResultLauncher<Intent> startThirdActivityForResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK) {
                    // Get data from Intent
                    Intent data = result.getData();
                    String startPoint = data.getStringExtra("startPoint");
                    String endPoint = data.getStringExtra("endPoint");
                    String distance = data.getStringExtra("distance");
                    String estimatedTime = data.getStringExtra("estimatedTime");
                    String numberOfPassengers = data.getStringExtra("numberOfPassengers");
                    String additionalInfo = data.getStringExtra("additionalInfo");

                    // Create a string with route info
                    String routeInfo = "Start point: " + startPoint + "\n" +
                            "End point: " + endPoint + "\n" +
                            "Distance: " + distance + " km\n" +
                            "Estimated time: " + estimatedTime + " min\n" +
                            "Number of passengers: " + numberOfPassengers + "\n" +
                            "Additional information: " + additionalInfo;

                    // Update the text field
                    binding.routeInfo.setText(routeInfo);

                    // Enable the Call Taxi button
                    binding.callTaxi.setEnabled(true);
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        binding = ActivitySecondBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // Get user data from the first Activity
        String phone = getIntent().getStringExtra("phone");
        String firstName = getIntent().getStringExtra("firstName");
        String lastName = getIntent().getStringExtra("lastName");

        binding.userInfo.setText("Phone: " + phone + "\nName: " + firstName + " " + lastName);

        binding.setPath.setOnClickListener(v -> {
            // Start the third Activity for route input
            Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
            startThirdActivityForResult.launch(intent);
        });

        binding.callTaxi.setOnClickListener(v -> Toast.makeText(SecondActivity.this, "Taxi has been successfully sent!", Toast.LENGTH_SHORT).show());
    }

    // Rest of the lifecycle methods...
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }
}