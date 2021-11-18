package com.example.lab1v2.storages;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;

import androidx.annotation.RequiresApi;

import com.example.lab1v2.model.Task;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskLocalRepository implements TaskRepository {

    private final Context context;
    private List<Task> tasks;

    public TaskLocalRepository(Context context) {
        this.context = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void init() {
        tasks = new ArrayList<>();
        SharedPreferences data = PreferenceManager.getDefaultSharedPreferences(context);
        String stringData = data.getString("data", "");

        Type targetClassType = new TypeToken<ArrayList<Task>>() {}.getType();
        //tasks = new Gson().fromJson(stringData, targetClassType);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public List<Task> findAll() {
        if (tasks == null) {
            init();
        }
        return tasks;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public Optional<Task> findById(Long id) {
        if (tasks == null) {
            init();
        }
        return tasks.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void save(Task task) {
        Optional<Task> savedTask = findById(task.getId());

        if (savedTask.isPresent()) {
            Task taskToUpdate = savedTask.get();
            taskToUpdate.setName(task.getName());
            taskToUpdate.setHours(task.getHours());
            taskToUpdate.setDone(task.isDone());
        } else {
            task.setId(getId());
            tasks.add(task);
        }

        saveArray(tasks);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private Long getId() {
        long maxId = tasks.stream()
                .mapToLong(Task::getId)
                .max().orElse(0);

        return maxId + 1;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void deleteById(Long id) {
        Optional<Task> savedTask = findById(id);
        if (savedTask.isPresent()) {
            tasks.remove(savedTask.get());
            saveArray(tasks);
        }
    }

    @Override
    public void setBackUp(List<Task> tasks) {
        this.tasks = tasks;
        saveArray(tasks);
    }

    private void saveArray(List<Task> tasks) {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("data", new Gson().toJson(tasks));
        editor.apply();
    }
}
