package com.example.getfit;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ViewWeights extends AppCompatActivity {

    // creating variables for our array list,
    // db-handler, adapter and recycler view.
    private ArrayList<WeightModal> weightModalArrayList;
    private DBHandler dbHandler;
    private WeightRVAdapter weightRVAdapter;
    private RecyclerView weightsRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initializing our all variables.
        weightModalArrayList = new ArrayList<>();
        dbHandler = new DBHandler(ViewWeights.this);
        load();
    }

    void load() {

        // getting our weight array list from db handler class.
        weightModalArrayList.clear();
        // getting our weight array list from db handler class.
        weightModalArrayList = dbHandler.readWeight();

        // on below line passing our array list to our adapter class.
        weightRVAdapter = new WeightRVAdapter(weightModalArrayList, ViewWeights.this, this);
        weightsRV = findViewById(R.id.weightsList);

        // setting layout manager for our recycler view.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ViewWeights.this, RecyclerView.VERTICAL, false);
        weightsRV.setLayoutManager(linearLayoutManager);

        // setting our adapter to recycler view.
        weightsRV.setAdapter(weightRVAdapter);

    }

    public void delete(String weight, String date) {

        dbHandler.deleteWeightByDateAndWeight(weight, date);
        load();
    }
}
