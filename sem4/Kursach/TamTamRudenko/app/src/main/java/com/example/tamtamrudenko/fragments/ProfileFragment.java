package com.example.tamtamrudenko.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tamtamrudenko.adapters.EventAdapter;
import com.example.tamtamrudenko.databinding.FragmentProfileBinding;
import com.example.tamtamrudenko.models.Const;
import com.example.tamtamrudenko.models.Event;
import com.example.tamtamrudenko.models.User;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.example.tamtamrudenko.transform.*;

import java.io.ByteArrayOutputStream;

public class ProfileFragment extends Fragment {

    private User user;
    private Uri uploadUrl;

    DatabaseReference mDataBase;
    StorageReference mStorageReference;

    private FragmentProfileBinding binding;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            user = args.getParcelable("user");
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    ProfileListener profileListener;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        profileListener = (ProfileListener) context;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mDataBase = FirebaseDatabase
                .getInstance(Const.DB_URL)
                .getReference(Const.KEY_USER);
        mStorageReference = FirebaseStorage.getInstance().getReference();

        updateProfileInfo(user);


        binding.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.profileScrollView.setVisibility(View.GONE);
                binding.editScrollView.setVisibility(View.VISIBLE);
                if (user.getUserImageUrl() != null && !user.getUserImageUrl().trim().equals("")){
                    Picasso.get().load(user.getUserImageUrl()).transform(new RoundedCornersTransform()).into(binding.chooseImageButton);
                    uploadUrl = Uri.parse(user.getUserImageUrl());
                }
                binding.namePole.setText(user.getName());
                binding.surname.setText(user.getSurname());
                binding.agePole.setText(user.getAge().toString());
                binding.descriptionPole.setText(user.getDescription());
            }
        });
        binding.btnDone.setOnClickListener(initAddUserInfo);
        binding.chooseImageButton.setOnClickListener(onClickChooseImage);
    }

    View.OnClickListener initAddUserInfo = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            User user = prepareUserInfo();

            if (user == null){
                return;
            }

            mDataBase.child(user.getId()).setValue(user);

            updateProfileInfo(user);

            binding.editScrollView.setVisibility(View.GONE);
            binding.profileScrollView.setVisibility(View.VISIBLE);
            profileListener.sendUser(user);

        }
    };

    private User prepareUserInfo(){
        Integer age;
        String name = String.valueOf(binding.namePole.getText()).trim();
        try {
            age = Integer.parseInt(String.valueOf(binding.agePole.getText()).trim());
        } catch (Exception e){
            return null;
        }

        if (TextUtils.isEmpty(name)){
            return null;
        }
        if (age < 12 && age > 112){
            return null;
        }
        if (uploadUrl == null){
            uploadUrl = Uri.parse("");
        }

        String surname = String.valueOf(binding.surname.getText()).trim();
        surname = (!surname.equals("")) ? surname : "none";

        String description = String.valueOf(binding.descriptionPole.getText()).trim();
        description = (!description.equals("")) ? description : "none";

        Boolean isCreator = binding.isCreator.isChecked();
        String id = user.getId();
        return new User(id, name, surname, age, description, user.getEvents(), isCreator, uploadUrl.toString());
    }

    View.OnClickListener onClickChooseImage = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            getImage();
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 200 && data != null && data.getData() != null){
            binding.chooseImageButton.setImageURI(data.getData());
            uploadImage();
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
        StorageReference mRef = mStorageReference.child(Const.KEY_PROFILE_IMAGES).child(user.getId()+"-profileImage");
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

    private void updateProfileInfo(User user){
        binding.name.setText(user.getName() + " " + user.getSurname());
        binding.age.setText(user.getAge().toString());
        binding.description.setText(user.getDescription());

        if (user.isCreator()){
            binding.eventCreator.setVisibility(View.VISIBLE);
        }
        else {
            binding.eventCreator.setVisibility(View.GONE);
        }
        if (user.getUserImageUrl() != null && !user.getUserImageUrl().trim().equals(""))
            Picasso.get().load(user.getUserImageUrl()).transform(new RoundedCornersTransform()).into(binding.profileImage);
    }


    //DEFINE THE INTERFACE
    public interface ProfileListener {
        void sendUser(User user);
    }
}