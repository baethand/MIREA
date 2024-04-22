package com.example.tamtamrudenko;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.tamtamrudenko.databinding.ActivityRegisterBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {
    private ActivityRegisterBinding binding;
    FirebaseAuth mAuth;

    @Override
    public void onStart() {
        super.onStart();
        // Check if User is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        mAuth = FirebaseAuth.getInstance();

        binding.btnRegister.setOnClickListener(initRegistration);

        binding.loginNow.setOnClickListener(goToLogin);
    }


    View.OnClickListener goToLogin = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        }
    };

    View.OnClickListener initRegistration = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            binding.progressBar.setVisibility(View.VISIBLE);
            String email, password;

            email = String.valueOf(binding.email.getText()).trim().toLowerCase();
            password = String.valueOf(binding.password.getText()).trim();

            if (TextUtils.isEmpty(email)){
                Toast.makeText(Register.this, "Please enter email", Toast.LENGTH_SHORT).show();
                binding.progressBar.setVisibility(View.GONE);
                return;
            }
            if (TextUtils.isEmpty(password)){
                Toast.makeText(Register.this, "Please enter password", Toast.LENGTH_SHORT).show();
                binding.progressBar.setVisibility(View.GONE);
                return;
            }

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                binding.progressBar.setVisibility(View.GONE);
                                Toast.makeText(Register.this, "Registration completed.",
                                        Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), AddProfile.class);
                                startActivity(intent);
                                finish();
                            } else {
                                binding.progressBar.setVisibility(View.GONE);
                                Toast.makeText(Register.this, "Registration failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

        }
    };
}