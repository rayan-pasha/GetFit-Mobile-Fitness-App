package com.example.getfit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    private static final String DB_NAME = "WeightDB";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "myweights";
    private static final String ID_COL = "id";
    private static final String WEIGHT_COL = "weight";
    private static final String DATE_COL = "date";
    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + WEIGHT_COL + " TEXT,"
                + DATE_COL + " TEXT)";

        db.execSQL(query);
    }

    // this method is use to add new weight to our sqlite database.
    public void addNewWeight(String weight, String date) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(WEIGHT_COL, weight);
        values.put(DATE_COL, date);


        db.insert(TABLE_NAME, null, values);

        db.close();
    }

    public void deleteWeightByDateAndWeight(String weight, String date) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Define the WHERE clause to identify the specific entry by weight and date
        String whereClause = WEIGHT_COL + " = ? AND " + DATE_COL + " = ?";
        String[] whereArgs = {weight, date};

        db.delete(TABLE_NAME, whereClause, whereArgs);

        db.close();
    }

    // method for reading all the weight.
    public ArrayList<WeightModal> readWeight()
    {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursorWeight = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        ArrayList<WeightModal> weightModalArrayList = new ArrayList<>();

        if (cursorWeight.moveToFirst()) {
            do {
                weightModalArrayList.add(new WeightModal(
                        cursorWeight.getString(1),
                        cursorWeight.getString(2)));
            } while (cursorWeight.moveToNext());
        }
        cursorWeight.close();
        return weightModalArrayList;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
