package com.example.tamtamrudenko;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.tamtamrudenko.databinding.ActivityAddProfileBinding;
import com.example.tamtamrudenko.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.ktx.Firebase;

import java.util.UUID;

public class AddProfile extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseUser user;
    DatabaseReference mDataBase;
    private ActivityAddProfileBinding binding;
    private static String KEY_USER = "user";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddProfileBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        mDataBase = FirebaseDatabase.getInstance().getReference(KEY_USER);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");
        checkTheAuth();
        binding.btnNext.setOnClickListener(initAddUserInfo);
    }

    View.OnClickListener initAddUserInfo = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            binding.progressBar.setVisibility(View.VISIBLE);

            String name = String.valueOf(binding.name.getText()).trim();
            Integer age = Integer.parseInt(String.valueOf(binding.age.getText()));

            if (TextUtils.isEmpty(name)){
                Toast.makeText(AddProfile.this, "Please enter valid Name (2+ chars)", Toast.LENGTH_SHORT).show();
                binding.progressBar.setVisibility(View.GONE);
                return;
            }
            if (TextUtils.isEmpty(String.valueOf(age))){
                Toast.makeText(AddProfile.this, "Please enter valid age (12+)", Toast.LENGTH_SHORT).show();
                binding.progressBar.setVisibility(View.GONE);
                return;
            }

            String surname = String.valueOf(binding.surname.getText()).trim();
            String description = String.valueOf(binding.description.getText()).trim();

            String id = mDataBase.getKey();
            User user = new User(id, name, surname, age, description);
            mDataBase.setValue("hello");
            Toast.makeText(AddProfile.this, "Successfully added!", Toast.LENGTH_SHORT).show();
        }
    };

    public void checkTheAuth(){
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        if (user == null){
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        }
    }
}