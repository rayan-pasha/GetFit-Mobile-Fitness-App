package com.example.getfit;

import java.io.Serializable;

/**
 * Model class to represent a single note.
 */
public class Workout implements Serializable {

    // Instance variables
    private long ID;
    private String title;
    private String content;
    private String date;
    private String time;
    private String sets;
    private String reps;
    private String color;
    private byte[] imageByteArray = new byte[0];

    // Default constructor
    Workout() {}


    Workout(String title, String content, String date, String time, String sets, String reps, String color, byte[] imageByteArray){
        this.title = title;
        this.content = content;
        this.date = date;
        this.time = time;
        this.sets = sets;
        this.reps = reps;
        this.color = color;
        this.imageByteArray=imageByteArray;
    }

    /**
     * Full constructor. Useful when retrieving a note from a database.
     */
    Workout(long id, String title, String content, String date, String time, String sets, String reps, String color, byte[] imageByteArray) {
        this.ID = id;
        this.title = title;
        this.content = content;
        this.date = date;
        this.time = time;
        this.sets = sets;
        this.reps = reps;
        this.color = color;
        this.imageByteArray=imageByteArray;
    }

    // Getter and Setter methods for the instance variables

    public String getSets() {
        return sets;
    }

    public void setSets(String sets) {
        this.sets = sets;
    }

    public String getReps() {
        return reps;
    }

    public void setReps(String reps) {
        this.reps = reps;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    public byte[] getImageByteArray(){return imageByteArray;}
    public void setImageByteArray(byte[] imageByteArray){this.imageByteArray=imageByteArray;}
}
