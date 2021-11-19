package com.example.lab1v2.storages;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;

import androidx.annotation.RequiresApi;

import com.example.lab1v2.model.Monkey;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MonkeyLocalRepository implements MonkeyRepository {

    private final Context context;
    private List<Monkey> monkeys;

    public MonkeyLocalRepository(Context context) {
        this.context = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void init() {
        monkeys = new ArrayList<>();
        SharedPreferences data = PreferenceManager.getDefaultSharedPreferences(context);
        String stringData = data.getString("data", "");

        Type targetClassType = new TypeToken<ArrayList<Monkey>>() {}.getType();
        //tasks = new Gson().fromJson(stringData, targetClassType);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public List<Monkey> findAll() {
        if (monkeys == null) {
            init();
        }
        return monkeys;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public Optional<Monkey> findById(Long id) {
        if (monkeys == null) {
            init();
        }
        return monkeys.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void save(Monkey monkey) {
        Optional<Monkey> savedTask = findById(monkey.getId());

        if (savedTask.isPresent()) {
            Monkey monkeyToUpdate = savedTask.get();
            monkeyToUpdate.setName(monkey.getName());
            monkeyToUpdate.setWeight(monkey.getWeight());
            monkeyToUpdate.setWild(monkey.isWild());
        } else {
            monkey.setId(getId());
            monkeys.add(monkey);
        }

        saveArray(monkeys);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private Long getId() {
        long maxId = monkeys.stream()
                .mapToLong(Monkey::getId)
                .max().orElse(0);

        return maxId + 1;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void deleteById(Long id) {
        Optional<Monkey> savedTask = findById(id);
        if (savedTask.isPresent()) {
            monkeys.remove(savedTask.get());
            saveArray(monkeys);
        }
    }

    @Override
    public void setBackUp(List<Monkey> monkeys) {
        this.monkeys = monkeys;
        saveArray(monkeys);
    }

    private void saveArray(List<Monkey> monkeys) {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("data", new Gson().toJson(monkeys));
        editor.apply();
    }
}
