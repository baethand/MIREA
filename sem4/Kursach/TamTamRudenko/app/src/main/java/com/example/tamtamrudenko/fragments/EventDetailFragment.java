package com.example.tamtamrudenko.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tamtamrudenko.R;
import com.example.tamtamrudenko.adapters.EventAdapter;
import com.example.tamtamrudenko.databinding.FragmentEventDetailBinding;
import com.example.tamtamrudenko.databinding.FragmentEventsBinding;
import com.example.tamtamrudenko.models.Const;
import com.example.tamtamrudenko.models.Event;
import com.example.tamtamrudenko.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

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
        if (user.getEvents() == null){
            user.setEvents(new ArrayList<>());
        }
        if (event.getUsersId() == null) {
            event.setUsersId(new ArrayList<>());
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
        binding.btnUnparty.setOnClickListener(unParty);
        getDataFromFirebase();
    }

    View.OnClickListener unParty = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mDataBase.child(Const.KEY_EVENTS).child(event.getId()).child("usersId")
                    .child(event.getUsersId().indexOf(user.getId())+"").removeValue();

            mDataBase.child(Const.KEY_USER).child(user.getId()).child("events")
                    .child(user.getEvents().indexOf(event.getId())+"").removeValue();
        }
    };

    private void getDataFromFirebase() {

        mDataBase.child(Const.KEY_USER).child(user.getId()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                user = dataSnapshot.getValue(User.class);
                if (user.getEvents() == null){
                    user.setEvents(new ArrayList<>());
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Обработка ошибки
                Log.w("Firebase", "Failed to read value.", error.toException());
            }
        });

        mDataBase.child(Const.KEY_EVENTS).child(event.getId()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                event = dataSnapshot.getValue(Event.class);
                if (event.getUsersId() == null) {
                    event.setUsersId(new ArrayList<>());
                }
                updateUI();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Обработка ошибки
                Log.w("Firebase", "Failed to read value.", error.toException());
            }
        });
    }

    private void updateUI() {
        binding.name.setText(event.getName());
        binding.description.setText(event.getDescription());
        binding.nameOfEvent.setText(event.getName());
        Integer avSeats = event.getSeats()-event.getUsersId().size();
        if (avSeats <= 0){
            avSeats = 0;
            binding.btnParty.setVisibility(View.GONE);
            binding.btnUnparty.setVisibility(View.GONE);
            binding.unavailable.setVisibility(View.VISIBLE);
        }
        binding.seats.setText("Available seats: " + avSeats);

        binding.btnParty.setOnClickListener(getParty);

        if (event.getUsersId().contains(user.getId())){
            binding.btnParty.setVisibility(View.GONE);
            binding.btnUnparty.setVisibility(View.VISIBLE);
            binding.unavailable.setVisibility(View.GONE);
        }
        else if (avSeats != 0 && !event.getUsersId().contains(user.getId())) {
            binding.btnParty.setVisibility(View.VISIBLE);
            binding.btnUnparty.setVisibility(View.GONE);
            binding.unavailable.setVisibility(View.GONE);
        }
    }

    View.OnClickListener getParty = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            mDataBase.child(Const.KEY_USER).child(user.getId()).child("events")
                    .child(user.getEvents().size()+"").setValue(event.getId());

            mDataBase.child(Const.KEY_EVENTS).child(event.getId()).child("usersId")
                    .child(event.getUsersId().size()+"").setValue(user.getId());
        }
    };
}