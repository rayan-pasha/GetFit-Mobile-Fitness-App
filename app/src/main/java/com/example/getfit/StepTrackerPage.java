package com.example.getfit;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class StepTrackerPage extends AppCompatActivity implements OnMapReadyCallback {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private MapView mapView;
    private GoogleMap googleMap;
    private EditText stepsGoalEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_tracker_page);

        // Initialize the mapView
        mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        // Initialize the stepsGoalEditText
        stepsGoalEditText = findViewById(R.id.stepsGoalEditText);

        // Check for location permissions
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted, request the permission
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);
        } else {
            // Permission has already been granted
            startLocationUpdates();
        }

        // Add Set Goal Button
        Button setGoalButton = findViewById(R.id.setGoalButton);
        setGoalButton.setOnClickListener(v -> setGoal());

        // Add Track Steps Button
        Button trackStepsButton = findViewById(R.id.trackStepsButton);
        trackStepsButton.setOnClickListener(v -> {
            // Retrieve the steps goal from the EditText
            String stepsGoalText = stepsGoalEditText.getText().toString();

            if (!stepsGoalText.isEmpty()) {
                int stepsGoal = Integer.parseInt(stepsGoalText);
                // TODO: Implement the logic for tracking steps and use the 'stepsGoal' variable
                Toast.makeText(StepTrackerPage.this, "Track Steps clicked. Goal: " + stepsGoal, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(StepTrackerPage.this, "Please enter a valid steps goal", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setGoal() {
        // Set the goal steps based on the entered value in the EditText
        String stepsGoalText = stepsGoalEditText.getText().toString();

        if (!stepsGoalText.isEmpty()) {
            int stepsGoal = Integer.parseInt(stepsGoalText);
            Toast.makeText(StepTrackerPage.this, "Goal set to: " + stepsGoal, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(StepTrackerPage.this, "Please enter a valid steps goal", Toast.LENGTH_SHORT).show();
        }
    }

    private void startLocationUpdates() {
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, start location updates
                startLocationUpdates();
            } else {
                // Permission denied, show a message or handle it accordingly
                Toast.makeText(this, "Location permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap map) {
        googleMap = map;

        // Add a marker at Ontario Tech University
        LatLng ontarioTechLocation = new LatLng(43.9455, -78.8968);
        googleMap.addMarker(new MarkerOptions().position(ontarioTechLocation).title("Ontario Tech University"));

        // Set the camera position to Ontario Tech University with increased zoom level
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ontarioTechLocation, 16.0f));

        // Enable user interaction with the map
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.getUiSettings().setMapToolbarEnabled(true);

        // Set a listener to handle camera movements
        googleMap.setOnCameraMoveListener(() -> {
            LatLng center = googleMap.getCameraPosition().target;
            // You can use the 'center' LatLng to get the current map center and perform any actions you need.
        });

        // Set a listener to handle map clicks
        googleMap.setOnMapClickListener(latLng -> {
            // You can use 'latLng' to get the coordinates of the clicked position and perform any actions you need.
            Toast.makeText(StepTrackerPage.this, "Map Clicked: " + latLng.toString(), Toast.LENGTH_SHORT).show();
        });

        // Enable map scrolling and dragging
        googleMap.getUiSettings().setScrollGesturesEnabled(true);
        googleMap.getUiSettings().setZoomGesturesEnabled(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
}
