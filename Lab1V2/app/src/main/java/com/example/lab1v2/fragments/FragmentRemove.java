package com.example.lab1v2.fragments;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.lab1v2.MainActivity;
import com.example.lab1v2.R;

public class FragmentRemove extends Fragment {

    private final Long id;

    public FragmentRemove(Long id) {
        this.id = id;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_remove, container, false);

        Button buttonRemove = view.findViewById(R.id.buttonLoad);
        buttonRemove.setOnClickListener(action -> removeTask());

        Button buttonBack = view.findViewById(R.id.buttonSettingsBack);
        buttonBack.setOnClickListener(action -> back());

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void removeTask() {
        MainActivity activity = (MainActivity) getActivity();
        activity.getRepository().deleteById(id);
        activity.showListFragment();
    }

    private void back() {
        MainActivity activity = (MainActivity) getActivity();
        activity.showListFragment();
    }
}