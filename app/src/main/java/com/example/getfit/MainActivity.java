package com.example.getfit;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button2 = findViewById(R.id.button2);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start the FoodTrack activity
                Intent intentfood = new Intent(MainActivity.this, FoodTrack.class);

                // Start the FoodTrack activity
                startActivity(intentfood);
            }
        });

        Button button1 = findViewById(R.id.button1);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start the FoodTrack activity
                Intent intentstep = new Intent(MainActivity.this, StepTrackerPage.class);

                // Start the FoodTrack activity
                startActivity(intentstep);
            }
        });

        Button button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start the FoodTrack activity
                Intent intentexer = new Intent(MainActivity.this, MainWorkouts.class);

                // Start the FoodTrack activity
                startActivity(intentexer);
            }
        });


        Button button4 = findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Create an Intent to start the WeightTracker activity
                Intent intentmedit = new Intent(MainActivity.this, WeightTracker.class);

                // Start the FoodTrack activity
                startActivity(intentmedit);
            }
        });

        Button button5 = findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start the FoodTrack activity
                Intent intentmedit = new Intent(MainActivity.this, meditation.class);

                // Start the FoodTrack activity
                startActivity(intentmedit);
            }
        });

    }
}
