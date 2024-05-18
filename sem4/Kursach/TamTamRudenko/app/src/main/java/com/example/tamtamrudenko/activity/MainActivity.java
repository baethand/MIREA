package com.example.tamtamrudenko.activity;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.tamtamrudenko.R;
import com.example.tamtamrudenko.databinding.ActivityMainBinding;
import com.example.tamtamrudenko.fragments.CreateEventFragment;
import com.example.tamtamrudenko.fragments.EventsFragment;
import com.example.tamtamrudenko.fragments.MineEventsFragment;
import com.example.tamtamrudenko.fragments.ProfileFragment;
import com.example.tamtamrudenko.models.Const;
import com.example.tamtamrudenko.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements ProfileFragment.ProfileListener {

    FirebaseAuth auth;
    FirebaseUser user;
    User userInfo;
    FirebaseDatabase database;
    private ActivityMainBinding binding;
    DatabaseReference userRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance(Const.DB_URL);

        user = auth.getCurrentUser();

        userRef = database.getReference(Const.KEY_USER).child(user.getUid());

        if (user == null){
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        }
        else {
            binding.userDetails.setText(user.getEmail());
        }


        setNewFragment(new EventsFragment());

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
                EventsFragment eventsFragment = new EventsFragment();
                setNewFragment(eventsFragment);
            }
        });

        binding.btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateEventFragment createEventFragment = new CreateEventFragment();
                setNewFragment(createEventFragment);
            }
        });

        binding.btnMine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MineEventsFragment mineEventsFragment = new MineEventsFragment();
                setNewFragment(mineEventsFragment);
            }
        });

        binding.btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProfileFragment fragment = new ProfileFragment();
                setNewFragment(fragment);
            }
        });
        userInfo = updateUserInfo();
    }

    public void setNewFragment(Fragment fragment){
        userInfo = updateUserInfo();
        Bundle bundle = new Bundle();
        bundle.putParcelable("user", userInfo);
        fragment.setArguments(bundle);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(binding.fragmentMain.getId(), fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    public User updateUserInfo(){
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userInfo = dataSnapshot.getValue(User.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Обработка ошибки
                Log.d(TAG, "Error getting user data: " + databaseError.getMessage());
            }
        });

        return userInfo;
    };

    @Override
    public void sendUser(User user) {
        userInfo = user;
    }


}