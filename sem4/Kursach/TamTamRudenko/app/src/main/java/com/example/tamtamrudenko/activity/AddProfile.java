package com.example.tamtamrudenko.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.tamtamrudenko.databinding.ActivityAddProfileBinding;
import com.example.tamtamrudenko.models.Const;
import com.example.tamtamrudenko.models.User;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class AddProfile extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseUser user;
    DatabaseReference mDataBase;
    private ActivityAddProfileBinding binding;
    StorageReference mStorageReference;
    private Uri uploadUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddProfileBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        mDataBase = FirebaseDatabase
                .getInstance(Const.DB_URL)
                .getReference(Const.KEY_USER);
        mStorageReference = FirebaseStorage.getInstance().getReference();

        binding.chooseImageButton.setOnClickListener(onClickChooseImage);

        checkTheAuth();
        binding.btnNext.setOnClickListener(initAddUserInfo);
    }

    View.OnClickListener onClickChooseImage = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            getImage();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 200 && data != null && data.getData() != null){
            if (resultCode == RESULT_OK){
                binding.chooseImageButton.setImageURI(data.getData());
                uploadImage();
            }
        }
    }

    private void getImage(){
        Intent intentChooser = new Intent();
        intentChooser.setType("image/*");
        intentChooser.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intentChooser, 200);
    }

    private void uploadImage(){
        Bitmap bitmap = ((BitmapDrawable) binding.chooseImageButton.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 40, baos);
        byte[] byteArray = baos.toByteArray();
        StorageReference mRef = mStorageReference.child(user.getUid()+"-profileImage");
        UploadTask uploadTask = mRef.putBytes(byteArray);
        Task<Uri> task = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                return mRef.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                uploadUrl = task.getResult();
            }
        });
    }



    View.OnClickListener initAddUserInfo = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            binding.progressBar.setVisibility(View.VISIBLE);

            User user = prepareUserInfo();

            if (user == null){
                return;
            }

            mDataBase.child(user.getId()).setValue(user);
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
        if (uploadUrl == null){
            Toast.makeText(AddProfile.this, "Картинка не сохранена", Toast.LENGTH_SHORT).show();
            uploadUrl = Uri.parse("");
        }

        String surname = String.valueOf(binding.surname.getText()).trim();
        surname = (!surname.equals("")) ? surname : "none";

        String description = String.valueOf(binding.description.getText()).trim();
        description = (!description.equals("")) ? description : "none";

        Boolean isCreator = binding.isCreator.isChecked();
        String id = user.getUid();
        return new User(id, name, surname, age, description, new ArrayList<>(), isCreator, uploadUrl.toString());
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