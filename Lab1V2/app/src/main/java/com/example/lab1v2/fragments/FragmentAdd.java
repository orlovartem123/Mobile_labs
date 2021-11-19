package com.example.lab1v2.fragments;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.lab1v2.MainActivity;
import com.example.lab1v2.R;
import com.example.lab1v2.model.Monkey;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class FragmentAdd extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add, container, false);

        EditText textName = view.findViewById(R.id.nameText);
        EditText textHours = view.findViewById(R.id.hoursText);

        Button buttonAdd = view.findViewById(R.id.buttonAddTask);
        buttonAdd.setOnClickListener(action -> addTask(textName, textHours));

        Button buttonBack = view.findViewById(R.id.buttonAdditionBack);
        buttonBack.setOnClickListener(action -> back());

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void addTask(EditText textName, EditText textHours) {
        Monkey monkey = new Monkey();
        monkey.setName(textName.getText().toString());
        monkey.setWeight(Integer.parseInt(textHours.getText().toString()));

        MainActivity activity = (MainActivity) getActivity();
        activity.getRepository().save(monkey);
        activity.showListFragment();
    }

    private void back() {
        MainActivity activity = (MainActivity) getActivity();
        activity.showListFragment();
    }
}