package com.example.getfit;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<com.example.getfit.Workout> workouts; // List containing the workout data

    private OnDeleteListener deleteListener;
    private OnEditLister editListener;


    Adapter(Context context, List<Workout> workouts, OnDeleteListener deleteListener, OnEditLister editListener) {
        this.inflater = LayoutInflater.from(context);
        this.workouts = workouts;
        this.deleteListener = deleteListener;
        this.editListener=editListener;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // Inflate the custom list view layout and return a new ViewHolder
        View view = inflater.inflate(R.layout.custom_list_view, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        // Extract data for the workout at the current position
        String title = workouts.get(position).getTitle();
        String date = workouts.get(position).getDate();
        String description = workouts.get(position).getContent();
        String time = workouts.get(position).getTime();
        String sets = workouts.get(position).getSets();
        String reps = workouts.get(position).getReps();
        String colorOfWorkout = workouts.get(position).getColor();

        // Populate the ViewHolder views with the workout data
        holder.nTitle.setText(title);
        holder.nDesc.setText(description);
        holder.nDate.setText(date);
        holder.nTime.setText(time);
        holder.setView.setText(sets);
        holder.repView.setText(reps);

        // Set a click listener on the delete button to trigger the delete callback
        holder.dWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Workout workoutToDelete = workouts.get(position);
                deleteListener.onWorkoutDelete(workoutToDelete);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Workout workoutToEdit = workouts.get(position);
                editListener.onWorkoutEdit(workoutToEdit);
            }
        });

        // Set the background color of the workout based on its color attribute
        if (colorOfWorkout.equalsIgnoreCase("blue")) {
            holder.nColor.setBackgroundResource(R.color.blue);
        } else if (colorOfWorkout.equalsIgnoreCase("green")) {
            holder.nColor.setBackgroundResource(R.color.green);
        } else if (colorOfWorkout.equalsIgnoreCase("sand")) {
            holder.nColor.setBackgroundResource(R.color.sand);
            Log.d("selection", "color will be: " + colorOfWorkout);
        }

        // Log for debugging
        Log.d("Color", "Color -> " + colorOfWorkout);
    }

    @Override
    public int getItemCount() {
        // Return the total number of workout
        return workouts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // UI elements within each workout item
        TextView nTitle, nDesc, nDate, nTime, setView, repView;
        ImageButton dWorkout;
        ConstraintLayout nColor;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // Bind the views from the layout to the ViewHolder's member variables
            nTitle = itemView.findViewById(R.id.nTitle);
            nDesc = itemView.findViewById(R.id.nDesc);
            nDate = itemView.findViewById(R.id.nDate);
            nTime = itemView.findViewById(R.id.nTime);
            setView = itemView.findViewById(R.id.setView);
            repView = itemView.findViewById(R.id.repView);
            nColor = itemView.findViewById(R.id.boxToFill);
            dWorkout = itemView.findViewById(R.id.deleteWorkout);
        }
    }

    public void updateWorkouts(List<Workout> newWorkouts) {
        workouts = newWorkouts;
        notifyDataSetChanged(); // Notify the RecyclerView about data changes to refresh the view
    }

    public interface OnDeleteListener {
        void onWorkoutDelete(Workout workout);
    }
    public interface OnEditLister {
        void onWorkoutEdit(Workout workout);
    }
}

