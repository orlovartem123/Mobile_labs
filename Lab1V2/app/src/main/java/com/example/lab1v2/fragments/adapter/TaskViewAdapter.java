package com.example.lab1v2.fragments.adapter;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.example.lab1v2.R;
import com.example.lab1v2.model.Task;

import java.util.List;

public class TaskViewAdapter extends BaseAdapter {

    private final List<Task> tasks;

    public TaskViewAdapter(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public int getCount() {
        return tasks.size();
    }

    @Override
    public Task getItem(int i) {
        return tasks.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final View result;

        if (view == null) {
            result = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.item_view, viewGroup, false);
        } else {
            result = view;
        }

        Task item = getItem(i);

        TextView textName = result.findViewById(R.id.textName);
        textName.setText(item.getName());

        TextView textHours = result.findViewById(R.id.textHours);
        String text = item.getHours() + "h";
        textHours.setText(text);

        CheckBox checkBox = result.findViewById(R.id.checkBoxDone);
        checkBox.setChecked(item.isDone());

        return result;
    }
}