package com.example.lab1v2.storages;

import com.example.lab1v2.model.Monkey;

import java.util.List;
import java.util.Optional;

public interface MonkeyRepository {

    List<Monkey> findAll();

    Optional<Monkey> findById(Long id);

    void save (Monkey monkey);

    void deleteById(Long id);

    void setBackUp(List<Monkey> monkeys);
}
