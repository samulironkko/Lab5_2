package com.example.lab5_2;

import android.provider.BaseColumns;

public final class ToDoItemContract {

    private ToDoItemContract(){}

    public static class ToDoItem implements BaseColumns {
        public static final String TABLE_NAME = "toDoItems";
        public static final String COLUMN_NAME_TITLE = "toDoName";
        public static final String COLUMN_NAME_DESCRIPTION = "toDoDescription";
        public static final String COLUMN_NAME_DATE = "toDoDate";
    }
}
