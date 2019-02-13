package com.example.lab5_2;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddNewItemActivity extends AppCompatActivity {


    CalendarView calendarView;
    TextView yearTextView;
    TextView dayTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_item);

        yearTextView = findViewById(R.id.year_text_view);
        dayTextView = findViewById(R.id.day_text_view);

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

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save_button, menu);

        return true;
    }
}
