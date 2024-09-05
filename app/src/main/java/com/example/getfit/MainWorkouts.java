package com.example.getfit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;


public class MainWorkouts extends AppCompatActivity implements Adapter.OnDeleteListener, Adapter.OnEditLister {

    // UI components
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private SearchView searchView;

    private Adapter adapter;
    private List<Workout> workouts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workout_main);

        // Binding UI components
        toolbar = findViewById(R.id.toolbar);
        searchView = findViewById(R.id.searchBar);

        // Setting up the toolbar
        setSupportActionBar(toolbar);

        // Fetching Workout from the database
        WorkoutDatabase db = new WorkoutDatabase(this);
        workouts = db.getWorkouts();

        // Configuring the RecyclerView
        recyclerView = findViewById(R.id.listOfWorkout);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(this, workouts, this, this::onWorkoutEdit); // 'this' is passed for the delete Workout callback
        recyclerView.setAdapter(adapter);

        // Setting a listener on the search bar to filter Workout
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Handle when the user presses the search button on the keyboard (if needed)
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Filter the Workout list based on the user's search query
                filter(newText);
                return true;
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options for the activity
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here.
        if (item.getItemId() == R.id.add) {
            // Navigate to the AddWorkout activity when 'add' option is selected
            Intent i = new Intent(this, AddWorkout.class);
            startActivity(i);
        }
        if (item.getItemId() == R.id.backwLog) {
            // Navigate to the AddWorkout activity when 'add' option is selected
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onWorkoutDelete(Workout workout) {
        // Callback method to handle Workout deletion

        WorkoutDatabase db = new WorkoutDatabase(this); // Database instance
        db.deleteWorkout(workout); // Delete the Workout from the database

        workouts = db.getWorkouts(); // Refresh the list after deletion
        adapter.updateWorkouts(workouts); // Notify the adapter to refresh the RecyclerView
    }

    // Method to filter the Workout list based on a search query
    private void filter(String query) {
        List<Workout> filteredWorkouts = new ArrayList<>();

        // Loop through all Workout and check if the title contains the search query
        for (Workout workout : workouts) {
            if (workout.getTitle().toLowerCase().contains(query.toLowerCase())) {
                filteredWorkouts.add(workout);
            }
        }

        // Update the RecyclerView with the filtered Workout
        adapter.updateWorkouts(filteredWorkouts);
    }

    @Override
    public void onWorkoutEdit(Workout workout){
        Intent editIntent = new Intent(this, AddWorkout.class);
        editIntent.putExtra("editNote", workout);
        startActivity(editIntent);
    }
}

