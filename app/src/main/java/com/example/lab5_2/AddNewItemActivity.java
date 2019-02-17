package com.example.lab5_2;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddNewItemActivity extends AppCompatActivity {


    CalendarView calendarView;
    TextView yearTextView;
    TextView dayTextView;
    EditText nameEditText;
    EditText descEditText;
    String dueDate;

    ToDoModel model = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_item);

        yearTextView = findViewById(R.id.year_text_view);
        dayTextView = findViewById(R.id.day_text_view);

        nameEditText = findViewById(R.id.name_edit_text);
        descEditText = findViewById(R.id.desc_edit_text);

        calendarView = findViewById(R.id.calendar_view);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String weekDayName;
                String monthName;

                SimpleDateFormat dayFormat = new SimpleDateFormat("E", Locale.US);
                SimpleDateFormat monthFormat = new SimpleDateFormat("MMM", Locale.US);

                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth);

                weekDayName = dayFormat.format(calendar.getTime());
                monthName = monthFormat.format(calendar.getTime());

                yearTextView.setText(String.valueOf(year));
                dayTextView.setText(weekDayName + ", " + dayOfMonth + " " + monthName);

                SimpleDateFormat dayFor = new SimpleDateFormat("d", Locale.US);
                SimpleDateFormat monthFor = new SimpleDateFormat("MM", Locale.US);

                String day = dayFor.format(calendar.getTime());
                String month2 = monthFor.format(calendar.getTime());

                dueDate = day + "." + month2 + "." + String.valueOf(year);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save_button, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        model = new ToDoModel(this);

        ToDoItem toDoItem = new ToDoItem();
        toDoItem.setName(nameEditText.getText().toString());
        toDoItem.setDescription(descEditText.getText().toString());
        toDoItem.setDueDate(dueDate);

        model.addToDoItemToDb(toDoItem);

        finish();

        return true;
    }
}
