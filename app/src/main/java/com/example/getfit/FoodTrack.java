package com.example.getfit;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class FoodTrack extends AppCompatActivity {

    private Spinner spinnerFood, spinnerProtein, spinnerFats;
    private Spinner spinnerFood1, spinnerProtein1, spinnerFats1;
    private Spinner spinnerFood2, spinnerProtein2, spinnerFats2;
    private TextView totalCaloriesTextView, proteinTextView, fatsTextView, carbsTextView;
    private ProgressBar caloriesProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.foodtrack_activity);


        caloriesProgressBar = findViewById(R.id.caloriesProgressBar);

        spinnerFood = findViewById(R.id.spinnerFood);
        spinnerProtein = findViewById(R.id.spinnerProtein);
        spinnerFats = findViewById(R.id.spinnerFats);
        spinnerFood1 = findViewById(R.id.spinnerFood1);
        spinnerProtein1 = findViewById(R.id.spinnerProtein1);
        spinnerFats1 = findViewById(R.id.spinnerFats1);
        spinnerFood2 = findViewById(R.id.spinnerFood2);
        spinnerProtein2 = findViewById(R.id.spinnerProtein2);
        spinnerFats2 = findViewById(R.id.spinnerFats2);

        totalCaloriesTextView = findViewById(R.id.totalCaloriesTextView);
        proteinTextView = findViewById(R.id.proteinTextView);
        fatsTextView = findViewById(R.id.fatsTextView);
        carbsTextView = findViewById(R.id.carbsTextView);

        FoodItem noItem = new FoodItem("--", 0, 0, 0, 0);
        FoodItem banana = new FoodItem("Banana", 105, 1, 1, 27);
        FoodItem chicken = new FoodItem("Chicken Breast", 105, 1, 1, 0);
        FoodItem mango = new FoodItem("Mango", 35, 0, 0, 27);
        FoodItem groundBeef = new FoodItem("Ground Beef", 337, 33, 24, 0);
        FoodItem beefSirloin = new FoodItem("Beef Sirloin", 202, 35, 7, 0);
        FoodItem lamb = new FoodItem("Lamb Breast", 431, 24, 27, 0);
        FoodItem venison = new FoodItem("Venison", 155, 33, 3, 0);
        FoodItem pheasant = new FoodItem("Pheasant", 200, 37, 5, 0);
        FoodItem cod = new FoodItem("Cod", 113, 26, 1, 0);
        FoodItem monkFish = new FoodItem("Monk Fish", 100, 24, 3, 0);
        FoodItem lobster = new FoodItem("Lobster", 115, 26, 2, 0);
        FoodItem eggs = new FoodItem("Eggs", 150, 14, 10, 0);
        FoodItem quinoa = new FoodItem("Quinoa", 191, 8, 3, 31);
        FoodItem whiteRice = new FoodItem("White Rice", 259, 7, 0, 57);
        FoodItem carrot = new FoodItem("Carrots", 30, 0, 0, 5);
        FoodItem butter = new FoodItem("Butter", 67, 0, 14, 0);
        FoodItem peanuts = new FoodItem("Peanuts", 128, 6, 11, 1);
        FoodItem avocado = new FoodItem("Avocado", 141, 1, 14, 1);
        FoodItem fetaCheese = new FoodItem("Feta Cheese", 76, 5, 6, 0);
        FoodItem oliveOil = new FoodItem("Olive Oil", 108, 0, 12, 0);



        ArrayAdapter<FoodItem> foodAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        foodAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        foodAdapter.add(noItem);
        foodAdapter.add(banana);
        foodAdapter.add(chicken);
        foodAdapter.add(mango);
        foodAdapter.add(groundBeef);
        foodAdapter.add(beefSirloin);
        foodAdapter.add(lamb);
        foodAdapter.add(venison);
        foodAdapter.add(pheasant);
        foodAdapter.add(cod);
        foodAdapter.add(monkFish);
        foodAdapter.add(lobster);
        foodAdapter.add(eggs);
        foodAdapter.add(quinoa);
        foodAdapter.add(whiteRice);
        foodAdapter.add(carrot);
        foodAdapter.add(butter);
        foodAdapter.add(peanuts);
        foodAdapter.add(avocado);
        foodAdapter.add(fetaCheese);
        foodAdapter.add(oliveOil);

        spinnerFood.setAdapter(foodAdapter);

        ArrayAdapter<FoodItem> foodAdapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        foodAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        foodAdapter1.add(noItem);
        foodAdapter1.add(banana);
        foodAdapter1.add(chicken);
        foodAdapter1.add(mango);
        foodAdapter1.add(groundBeef);
        foodAdapter1.add(beefSirloin);
        foodAdapter1.add(lamb);
        foodAdapter1.add(venison);
        foodAdapter1.add(pheasant);
        foodAdapter1.add(cod);
        foodAdapter1.add(monkFish);
        foodAdapter1.add(lobster);
        foodAdapter1.add(eggs);
        foodAdapter1.add(quinoa);
        foodAdapter1.add(whiteRice);
        foodAdapter1.add(carrot);
        foodAdapter1.add(butter);
        foodAdapter1.add(peanuts);
        foodAdapter1.add(avocado);
        foodAdapter1.add(fetaCheese);
        foodAdapter1.add(oliveOil);
        spinnerFood1.setAdapter(foodAdapter1);

        ArrayAdapter<FoodItem> foodAdapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        foodAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        foodAdapter2.add(noItem);
        foodAdapter2.add(banana);
        foodAdapter2.add(chicken);
        foodAdapter2.add(mango);
        foodAdapter2.add(groundBeef);
        foodAdapter2.add(beefSirloin);
        foodAdapter2.add(lamb);
        foodAdapter2.add(venison);
        foodAdapter2.add(pheasant);
        foodAdapter2.add(cod);
        foodAdapter2.add(monkFish);
        foodAdapter2.add(lobster);
        foodAdapter2.add(eggs);
        foodAdapter2.add(quinoa);
        foodAdapter2.add(whiteRice);
        foodAdapter2.add(carrot);
        foodAdapter2.add(butter);
        foodAdapter2.add(peanuts);
        foodAdapter2.add(avocado);
        foodAdapter2.add(fetaCheese);
        foodAdapter2.add(oliveOil);
        spinnerFood2.setAdapter(foodAdapter2);

        ArrayAdapter<FoodItem> proteinAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        proteinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        proteinAdapter.add(noItem);
        proteinAdapter.add(banana);
        proteinAdapter.add(chicken);
        proteinAdapter.add(mango);
        proteinAdapter.add(groundBeef);
        proteinAdapter.add(beefSirloin);
        proteinAdapter.add(lamb);
        proteinAdapter.add(venison);
        proteinAdapter.add(pheasant);
        proteinAdapter.add(cod);
        proteinAdapter.add(monkFish);
        proteinAdapter.add(lobster);
        proteinAdapter.add(eggs);
        proteinAdapter.add(quinoa);
        proteinAdapter.add(whiteRice);
        proteinAdapter.add(carrot);
        proteinAdapter.add(butter);
        proteinAdapter.add(peanuts);
        proteinAdapter.add(avocado);
        proteinAdapter.add(fetaCheese);
        proteinAdapter.add(oliveOil);

        spinnerProtein.setAdapter(proteinAdapter);

        ArrayAdapter<FoodItem> proteinAdapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        proteinAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        proteinAdapter1.add(noItem);
        proteinAdapter1.add(banana);
        proteinAdapter1.add(chicken);
        proteinAdapter1.add(mango);
        proteinAdapter1.add(groundBeef);
        proteinAdapter1.add(beefSirloin);
        proteinAdapter1.add(lamb);
        proteinAdapter1.add(venison);
        proteinAdapter1.add(pheasant);
        proteinAdapter1.add(cod);
        proteinAdapter1.add(monkFish);
        proteinAdapter1.add(lobster);
        proteinAdapter1.add(eggs);
        proteinAdapter1.add(quinoa);
        proteinAdapter1.add(whiteRice);
        proteinAdapter1.add(carrot);
        proteinAdapter1.add(butter);
        proteinAdapter1.add(peanuts);
        proteinAdapter1.add(avocado);
        proteinAdapter1.add(fetaCheese);
        proteinAdapter1.add(oliveOil);
        spinnerProtein1.setAdapter(proteinAdapter1);

        ArrayAdapter<FoodItem> proteinAdapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        proteinAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        proteinAdapter2.add(noItem);
        proteinAdapter2.add(banana);
        proteinAdapter2.add(chicken);
        proteinAdapter2.add(mango);
        proteinAdapter2.add(groundBeef);
        proteinAdapter2.add(beefSirloin);
        proteinAdapter2.add(lamb);
        proteinAdapter2.add(venison);
        proteinAdapter2.add(pheasant);
        proteinAdapter2.add(cod);
        proteinAdapter2.add(monkFish);
        proteinAdapter2.add(lobster);
        proteinAdapter2.add(eggs);
        proteinAdapter2.add(quinoa);
        proteinAdapter2.add(whiteRice);
        proteinAdapter2.add(carrot);
        proteinAdapter2.add(butter);
        proteinAdapter2.add(peanuts);
        proteinAdapter2.add(avocado);
        proteinAdapter2.add(fetaCheese);
        proteinAdapter2.add(oliveOil);
        spinnerProtein2.setAdapter(proteinAdapter2);

        ArrayAdapter<FoodItem> fatsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        fatsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fatsAdapter.add(noItem);
        fatsAdapter.add(banana);
        fatsAdapter.add(chicken);
        fatsAdapter.add(mango);
        fatsAdapter.add(groundBeef);
        fatsAdapter.add(beefSirloin);
        fatsAdapter.add(lamb);
        fatsAdapter.add(venison);
        fatsAdapter.add(pheasant);
        fatsAdapter.add(cod);
        fatsAdapter.add(monkFish);
        fatsAdapter.add(lobster);
        fatsAdapter.add(eggs);
        fatsAdapter.add(quinoa);
        fatsAdapter.add(whiteRice);
        fatsAdapter.add(carrot);
        fatsAdapter.add(butter);
        fatsAdapter.add(peanuts);
        fatsAdapter.add(avocado);
        fatsAdapter.add(fetaCheese);
        fatsAdapter.add(oliveOil);

        spinnerFats.setAdapter(fatsAdapter);

        ArrayAdapter<FoodItem> fatsAdapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        fatsAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fatsAdapter1.add(noItem);
        fatsAdapter1.add(banana);
        fatsAdapter1.add(chicken);
        fatsAdapter1.add(mango);
        fatsAdapter1.add(groundBeef);
        fatsAdapter1.add(beefSirloin);
        fatsAdapter1.add(lamb);
        fatsAdapter1.add(venison);
        fatsAdapter1.add(pheasant);
        fatsAdapter1.add(cod);
        fatsAdapter1.add(monkFish);
        fatsAdapter1.add(lobster);
        fatsAdapter1.add(eggs);
        fatsAdapter1.add(quinoa);
        fatsAdapter1.add(whiteRice);
        fatsAdapter1.add(carrot);
        fatsAdapter1.add(butter);
        fatsAdapter1.add(peanuts);
        fatsAdapter1.add(avocado);
        fatsAdapter1.add(fetaCheese);
        fatsAdapter1.add(oliveOil);
        spinnerFats1.setAdapter(fatsAdapter1);

        ArrayAdapter<FoodItem> fatsAdapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        fatsAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fatsAdapter2.add(noItem);
        fatsAdapter2.add(banana);
        fatsAdapter2.add(chicken);
        fatsAdapter2.add(mango);
        fatsAdapter2.add(groundBeef);
        fatsAdapter2.add(beefSirloin);
        fatsAdapter2.add(lamb);
        fatsAdapter2.add(venison);
        fatsAdapter2.add(pheasant);
        fatsAdapter2.add(cod);
        fatsAdapter2.add(monkFish);
        fatsAdapter2.add(lobster);
        fatsAdapter2.add(eggs);
        fatsAdapter2.add(quinoa);
        fatsAdapter2.add(whiteRice);
        fatsAdapter2.add(carrot);
        fatsAdapter2.add(butter);
        fatsAdapter2.add(peanuts);
        fatsAdapter2.add(avocado);
        fatsAdapter2.add(fetaCheese);
        fatsAdapter2.add(oliveOil);
        spinnerFats2.setAdapter(fatsAdapter2);

        // Set up listeners for spinners
        spinnerFood.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                updateNutritionalInfo();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

        spinnerFood1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                updateNutritionalInfo();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

        spinnerFood2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                updateNutritionalInfo();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });


        spinnerProtein.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                updateNutritionalInfo();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

        spinnerProtein1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                updateNutritionalInfo();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here
            }
        });

        spinnerProtein2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                updateNutritionalInfo();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

        spinnerFats.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Handle fats item selection
                updateNutritionalInfo();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

        spinnerFats1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Handle fats item selection
                updateNutritionalInfo();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

        spinnerFats2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Handle fats item selection
                updateNutritionalInfo();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
    }





    private void updateNutritionalInfo() {
        // Get selected food items from spinners
        FoodItem selectedFoodFood = (FoodItem) spinnerFood.getSelectedItem();
        FoodItem selectedProteinFood = (FoodItem) spinnerProtein.getSelectedItem();
        FoodItem selectedFatsFood = (FoodItem) spinnerFats.getSelectedItem();
        FoodItem selectedFoodFood1 = (FoodItem) spinnerFood1.getSelectedItem();
        FoodItem selectedProteinFood1 = (FoodItem) spinnerProtein1.getSelectedItem();
        FoodItem selectedFatsFood1 = (FoodItem) spinnerFats1.getSelectedItem();
        FoodItem selectedFoodFood2 = (FoodItem) spinnerFood2.getSelectedItem();
        FoodItem selectedProteinFood2 = (FoodItem) spinnerProtein2.getSelectedItem();
        FoodItem selectedFatsFood2 = (FoodItem) spinnerFats2.getSelectedItem();

        // Calculate total values
        int totalCalories = selectedFoodFood.getCalories() + selectedProteinFood.getCalories() + selectedFatsFood.getCalories()
                + selectedFoodFood1.getCalories() + selectedFoodFood2.getCalories() + selectedProteinFood1.getCalories()
                + selectedProteinFood2.getCalories() + selectedFatsFood1.getCalories() + selectedFatsFood2.getCalories();

        int totalProtein = selectedFoodFood.getProtein() + selectedProteinFood.getProtein() + selectedFatsFood.getProtein()
                + selectedFoodFood1.getProtein() + selectedFoodFood2.getProtein() + selectedProteinFood1.getProtein()
                + selectedProteinFood2.getProtein() + selectedFatsFood1.getProtein() + selectedFatsFood2.getProtein();

        int totalFats = selectedFoodFood.getFats() + selectedProteinFood.getFats() + selectedFatsFood.getFats()
                + selectedFoodFood1.getFats() + selectedFoodFood2.getFats() + selectedProteinFood1.getFats()
                + selectedProteinFood2.getFats() + selectedFatsFood1.getFats() + selectedFatsFood2.getFats();

        int totalCarbs = selectedFoodFood.getCarbs() + selectedProteinFood.getCarbs() + selectedFatsFood.getCarbs()
                + selectedFoodFood1.getCarbs() + selectedFoodFood2.getCarbs() + selectedProteinFood1.getCarbs()
                + selectedProteinFood2.getCarbs() + selectedFatsFood1.getCarbs() + selectedFatsFood2.getCarbs();

        // Update TextViews with total nutritional information
        totalCaloriesTextView.setText(String.valueOf(totalCalories));
        proteinTextView.setText(String.valueOf(totalProtein));
        fatsTextView.setText(String.valueOf(totalFats));
        carbsTextView.setText(String.valueOf(totalCarbs));

        if (totalCalories <= 2000) {
            caloriesProgressBar.setProgress(totalCalories);
        } else {
            caloriesProgressBar.setProgress(2000);
        }
    }
}

