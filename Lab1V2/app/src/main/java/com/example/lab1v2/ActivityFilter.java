package com.example.lab1v2;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lab1v2.fragments.adapter.TaskViewAdapter;
import com.example.lab1v2.model.Task;

import java.util.Arrays;

public class ActivityFilter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        ListView listView = findViewById(R.id.filterList);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setItemsCanFocus(false);
        Task[] tasks = (Task[]) getIntent().getSerializableExtra("list");
        listView.setAdapter(new TaskViewAdapter(Arrays.asList(tasks)));

        Button back = findViewById(R.id.buttonBack);
        back.setOnClickListener(view -> finish());
    }
}