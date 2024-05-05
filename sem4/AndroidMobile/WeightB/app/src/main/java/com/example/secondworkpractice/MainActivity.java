package com.example.secondworkpractice;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements MainFragment.MainFragmentListener, ResultFragment.SecondFragmentListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new MainFragment())
                    .commit();
        }
    }
    @Override
    public void onBackButtonClick() {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void onButtonClick(Bundle data) {
        ResultFragment secondFragment = new ResultFragment();
        secondFragment.setArguments(data);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, secondFragment)
                .addToBackStack(null)
                .commit();
    }
}
