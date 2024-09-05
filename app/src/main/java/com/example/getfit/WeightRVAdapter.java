package com.example.getfit;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class WeightRVAdapter extends RecyclerView.Adapter<WeightRVAdapter.ViewHolder> {

    private ArrayList<WeightModal> weightModalArrayList;
    private Context context;

    Activity weightTracker;

    // constructor
    public WeightRVAdapter(ArrayList<WeightModal> weightModalArrayList, Context context, Activity weightTracker) {
        this.weightModalArrayList = weightModalArrayList;
        this.context = context;
        this.weightTracker = weightTracker;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflating our layout file for our recycler view items.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weight_rv_item, parent, false);

        TextView weight = view.findViewById(R.id.idTVWeight);
        TextView date = view.findViewById(R.id.idTVDate);
        Button delete = view.findViewById(R.id.idBtnDelete);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (weightTracker != null && weightTracker instanceof WeightTracker) {

                    ((WeightTracker) weightTracker).delete(weight.getText().toString(), date.getText().toString());

                }

            }
        });

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //setting data to our views of recycler view item.
        WeightModal modal = weightModalArrayList.get(position);
        holder.dateTV.setText(modal.getDate());
        holder.weightTV.setText(modal.getWeight());

    }

    @Override
    public int getItemCount() {
        // returning the size of our array list
        return weightModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our text views.
        private TextView dateTV, weightTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views
            dateTV = itemView.findViewById(R.id.idTVDate);
            weightTV = itemView.findViewById(R.id.idTVWeight);

        }
    }
}
