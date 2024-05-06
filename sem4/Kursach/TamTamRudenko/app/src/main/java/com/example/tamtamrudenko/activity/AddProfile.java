package com.example.tamtamrudenko.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.tamtamrudenko.databinding.ActivityAddProfileBinding;
import com.example.tamtamrudenko.models.Const;
import com.example.tamtamrudenko.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AddProfile extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseUser user;
    DatabaseReference mDataBase;
    private ActivityAddProfileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddProfileBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        mDataBase = FirebaseDatabase
                .getInstance(Const.DB_URL)
                .getReference(Const.KEY_USER);
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        checkTheAuth();
        binding.btnNext.setOnClickListener(initAddUserInfo);
    }

    View.OnClickListener initAddUserInfo = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            binding.progressBar.setVisibility(View.VISIBLE);

            User user = prepareUserInfo();

            if (user == null){
                return;
            }

            mDataBase.push().setValue(user);
            binding.progressBar.setVisibility(View.GONE);
            Toast.makeText(AddProfile.this, "Successfully added!", Toast.LENGTH_SHORT).show();
            goToMain();
        }
    };

    private User prepareUserInfo(){
        Integer age;
        String name = String.valueOf(binding.name.getText()).trim();
        try {
            age = Integer.parseInt(String.valueOf(binding.age.getText()).trim());
        } catch (Exception e){
            Toast.makeText(AddProfile.this, "Not valid Age", Toast.LENGTH_SHORT).show();
            binding.progressBar.setVisibility(View.GONE);
            return null;
        }

        if (TextUtils.isEmpty(name)){
            Toast.makeText(AddProfile.this, "Not valid Name", Toast.LENGTH_SHORT).show();
            binding.progressBar.setVisibility(View.GONE);
            return null;
        }
        if (age < 12 && age > 112){
            Toast.makeText(AddProfile.this, "Unavailable age", Toast.LENGTH_SHORT).show();
            binding.progressBar.setVisibility(View.GONE);
            return null;
        }

        String surname = String.valueOf(binding.surname.getText()).trim();
        surname = (!surname.equals("")) ? surname : "none";

        String description = String.valueOf(binding.description.getText()).trim();
        description = (!description.equals("")) ? description : "none";

        Boolean isCreator = binding.isCreator.isChecked();
        String id = user.getUid();
        return new User(id, name, surname, age, description, new ArrayList<>(), isCreator);
    }

    public void checkTheAuth(){
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        if (user == null){
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        }
    }

    public void goToMain() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }
}