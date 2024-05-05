package com.example.tamtamrudenko.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.tamtamrudenko.R;
import com.example.tamtamrudenko.databinding.ActivityMainBinding;
import com.example.tamtamrudenko.fragments.EditProfileFragment;
import com.example.tamtamrudenko.fragments.EventsFragment;
import com.example.tamtamrudenko.fragments.ProfileFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseUser user;
    private ActivityMainBinding binding;
    EditProfileFragment editProfileFragment = new EditProfileFragment();

    FrameLayout frameLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        frameLayout = findViewById(R.id.fragment_main);


        if (user == null){
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        }
        else {
            binding.userDetails.setText(user.getUid());
        }

        binding.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });



        binding.btnEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditProfileFragment profileFragment = new EditProfileFragment();
                setNewFragment(profileFragment);
            }
        });

        binding.btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventsFragment eventsFragment = new EventsFragment();
                setNewFragment(eventsFragment);
            }
        });

        binding.btnMine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        binding.btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ProfileFragment profileFragment = new ProfileFragment();
                setNewFragment(profileFragment);
            }
        });
    }

    public void setNewFragment(Fragment fragment){
        Toast.makeText(MainActivity.this, "Start new fragment", Toast.LENGTH_SHORT).show();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_main, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }
}