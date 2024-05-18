package com.example.tamtamrudenko.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tamtamrudenko.R;
import com.example.tamtamrudenko.databinding.FragmentEventDetailBinding;
import com.example.tamtamrudenko.databinding.FragmentEventsBinding;
import com.example.tamtamrudenko.models.Const;
import com.example.tamtamrudenko.models.Event;
import com.example.tamtamrudenko.models.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class EventDetailFragment extends Fragment {

    private Event event;
    private User user;
    DatabaseReference mDataBase;
    StorageReference mStorageReference;
    private FragmentEventDetailBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            event = args.getParcelable("event");
            user = args.getParcelable("user");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEventDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mDataBase = FirebaseDatabase
                .getInstance(Const.DB_URL).getReference();
        mStorageReference = FirebaseStorage.getInstance().getReference();
        Picasso.get().load(event.getEventImageUrl()).into(binding.imageEvent);
        binding.name.setText(event.getName());
        binding.description.setText(event.getDescription());
        binding.seats.setText("Available seats: " + (event.getSeats()-event.getUsersId().size()));
        binding.btnParty.setOnClickListener(getParty);
    }

    View.OnClickListener getParty = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mDataBase.child(Const.KEY_EVENTS).child(event.getId()).child("usersId").child(event.getUsersId().size()+"").setValue(user.getId());
        }
    };
}