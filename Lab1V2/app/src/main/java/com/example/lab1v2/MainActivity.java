package com.example.lab1v2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.lab1v2.fragments.FragmentAdd;
import com.example.lab1v2.fragments.FragmentEdit;
import com.example.lab1v2.fragments.FragmentList;
import com.example.lab1v2.fragments.FragmentRemove;
import com.example.lab1v2.fragments.FragmentSettings;
import com.example.lab1v2.model.Task;
import com.example.lab1v2.storages.TaskLocalRepository;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TaskLocalRepository repository;

    public TaskLocalRepository getRepository(){
        return repository;
    }

    public void changeRepository() {
        SharedPreferences data = PreferenceManager.getDefaultSharedPreferences(this);
        boolean useDb = data.getBoolean("rep", false);
        this.repository = new TaskLocalRepository(this);
//        if (useDb) {
//            this.repository = new TaskDbRepository(this);
//        } else {
//            this.repository = new TaskLocalRepository(this);
//        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        changeRepository();
        setContentView(R.layout.activity_main);

        Button buttonFilter = findViewById(R.id.buttonFilter);
        Button buttonAdd = findViewById(R.id.buttonAdd);
        FloatingActionButton buttonSettings = findViewById(R.id.loadButton);

        showListFragment();

        buttonFilter.setOnClickListener(view -> filter());
        buttonAdd.setOnClickListener(view -> setFragment(new FragmentAdd()));
        buttonSettings.setOnClickListener(view -> setFragment(new FragmentSettings()));
    }

    public void showEditFragment(Task task) {
        setFragment(new FragmentEdit(task));
    }

    public void showDeleteFragment(Long id) {
        setFragment(new FragmentRemove(id));
    }

    public void showListFragment() {
        setFragment(new FragmentList());
    }

    public void setFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.commit();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void filter() {
        EditText filterTextPlain = findViewById(R.id.filterText);
        String filterText = filterTextPlain.getText().toString();
        Task[] filtered = repository.findAll().stream()
                .filter(s -> s.getName().toLowerCase(Locale.ROOT).startsWith(filterText.toLowerCase(Locale.ROOT)))
                .toArray(Task[]::new);

        Intent myIntent = new Intent(this, ActivityFilter.class);
        myIntent.putExtra("list", filtered);
        startActivity(myIntent);
    }
}