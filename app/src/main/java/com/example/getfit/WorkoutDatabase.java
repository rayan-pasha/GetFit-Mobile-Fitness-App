//TODO: FIGURE OUT HOW IN THE FLYING FUCK I AM TO STORE IMAGES IN AN SQLITE DATABASE, PEOPLE SAYING TO STORE USING BYTE ARRAY (HARD)
package com.example.getfit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * NoteDatabase is responsible for providing database functionalities for managing notes.
 * This includes operations like creating, reading, updating, and deleting notes.
 */
public class WorkoutDatabase extends SQLiteOpenHelper {

    // Constants defining database attributes such as its version, name and table name
    private static final int DATABASE_VERSION = 4;
    private static final String DATABASE_NAME = "workoutLog2";
    private static final String DATABASE_TABLE = "Logs2";

    // Column names for the notes table
    private static final String KEY_ID = "id";
    private static final String KEY_CONTENT = "content";
    private static final String KEY_TITLE = "title";
    private static final String KEY_DATE = "date";
    private static final String KEY_TIME = "time";
    private static final String KEY_SETS = "sets";
    private static final String KEY_REPS = "reps";
    private static final String KEY_COLOR = "color";
    private static final String KEY_IMAGE = "image";


    /**
     * Constructor to initialize the database helper with context.
     * @param context Context of the application
     */
    WorkoutDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Called when the database is being created for the first time.
     * Defines the structure of the table(s).
     * @param db The database instance
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + DATABASE_TABLE + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_TITLE + " TEXT,"
                + KEY_CONTENT + " TEXT,"
                + KEY_DATE + " TEXT,"
                + KEY_TIME + " TEXT,"
                + KEY_SETS + " TEXT,"
                + KEY_REPS + " TEXT,"
                + KEY_COLOR + " TEXT,"
                + KEY_IMAGE +" BLOB" +")";
        db.execSQL(query);
    }

    /**
     * Called when the database needs to be upgraded, for example, due to schema changes.
     * @param db The database instance
     * @param oldVersion The old database version
     * @param newVersion The new database version
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion >= newVersion) return;
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        onCreate(db);
    }

    /**
     * Adds a new note to the database.
     * @param workout The note to be added
     * @return ID of the note after insertion
     */
    public long addWorkout(Workout workout) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put(KEY_TITLE, workout.getTitle());
        c.put(KEY_CONTENT, workout.getContent());
        c.put(KEY_TIME, workout.getTime());
        c.put(KEY_DATE, workout.getDate());
        c.put(KEY_SETS, workout.getSets());
        c.put(KEY_REPS, workout.getReps());
        c.put(KEY_COLOR, workout.getColor());
        c.put(KEY_IMAGE, workout.getImageByteArray());

        long ID = db.insert(DATABASE_TABLE, null, c);
        Log.d("Inserted", "ID ->" + ID);
        Log.d("Color", "Color ->  " + workout.getColor());
        return ID;
    }

    /**
     * Fetches a note based on its ID.
     * @param id ID of the note to retrieve
     * @return The corresponding note
     */
    public Workout getWorkout(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(DATABASE_TABLE, new String[]{KEY_ID, KEY_TITLE, KEY_CONTENT, KEY_DATE, KEY_TIME,
                KEY_SETS, KEY_REPS, KEY_COLOR, KEY_IMAGE}, KEY_ID + "=?", new String[]{String.valueOf(id)},
                null, null, null);
        if (cursor != null) cursor.moveToFirst();
        return new Workout(cursor.getLong(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4),
                cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getBlob(8));
    }

    /**
     * Fetches all the notes present in the database.
     * @return List of all notes
     */
    public List<Workout> getWorkouts() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Workout> allWorkouts = new ArrayList<>();
        String query = "SELECT * FROM " + DATABASE_TABLE;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                Workout workout = new Workout();
                workout.setID(cursor.getLong(0));
                workout.setTitle(cursor.getString(1));
                workout.setContent(cursor.getString(2));
                workout.setDate(cursor.getString(3));
                workout.setTime(cursor.getString(4));
                workout.setSets(cursor.getString(5));
                workout.setReps(cursor.getString(6));
                workout.setColor(cursor.getString(7));
                workout.setImageByteArray(cursor.getBlob(8));
                allWorkouts.add(workout);
            } while (cursor.moveToNext());
        }
        return allWorkouts;
    }

    /**
     * Deletes a specific note from the database.
     * @param workout The note to be deleted
     */
    public void deleteWorkout(Workout workout) {
        SQLiteDatabase db = this.getWritableDatabase();
        int deletedRows = db.delete(DATABASE_TABLE, KEY_ID + "=?", new String[]{String.valueOf(workout.getID())});
        Log.d("NoteDatabase", "Attempting to delete note with ID: " + workout.getID() + ". Rows deleted: " + deletedRows);
        db.close();
    }

    public int updateWorkout(Workout workout){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, workout.getTitle());
        values.put(KEY_CONTENT, workout.getContent());
        values.put(KEY_TIME, workout.getTime());
        values.put(KEY_DATE, workout.getDate());
        values.put(KEY_SETS, workout.getSets());
        values.put(KEY_REPS, workout.getReps());
        values.put(KEY_COLOR, workout.getColor());
        values.put(KEY_IMAGE, workout.getImageByteArray());

        // Updating row
        return db.update(DATABASE_TABLE, values, KEY_ID + " = ?", new String[]{String.valueOf(workout.getID())});
    }
}
