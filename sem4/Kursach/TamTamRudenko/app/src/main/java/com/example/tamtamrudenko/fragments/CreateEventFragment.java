package com.example.tamtamrudenko.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tamtamrudenko.databinding.FragmentCreateEventBinding;
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

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.UUID;

public class CreateEventFragment extends Fragment {

    private FragmentCreateEventBinding binding;
    DatabaseReference mDataBase;
    StorageReference mStorageReference;
    User user;
    Event event;
    UUID uuid;

    Uri uploadUrl;

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
        binding = FragmentCreateEventBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mDataBase = FirebaseDatabase
                .getInstance(Const.DB_URL)
                .getReference(Const.KEY_EVENTS);
        mStorageReference = FirebaseStorage.getInstance().getReference();
        uuid = UUID.randomUUID();

        binding.btnImageEvent.setOnClickListener(onClickChooseImage);

        binding.btnCreate.setOnClickListener(initAddEventInfo);
    }

    View.OnClickListener initAddEventInfo = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            event = prepareEventObject();

            if (event == null){
                return;
            }

            mDataBase.child(event.getId()).setValue(event);

        }
    };

    private Event prepareEventObject(){
        Event event = new Event();
        Integer seats;

        event.setUsersId(new ArrayList<>());
        event.getUsersId().add(user.getId());

        String name = String.valueOf(binding.eventName.getText()).trim();
        try {
            seats = Integer.parseInt(String.valueOf(binding.seats.getText()).trim());
        } catch (Exception e){
            return null;
        }

        if (TextUtils.isEmpty(name)){
            return null;
        }
        if (seats < 1 || seats > 10000){
            return null;
        }
        if (uploadUrl == null){
            uploadUrl = Uri.parse("");
        }

        event.setId(uuid.toString());
        event.setName(name);
        event.setSeats(seats);
        event.setEventImageUrl(uploadUrl.toString());
        event.setDescription(binding.eventDescription.getText().toString());

        return event;
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
            binding.btnImageEvent.setImageURI(data.getData());
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
        Bitmap bitmap = ((BitmapDrawable) binding.btnImageEvent.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 40, baos);
        byte[] byteArray = baos.toByteArray();
        StorageReference mRef = mStorageReference.child(Const.KEY_EVENTS_IMAGES).child(uuid.toString() +"-eventImage");
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
}