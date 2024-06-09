package com.example.tamtamrudenko.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tamtamrudenko.R;
import com.example.tamtamrudenko.adapters.EventAdapter;
import com.example.tamtamrudenko.databinding.FragmentEventsBinding;
import com.example.tamtamrudenko.databinding.FragmentProfileBinding;
import com.example.tamtamrudenko.models.Const;
import com.example.tamtamrudenko.models.Event;
import com.example.tamtamrudenko.models.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class EventsFragment extends Fragment {
    private FragmentEventsBinding binding;
    private EventAdapter eventAdapter;
    private User user;
    private List<Event> eventList;
    DatabaseReference mDataBase;
    StorageReference mStorageReference;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            user = args.getParcelable("user");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEventsBinding.inflate(inflater,
                container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mDataBase = FirebaseDatabase
                .getInstance(Const.DB_URL)
                .getReference(Const.KEY_EVENTS);
        mStorageReference = FirebaseStorage.getInstance().getReference();

        DividerItemDecoration itemDecorator = new DividerItemDecoration(
                getContext(), DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(ContextCompat.getDrawable(
                getContext(), R.drawable.empty_tall_divider));
        binding.recyclerView.addItemDecoration(itemDecorator);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(
                getContext()));
        eventList = new ArrayList<>();
        getDataFromFirebase();
    }

    private void getDataFromFirebase() {
        mDataBase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                eventList = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Event item = snapshot.getValue(Event.class);
                    eventList.add(item);
                }
                updateUI(eventList);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Обработка ошибки
                Log.w("Firebase", "Failed to read value.",
                        error.toException());
            }
        });
    }

    private void updateUI(List<Event> list) {
        eventAdapter = new EventAdapter(getContext(), eventList, event -> {
            EventDetailFragment fragment = new EventDetailFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable("user", user);
            bundle.putParcelable("event", event);
            fragment.setArguments(bundle);

            getParentFragmentManager().beginTransaction()
                    .replace(R.id.fragment_main, fragment)
                    .addToBackStack(null)
                    .commit();
        });
        binding.recyclerView.setAdapter(eventAdapter);
    }
}