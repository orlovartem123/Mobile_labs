package com.example.lab1v2.fragments;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.fragment.app.Fragment;

import com.example.lab1v2.MainActivity;
import com.example.lab1v2.R;
import com.example.lab1v2.model.Task;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FragmentSettings extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        Button buttonLoad = view.findViewById(R.id.buttonLoad);
        buttonLoad.setOnClickListener(action -> loadDataFromJson());

        Button buttonBack = view.findViewById(R.id.buttonSettingsBack);
        buttonBack.setOnClickListener(action -> back());

        @SuppressLint("UseSwitchCompatOrMaterialCode")
        Switch switchDone = view.findViewById(R.id.switchDb);
        MainActivity activity = (MainActivity) getActivity();
        SharedPreferences data = PreferenceManager.getDefaultSharedPreferences(activity);
        boolean useDb = data.getBoolean("rep", false);
        switchDone.setChecked(useDb);
        switchDone.setOnCheckedChangeListener((CompoundButton buttonView, boolean isChecked) -> {
            SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(activity);
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean("rep", isChecked);
            editor.apply();
            activity.changeRepository();
        });

        return view;
    }

    private void loadDataFromJson() {
        InputStream inputStream = getResources().openRawResource(R.raw.data);
        try (Reader fileReader = new InputStreamReader(inputStream)) {
            Gson gson = new Gson();
            Type targetClassType = new TypeToken<ArrayList<Task>>(){}.getType();
            List<Task> tasks = gson.fromJson(fileReader, targetClassType);

            MainActivity activity = (MainActivity) getActivity();
            activity.getRepository().setBackUp(tasks);
            activity.showListFragment();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void back() {
        MainActivity activity = (MainActivity) getActivity();
        activity.showListFragment();
    }
}