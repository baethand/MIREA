package com.example.tamtamrudenko.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;

import com.example.tamtamrudenko.R;
import com.example.tamtamrudenko.databinding.FragmentProfileBinding;
import com.example.tamtamrudenko.models.User;

public class ProfileFragment extends Fragment {

    private User user;

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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.name.setText(user.getName());
        binding.age.setText(user.getAge().toString());
        binding.description.setText(user.getDescription());

        if (user.isCreator()){
            binding.eventCreator.setVisibility(View.VISIBLE);
        }
        else {
            binding.eventCreator.setVisibility(View.GONE);
        }
    }
}