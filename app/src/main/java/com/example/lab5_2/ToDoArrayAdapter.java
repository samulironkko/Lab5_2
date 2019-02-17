package com.example.lab5_2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ToDoArrayAdapter extends ArrayAdapter<ToDoItem> {

    static final int VIEW_TYPE_ToDo = 0;
    static final int VIEW_TYPE_COUNT = 1;

    public ToDoArrayAdapter(Context context, ArrayList<ToDoItem> toDoItems){super(context, 0, toDoItems);}

    @Override
    public int getViewTypeCount() {return VIEW_TYPE_COUNT;}


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ToDoItem toDoItem = getItem(position);

        if (convertView == null) {
            int layoutId = 0;
            layoutId = R.layout.list_row_todo;
            convertView = LayoutInflater.from(getContext()).inflate(layoutId, parent, false);
        }

        TextView titleText = convertView.findViewById(R.id.title_text_view);
        TextView dateText = convertView.findViewById(R.id.date_text_view);
        TextView descText = convertView.findViewById(R.id.desc_text_view);

        titleText.setText(toDoItem.getName());
        dateText.setText(toDoItem.getDueDate());
        descText.setText(toDoItem.getDescription());

        return convertView;
    }
}
