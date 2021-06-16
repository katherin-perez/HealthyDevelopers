package com.example.healthydevelopers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healthydevelopers.model.Progress;

import java.util.List;

public class UserHabitsAdapter extends BaseAdapter {

    List<Progress> progress;
    Context context;

    public UserHabitsAdapter(List<Progress> progress, Context context) {
        this.progress = progress;
        this.context = context;
    }

    @Override
    public int getCount() {
        return progress.size();
    }

    @Override
    public Object getItem(int position) {
        return progress.get(position);
    }

    @Override
    public long getItemId(int position) {
        return progress.get(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.full_habit_row, null);

        TextView tvNameCompletedHabit = view.findViewById(R.id.tvNameCompletedHabit),
                tvCategoryCompletedHabit = view.findViewById(R.id.tvCategoryCompletedHabit);
        ImageView imgCompletedHabit = view.findViewById(R.id.imgCompletedHabit);

        tvNameCompletedHabit.setText((String) UserHabitsList.habitList.get(progress.get(position).getHabitId() - 1).getTitle());
        tvCategoryCompletedHabit.setText((String) UserHabitsList.habitList.get(progress.get(position).getHabitId() - 1).getCategory());
        imgCompletedHabit.setImageDrawable(context.getDrawable(R.drawable.check2));

        return view;
    }
}