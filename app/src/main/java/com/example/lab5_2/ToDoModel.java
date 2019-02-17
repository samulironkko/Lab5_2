package com.example.lab5_2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.util.ArrayList;

public class ToDoModel {

    ToDoItemdbHelper mDbHelper = null;

    public ToDoModel(Context context) {
        this.mDbHelper = new ToDoItemdbHelper(context);
    }

    void addToDoItemToDb(ToDoItem addable) {

        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(ToDoItemContract.ToDoItem.COLUMN_NAME_TITLE, addable.name);
        values.put(ToDoItemContract.ToDoItem.COLUMN_NAME_DESCRIPTION, addable.description);
        values.put(ToDoItemContract.ToDoItem.COLUMN_NAME_DATE, String.valueOf(addable.dueDate));

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(ToDoItemContract.ToDoItem.TABLE_NAME, null, values);

    }

    public ArrayList<ToDoItem> readToDoItemDb() {

        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                ToDoItemContract.ToDoItem.COLUMN_NAME_TITLE,
                ToDoItemContract.ToDoItem.COLUMN_NAME_DESCRIPTION,
                ToDoItemContract.ToDoItem.COLUMN_NAME_DATE
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = ToDoItemContract.ToDoItem.COLUMN_NAME_TITLE + " = ?";
        String[] selectionArgs = { "toDoName" };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                ToDoItemContract.ToDoItem.COLUMN_NAME_DESCRIPTION + " DESC";

        Cursor cursor = db.query(
                ToDoItemContract.ToDoItem.TABLE_NAME,   // The table to query
                null,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );

        ArrayList<ToDoItem> toDoItems = new ArrayList<>();
        while(cursor.moveToNext()) {
            ToDoItem toDoItem = new ToDoItem();
            toDoItem.setName(cursor.getString(cursor.getColumnIndex(ToDoItemContract.ToDoItem.COLUMN_NAME_TITLE)));
            toDoItem.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(ToDoItemContract.ToDoItem.COLUMN_NAME_DESCRIPTION)));
            toDoItem.setDueDate(cursor.getString(cursor.getColumnIndexOrThrow(ToDoItemContract.ToDoItem.COLUMN_NAME_DATE)));

            toDoItems.add(toDoItem);
        }
        cursor.close();

        return toDoItems;

    }

    public Integer deleteToDoItem(String desc) {

        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        return db.delete(ToDoItemContract.ToDoItem.TABLE_NAME, ToDoItemContract.ToDoItem.COLUMN_NAME_DESCRIPTION + " = ?", new String[] {desc});
    }

}
