package com.example.getfit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class addWeight extends AppCompatActivity {

    private EditText weightEdt, dateEdt;
    private Button addWeightBtn, deleteBtn;
    private DBHandler dbHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_weight);

        // links add cancel button to main activity
        Button idBtnCancel = findViewById(R.id.idBtnCancel);

        idBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(addWeight.this, WeightTracker.class));
            }
        });


        // initializing all our variables.
        weightEdt = findViewById(R.id.idEdtWeight);
        dateEdt = findViewById(R.id.idEdtDate);
        addWeightBtn = findViewById(R.id.idBtnAddWeight);


        // creating a new db-handler class and passing our context to it.
        dbHandler = new DBHandler(addWeight.this);

        // below line is to add on click listener for our add weight button.
        addWeightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // below line is to get data from all edit text fields.
                String weight = weightEdt.getText().toString();
                String date = dateEdt.getText().toString();


                // validating if the text fields are empty or not.
                if (weight.isEmpty() && date.isEmpty()) {
                    Toast.makeText(addWeight.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }

                // calling a method to add new weight to DB
                dbHandler.addNewWeight(weight, date);

                // after adding the data we are displaying a toast message.
                Toast.makeText(addWeight.this, "Weight has been added.", Toast.LENGTH_SHORT).show();
                weightEdt.setText("");
                dateEdt.setText("");

                startActivity(new Intent(addWeight.this, WeightTracker.class));

            }
        });
    }
}