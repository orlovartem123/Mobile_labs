package com.example.lab1v2.fragments;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.lab1v2.MainActivity;
import com.example.lab1v2.R;
import com.example.lab1v2.model.Monkey;

public class FragmentEdit extends Fragment {

    private final Monkey monkey;

    public FragmentEdit(Monkey monkey) {
        this.monkey = monkey;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit, container, false);

        EditText textName = view.findViewById(R.id.nameText3);
        textName.setText(monkey.getName());
        EditText textHours = view.findViewById(R.id.hoursText3);
        textHours.setText(String.valueOf(monkey.getWeight()));

        @SuppressLint("UseSwitchCompatOrMaterialCode")
        Switch switchDone = view.findViewById(R.id.switchDone);
        switchDone.setChecked(monkey.isWild());

        Button buttonEdit = view.findViewById(R.id.buttonEdit);
        buttonEdit.setOnClickListener(action -> editTask(textName, textHours, switchDone));

        Button buttonBack = view.findViewById(R.id.buttonEditionBack);
        buttonBack.setOnClickListener(action -> back());

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void editTask(EditText textName, EditText textHours, @SuppressLint("UseSwitchCompatOrMaterialCode") Switch switchDone) {
        monkey.setName(textName.getText().toString());
        monkey.setWeight(Integer.parseInt(textHours.getText().toString()));
        monkey.setWild(switchDone.isChecked());

        MainActivity activity = (MainActivity) getActivity();
        if (activity != null) {
            activity.getRepository().save(monkey);
            activity.showListFragment();
        }
    }

    private void back() {
        MainActivity activity = (MainActivity) getActivity();
        activity.showListFragment();
    }
}