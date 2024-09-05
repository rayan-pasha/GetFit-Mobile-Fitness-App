package com.example.getfit;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


// DatabaseHelper.java
public class FoodDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "food_database";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_FOOD = "food";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_CALORIES = "calories";
    private static final String COLUMN_PROTEIN = "protein";
    private static final String COLUMN_FATS = "fats";
    private static final String COLUMN_CARBS = "carbs";

    private static final String CREATE_TABLE_FOOD = "CREATE TABLE " + TABLE_FOOD + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_NAME + " TEXT,"
            + COLUMN_CALORIES + " INTEGER,"
            + COLUMN_PROTEIN + " INTEGER,"
            + COLUMN_FATS + " INTEGER,"
            + COLUMN_CARBS + " INTEGER)";

    public FoodDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_FOOD);

        insertFoodItem(db, new FoodItem("--", 0, 0, 0, 0));
        insertFoodItem(db, new FoodItem("Banana", 105, 1, 1, 27));
        insertFoodItem(db, new FoodItem("Chicken Breast", 105, 1, 1, 0));
        insertFoodItem(db, new FoodItem("Ground Beef", 337, 33, 24, 0));
        insertFoodItem(db, new FoodItem("Beef Sirloin", 202, 35, 7, 0));
        insertFoodItem(db, new FoodItem("Lamb Breast", 431, 24, 27, 0));
        insertFoodItem(db, new FoodItem("Venison", 155, 33, 3, 0));
        insertFoodItem(db, new FoodItem("Pheasant", 200, 37, 5, 0));
        insertFoodItem(db, new FoodItem("Cod", 113, 26, 1, 0));
        insertFoodItem(db, new FoodItem("Monk Fish", 100, 24, 3, 0));
        insertFoodItem(db, new FoodItem("Lobster", 115, 26, 2, 0));
        insertFoodItem(db, new FoodItem("Eggs", 150, 14, 10, 0));
        insertFoodItem(db, new FoodItem("Quinoa", 191, 8, 3, 31));
        insertFoodItem(db, new FoodItem("White Rice", 259, 7, 0, 57));
        insertFoodItem(db, new FoodItem("Carrots", 30, 0, 0, 5));
        insertFoodItem(db, new FoodItem("Butter", 67, 0, 14, 0));
        insertFoodItem(db, new FoodItem("Peanuts", 128, 6, 11, 1));
        insertFoodItem(db, new FoodItem("Avocado", 141, 1, 14, 1));
        insertFoodItem(db, new FoodItem("Feta Cheese", 76, 5, 6, 0));
        insertFoodItem(db, new FoodItem("Olive Oil", 108, 0, 12, 0));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    private void insertFoodItem(SQLiteDatabase db, FoodItem foodItem) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, foodItem.getName());
        values.put(COLUMN_CALORIES, foodItem.getCalories());
        values.put(COLUMN_PROTEIN, foodItem.getProtein());
        values.put(COLUMN_FATS, foodItem.getFats());
        values.put(COLUMN_CARBS, foodItem.getCarbs());

        db.insert(TABLE_FOOD, null, values);
    }

}
