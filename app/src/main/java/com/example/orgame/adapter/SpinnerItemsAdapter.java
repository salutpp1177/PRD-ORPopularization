package com.example.orgame.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.orgame.R;

import java.util.List;

public class SpinnerItemsAdapter extends ArrayAdapter<String> {

    public SpinnerItemsAdapter(@NonNull Context context, int resource, @NonNull String[] objects) {
        super(context, resource, objects);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.spinner_items, parent, false);
        TextView textView = view.findViewById(R.id.spinner_items_textview);
        String stringItem = getItem(position);

        if (stringItem != null) {
            textView.setText(stringItem);
        }

        return view;
    }
}
