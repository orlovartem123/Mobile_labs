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
import com.example.lab1v2.model.Monkey;

import java.util.List;

public class MonkeyViewAdapter extends BaseAdapter {

    private final List<Monkey> monkeys;

    public MonkeyViewAdapter(List<Monkey> monkeys) {
        this.monkeys = monkeys;
    }

    @Override
    public int getCount() {
        return monkeys.size();
    }

    @Override
    public Monkey getItem(int i) {
        return monkeys.get(i);
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

        Monkey item = getItem(i);

        TextView textName = result.findViewById(R.id.textName);
        textName.setText(item.getName());

        TextView textHours = result.findViewById(R.id.textHours);
        String text = item.getWeight() + " kg";
        textHours.setText(text);

        CheckBox checkBox = result.findViewById(R.id.checkBoxDone);
        checkBox.setChecked(item.isWild());

        return result;
    }
}