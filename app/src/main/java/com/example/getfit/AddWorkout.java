package com.example.getfit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class AddWorkout extends AppCompatActivity {

    // UI components
    private Toolbar toolbar;
    private Workout editWorkout;
    private EditText workoutTitle, workoutDescription, wSets, wReps;
    private Calendar c;
    private ChipGroup chipGroup;
    private ImageView workoutImage;
    private Chip colorGreen, colorBlue, colorSand;
    private Button capImg, uplImg;

    private String selectColor;
    private String todaysDate;
    private String currentTime;

    private final int SELECT_IMAGE = 101;
    private final int CAPTURE_IMAGE = 102;
    private final int REQUEST_PERMISSION = 100;

    private void requestPermissions() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_PERMISSION);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // permissions granted.
            } else {
                // permissions denied. Handle accordingly.
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        // Set up the toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("New Workout");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Connect UI elements to their respective views
        workoutTitle = findViewById(R.id.workoutTitle);
        workoutDescription = findViewById(R.id.workoutWeights);
        wSets = findViewById(R.id.sets);
        wReps = findViewById(R.id.reps);
        chipGroup = findViewById(R.id.chipGroup);
        colorGreen = findViewById(R.id.colorYellow);
        colorBlue = findViewById(R.id.colorOrange);
        colorSand = findViewById(R.id.colorSkyBlue);
        capImg = findViewById(R.id.captureImg);
        uplImg = findViewById(R.id.uploadImg);
        workoutImage =findViewById(R.id.noteImage);

        // Set up the capture image button
        capImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(AddWorkout.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions();  // request permissions if not granted
                    return;
                }

                Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takePicture, CAPTURE_IMAGE);
            }
        });

        // Set up the upload image button
        uplImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pickPhoto = new Intent(Intent.ACTION_GET_CONTENT);
                pickPhoto.setType("image/*");
                startActivityForResult(pickPhoto, SELECT_IMAGE);
            }
        });

        // Set up a text change listener for the workout title
        workoutTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Dynamically update the toolbar title based on the workout title
                if (s.length() != 0) {
                    getSupportActionBar().setTitle(s);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // This space is intentionally left blank.
            }

            @Override
            public void afterTextChanged(Editable s) {
                // This space is intentionally left blank.
            }
        });

        // Retrieve current date and time
        c = Calendar.getInstance();
        todaysDate = c.get(Calendar.YEAR) + "/" + (c.get(Calendar.MONTH) + 1) + "/" + c.get(Calendar.DAY_OF_MONTH);
        currentTime = pad(c.get(Calendar.HOUR)) + ":" + pad(c.get(Calendar.MINUTE));
        Log.d("calendar", "Date and Time: " + todaysDate + " and " + currentTime);

        // Set up the color selection mechanism for the workout
        chipGroup.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(ChipGroup group, int checkedId) {
                int color = getColorForChipId(checkedId);
                if (color != 0) {
                    workoutTitle.setBackgroundResource(color);
                    workoutDescription.setBackgroundResource(color);
                    wSets.setBackgroundResource(color);
                    wReps.setBackgroundResource(color);
                }
            }
        });

        // This will check and set the default color after all initialization
        if (colorGreen.isChecked()) {
            selectColor = "green";
            workoutTitle.setBackgroundResource(R.color.green);
            workoutDescription.setBackgroundResource(R.color.green);
            wSets.setBackgroundResource(R.color.green);
            wReps.setBackgroundResource(R.color.green);
        }

        if(getIntent().hasExtra("editNote")){
            editWorkout =(Workout) getIntent().getSerializableExtra("editNote");
            setEditMode();
        } else{

        }

    }
    private void setEditMode(){

        isEditMode=true;
        // Populate UI fields with existing workout data for editing.
        workoutTitle.setText(editWorkout.getTitle());
        workoutDescription.setText(editWorkout.getContent());
        wSets.setText(editWorkout.getSets());
        wReps.setText(editWorkout.getReps());

        // Load and display the image if it exists
        if(editWorkout.getImageByteArray()!=null){
            Bitmap imageBitmap= BitmapFactory.decodeByteArray(editWorkout.getImageByteArray(), 0, editWorkout.getImageByteArray().length);
            workoutImage.setImageBitmap(imageBitmap);
        }


        // Enable editing for title and description fields.
        workoutTitle.setEnabled(true);
        workoutDescription.setEnabled(true);
        wSets.setEnabled(true);
        wReps.setEnabled(true);

        // Set the toolbar title to indicate edit mode.
        getSupportActionBar().setTitle("Edit Workout");
        selectColor= editWorkout.getColor();
        if (selectColor.equalsIgnoreCase("blue")) {
            workoutTitle.setBackgroundResource(R.color.blue);
            workoutDescription.setBackgroundResource(R.color.blue);
            wSets.setBackgroundResource(R.color.blue);
            wReps.setBackgroundResource(R.color.blue);
        } else if (selectColor.equalsIgnoreCase("green")) {
            workoutTitle.setBackgroundResource(R.color.green);
            workoutDescription.setBackgroundResource(R.color.green);
            wSets.setBackgroundResource(R.color.green);
            wReps.setBackgroundResource(R.color.green);
        } else if (selectColor.equalsIgnoreCase("sand")) {
            workoutTitle.setBackgroundResource(R.color.sand);
            workoutDescription.setBackgroundResource(R.color.sand);
            wSets.setBackgroundResource(R.color.sand);
            wReps.setBackgroundResource(R.color.sand);
        }
    }

    // Utility method to get the color resource based on selected chip
    private int getColorForChipId(int chipId) {
        if (chipId == colorGreen.getId()) {
            selectColor = "green";
            return R.color.green;
        } else if (chipId == colorBlue.getId()) {
            selectColor = "blue";
            return R.color.blue;
        } else if (chipId == colorSand.getId()) {
            selectColor = "sand";
            return R.color.sand;
        }
        return 0;
    }

    // Utility method to pad single digit numbers with a leading zero
    private String pad(int i) {
        if (i < 10) {
            return "0" + i;
        }
        return String.valueOf(i);
    }

    // Inflate menu options for this activity
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save_menu, menu);
        return true;
    }

    private boolean isEditMode = false;

    // Handle actions when a menu item is selected
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.delete) {
            if(isEditMode){
                deleteExistingWorkout();
            }else {
                Toast.makeText(this, "Workout Was Not Saved.", Toast.LENGTH_SHORT).show();
                goToMain();
            }
        } else if (item.getItemId() == R.id.save) {
            if(isEditMode){
                updateExistingWorkout();
            }else{
                saveNewWorkout();
            }

        } else if (item.getItemId() == android.R.id.home) {
            // Handle back button press
            onBackPressed(); // This will perform the default back action
        }
        return super.onOptionsItemSelected(item);
    }

    private void updateExistingWorkout(){
        if(editWorkout !=null){
            // Update the existing workout's details
            editWorkout.setTitle(workoutTitle.getText().toString());
            editWorkout.setContent(workoutDescription.getText().toString());
            editWorkout.setSets(wSets.getText().toString());
            editWorkout.setReps(wReps.getText().toString());
            editWorkout.setColor(selectColor);

            // Update the existing workout in the database
            WorkoutDatabase db = new WorkoutDatabase(this);
            db.updateWorkout(editWorkout);

            // Indicate that you've left edit mode
            isEditMode = false;

            // Notify the user that the workout has been updated
            Toast.makeText(this, "Workout Has Been Updated.", Toast.LENGTH_SHORT).show();

            // Return to the main activity
            goToMain();
        }
    }

    private void deleteExistingWorkout() {
        if (editWorkout != null) {
            // Delete the existing Workout from the database
            WorkoutDatabase db = new WorkoutDatabase(this);
            db.deleteWorkout(editWorkout);

            // Indicate that you've left edit mode
            isEditMode = false;

            // Notify the user that the Workout has been deleted
            Toast.makeText(this, "Workout Has Been Deleted.", Toast.LENGTH_SHORT).show();

            // Return to the main activity
            goToMain();
        }
    }

    private void saveNewWorkout() {
        byte[] imageByteArray = null; // Declare imageByteArray before the if statement

        // Create a new Workout and save it to the database
        if (workoutImage.getDrawable() != null && workoutImage.getDrawable() instanceof BitmapDrawable) {
            Bitmap imageBitmap = ((BitmapDrawable) workoutImage.getDrawable()).getBitmap();

            ByteArrayOutputStream stream = new ByteArrayOutputStream();

            if (imageBitmap != null) {
                Log.d("WorkoutLog", "ImageBitmap size before compression: " + imageBitmap.getByteCount());
                imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                Log.d("WorkoutLog", "ImageBitmap size after compression: " + stream.size());

                // Convert the bitmap to a byte array
                imageByteArray = stream.toByteArray(); // Assign a value to imageByteArray
            } else {
                Log.e("WorkoutLog", "ImageBitmap is null");
            }
        }

        // Create a new Workout with or without the imageByteArray
        Workout workout = new Workout(workoutTitle.getText().toString(), workoutDescription.getText().toString(),
                todaysDate, currentTime,wSets.getText().toString(),wReps.getText().toString(),
                selectColor, imageByteArray);
        WorkoutDatabase db = new WorkoutDatabase(this);
        db.addWorkout(workout);

        // Notify the user that the new Workout has been saved
        Toast.makeText(this, "Workout Has Been Saved.", Toast.LENGTH_SHORT).show();

        // Return to the main activity
        goToMain();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Check if the result was successful
        if (resultCode == RESULT_OK) {
            // Handle result based on request code
            if (requestCode == CAPTURE_IMAGE) {
                if (data != null && data.getExtras() != null) {
                    // Get the captured image as a bitmap from the Intent data
                    Bitmap imageBitmap = (Bitmap) data.getExtras().get("data");

                    // Set the captured image to the ImageView
                    if (imageBitmap != null) {
                        workoutImage.setImageBitmap(imageBitmap); // Set the image to the ImageView
                    } else {
                        Log.e("WorkoutLog", "Captured image bitmap is null");
                    }
                } else {
                    Log.e("WorkoutLog", "Captured image data is null");
                }
            }
            else if (requestCode == SELECT_IMAGE) {
                // Get the URI of the selected image from the Intent data
                Uri selectedImage = data.getData();

                try {
                    // Decode the URI into a bitmap
                    Bitmap imageBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);

                    // Set the selected image to the ImageView
                    workoutImage.setImageBitmap(imageBitmap); // Set the image to the ImageView
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    // Utility method to navigate to the main activity
    private void goToMain() {
        Intent i = new Intent(this, MainWorkouts.class);
        startActivity(i);
    }

    // Handle the back button press event
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
