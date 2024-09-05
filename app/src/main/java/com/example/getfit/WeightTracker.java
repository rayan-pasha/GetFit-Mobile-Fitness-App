package com.example.getfit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class WeightTracker extends AppCompatActivity {

    private ArrayList<WeightModal> weightModalArrayList;
    private DBHandler dbHandler;
    private WeightRVAdapter weightRVAdapter;
    private RecyclerView weightsRV;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_tracker);


        // links add new weigh-in btn to add weight screen
        Button idBtnNewWeight = findViewById(R.id.idBtnNewWeight);
        idBtnNewWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WeightTracker.this, addWeight.class));
            }
        });

        Button idButtonBack = findViewById(R.id.idBtnWeightBack);
        idButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WeightTracker.this, MainActivity.class));
            }
        });


        // initializing our all variables.
        weightModalArrayList = new ArrayList<>();
        dbHandler = new DBHandler(WeightTracker.this);

        load();
    }

    void load() {


        // getting our weight array list from db handler class.
        weightModalArrayList.clear();
        weightModalArrayList = dbHandler.readWeight();

        // passing array list to our adapter class.
        weightRVAdapter = new WeightRVAdapter(weightModalArrayList, WeightTracker.this, this);
        weightsRV = findViewById(R.id.weightsList);

        // setting layout manager for our recycler view.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(WeightTracker.this, RecyclerView.VERTICAL, false);
        weightsRV.setLayoutManager(linearLayoutManager);

        // setting our adapter to recycler view.
        weightsRV.setAdapter(weightRVAdapter);

    }

    public void delete(String weight, String date) {

        dbHandler.deleteWeightByDateAndWeight(weight, date);
        load();
    }
}