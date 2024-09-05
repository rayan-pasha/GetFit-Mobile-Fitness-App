package com.example.getfit;

public class WeightModal {

    // variables for our weight, date, and id
    private String weight;
    private String date;
    private int id;

    // creating getter and setter methods
    public String getWeight() { return weight; }
    public void setWeight(String weight)
    {
        this.weight = weight;
    }
    public String getDate()
    {
        return date;
    }
    public void setDate(String date)
    {
        this.date = date;
    }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    // constructor
    public WeightModal(String weight, String date)
    {
        this.weight = weight;
        this.date = date;
    }
}
