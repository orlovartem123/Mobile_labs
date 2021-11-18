package com.example.lab1v2.fragments.adapter;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.example.lab1v2.MainActivity;
import com.example.lab1v2.R;
import com.example.lab1v2.model.Task;

public class TaskAdapter extends BaseAdapter {

    private final MainActivity activity;

    public TaskAdapter(MainActivity activity) {
        this.activity = activity;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public int getCount() {
        return activity.getRepository().findAll() == null ? 0 : activity.getRepository().findAll().size();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public Task getItem(int i) {
        return activity.getRepository().findAll().get(i);
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
                    .inflate(R.layout.item, viewGroup, false);
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

        ImageButton buttonEdit = result.findViewById(R.id.imageButtonUpdate);
        buttonEdit.setOnClickListener(button -> activity.showEditFragment(item));
        ImageButton buttonDelete = result.findViewById(R.id.imageButtonDelete);
        buttonDelete.setOnClickListener(button -> activity.showDeleteFragment(item.getId()));

        return result;
    }
}
