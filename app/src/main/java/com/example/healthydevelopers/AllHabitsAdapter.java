package com.example.healthydevelopers;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.healthydevelopers.model.Habit;

import java.util.ArrayList;

public class AllHabitsAdapter extends BaseAdapter{
    ArrayList<Object> habitsList;
    Context context;

    private static final int HABITO = 0, ENCABEZADO = 1;
    LayoutInflater inflater;

    public AllHabitsAdapter(ArrayList<Object> habitsList, Context context) {
        this.habitsList = habitsList;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getItemViewType(int position) {
        if (habitsList.get(position) instanceof Habit) {
            return HABITO;
        }
        return ENCABEZADO;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getCount() {
        return habitsList.size();
    }

    @Override
    public Object getItem(int position) {
        return habitsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    //devolver el id del hábito

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        switch (getItemViewType(position)) {
            case ENCABEZADO:
                view = inflater.inflate(R.layout.habit_header, null);
                ImageView img = view.findViewById(R.id.imgHabitHeader);
                TextView tv = view.findViewById(R.id.tvHabitHeader);
                String elemento = (String) getItem(position);
                switch (elemento) {
                    case "Alimentacion":
                        view.setBackgroundColor(Color.parseColor("#E92BA7DD"));
                        img.setImageDrawable(context.getDrawable(R.drawable.feeding));
                        break;
                    case "Trabajo":
                        view.setBackgroundColor(Color.parseColor("#D52BA7DD"));
                        img.setImageDrawable(context.getDrawable(R.drawable.programmer));
                        break;
                    case "Fisicos":
                        view.setBackgroundColor(Color.parseColor("#2BA7DD"));
                        img.setImageDrawable(context.getDrawable(R.drawable.phisycal_health));
                        break;
                    case "Social/Familiar":
                        view.setBackgroundColor(Color.parseColor("#BC2BA7DD"));
                        img.setImageDrawable(context.getDrawable(R.drawable.friends));
                        break;
                    case "Espiritual/Mental":
                        view.setBackgroundColor(Color.parseColor("#9C2BA7DD"));
                        img.setImageDrawable(context.getDrawable(R.drawable.god));
                        break;
                }
                tv.setText(elemento);
                break;
            case HABITO:
                view = inflater.inflate(R.layout.habit_row, null);
                TextView tvHabito = view.findViewById(R.id.tvHabitRow);
                tvHabito.setText(((Habit) getItem(position)).getTitle());
                break;
        }
        return view;
    }
}
