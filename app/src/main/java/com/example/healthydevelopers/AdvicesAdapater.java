package com.example.healthydevelopers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AdvicesAdapater extends BaseAdapter {
    String[] advicesList;
    Context context;
    LayoutInflater inflater;

    public AdvicesAdapater(String[] advicesList, Context context) {
        this.advicesList = advicesList;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return advicesList.length;
    }

    @Override
    public Object getItem(int position) {
        return advicesList[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view = inflater.inflate(R.layout.support_simple_spinner_dropdown_item, null);
        if (position > 0) {
            view = inflater.inflate(R.layout.habit_row, null);
            TextView tvAdvice = view.findViewById(R.id.tvHabitRow);
            tvAdvice.setText(((String) getItem(position)));
        }
        return view;
    }
}
