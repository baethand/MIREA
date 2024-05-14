package com.example.taxiapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import com.example.taxiapp.databinding.ActivityMainBinding;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private SharedPreferences sharedPreferences;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE);

        // Восстановить данные пользователя при повторном запуске приложения
        binding.phone.setText(sharedPreferences.getString("phone", ""));
        binding.firstName.setText(sharedPreferences.getString("firstName", ""));
        binding.lastName.setText(sharedPreferences.getString("lastName", ""));

        binding.registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Сохранить данные пользователя с помощью SharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("phone", binding.phone.getText().toString());
                editor.putString("firstName", binding.firstName.getText().toString());
                editor.putString("lastName", binding.lastName.getText().toString());
                editor.apply();

                // Передать данные пользователя во второе Activity
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("phone", binding.phone.getText().toString());
                intent.putExtra("firstName", binding.firstName.getText().toString());
                intent.putExtra("lastName", binding.lastName.getText().toString());
                startActivity(intent);
            }
        });
    }



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