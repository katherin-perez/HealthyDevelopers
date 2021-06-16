package com.example.healthydevelopers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.healthydevelopers.model.User;

import java.util.List;

public class CommunityAdapter extends BaseAdapter {

    List<User> users;
    Context context;

    public CommunityAdapter(List<User> users, Context context) {
        this.users = users;
        this.context = context;
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int position) {
        return users.get(position);
    }

    @Override
    public long getItemId(int position) {
        return users.get(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.user_row, null);

        TextView tvNameCommunity = view.findViewById(R.id.tvNameCommunity),
                tvLastNameCommunity = view.findViewById(R.id.tvLastNameCommunity),
                tvMailCommunity = view.findViewById(R.id.tvMailCommunity);
        ImageView imgCommunity = view.findViewById(R.id.imgCommunity);

        User user = users.get(position);
        tvNameCommunity.setText(user.getName());
        tvLastNameCommunity.setText(user.getLast_name());
        tvMailCommunity.setText(user.getMail());
        if (user.getSex() == 'F'){
            imgCommunity.setImageDrawable(context.getDrawable(R.drawable.woman_profile));
        }else {
            imgCommunity.setImageDrawable(context.getDrawable(R.drawable.man_profile));
        }
        return view;
    }
}
