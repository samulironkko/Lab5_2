package com.example.lab5_2;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener {

    FloatingActionButton addButton;
    ArrayList<ToDoItem> toDoItems = new ArrayList<>();
    ToDoModel model = null;
    ListView listView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.list_view);

        addButton = findViewById(R.id.add_button);
        addButton.setOnClickListener(this);

        model = new ToDoModel(this);

        toDoItems = model.readToDoItemDb();

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Remove item");
                builder.setMessage("Do you really want to remove this item?");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String desc = toDoItems.get(position).getDescription();
                        model.deleteToDoItem(desc);
                        toDoItems = model.readToDoItemDb();
                        onResume();
                    }
                });
                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
                return true;
            }
        });

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, AddNewItemActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();

        toDoItems = model.readToDoItemDb();
        ToDoArrayAdapter adapter = new ToDoArrayAdapter(this, toDoItems);
        listView.setAdapter(adapter);
    }
}
