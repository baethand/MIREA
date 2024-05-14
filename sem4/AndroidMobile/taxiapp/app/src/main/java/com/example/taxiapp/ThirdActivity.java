package com.example.taxiapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import com.example.taxiapp.databinding.ActivityThirdBinding;

public class ThirdActivity extends AppCompatActivity {

    private ActivityThirdBinding binding;
    private static final String TAG = "ThirdActivity";
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        binding = ActivityThirdBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        mediaPlayer = MediaPlayer.create(this, R.raw.mortal);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();

        binding.soundSwitch.setChecked(true);


        binding.soundSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                mediaPlayer.start();
            } else {
                mediaPlayer.pause();
            }
        });

        // Проверить состояние soundSwitch и запустить музыку, если он включен
        if (binding.soundSwitch.isChecked()) {
            mediaPlayer.start();
        }

        // Инициализировать EditText для ввода параметров маршрута движения
        binding.editText1.setHint("Start point");
        binding.editText2.setHint("End point");
        binding.editText3.setHint("Distance (km)");
        binding.editText4.setHint("Estimated time (min)");
        binding.editText5.setHint("Number of passengers");
        binding.editText6.setHint("Additional information");

        binding.ok.setOnClickListener(v -> {
            // Собрать данные маршрута
            String startPoint = binding.editText1.getText().toString();
            String endPoint = binding.editText2.getText().toString();
            String distance = binding.editText3.getText().toString();
            String estimatedTime = binding.editText4.getText().toString();
            String numberOfPassengers = binding.editText5.getText().toString();
            String additionalInfo = binding.editText6.getText().toString();

            // Проверить, что все поля заполнены
            if (startPoint.isEmpty() || endPoint.isEmpty() || distance.isEmpty() || estimatedTime.isEmpty() || numberOfPassengers.isEmpty()) {
                Toast.makeText(ThirdActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            // Вернуть данные маршрута во вторую активность
            Intent intent = new Intent();
            intent.putExtra("startPoint", startPoint);
            intent.putExtra("endPoint", endPoint);
            intent.putExtra("distance", distance);
            intent.putExtra("estimatedTime", estimatedTime);
            intent.putExtra("numberOfPassengers", numberOfPassengers);
            intent.putExtra("additionalInfo", additionalInfo);
            setResult(RESULT_OK, intent);
            finish();
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
        mediaPlayer.stop();
        mediaPlayer.release();
        Log.d(TAG, "onDestroy");
    }
}

