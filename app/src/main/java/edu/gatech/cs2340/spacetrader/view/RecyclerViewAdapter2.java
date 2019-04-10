package edu.gatech.cs2340.spacetrader.view;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import edu.gatech.cs2340.spacetrader.R;
import edu.gatech.cs2340.spacetrader.model.SolarSystem;

/**
 * RecyclerViewAdapter2 controls the recyclerView for the Travel screen
 */
public class RecyclerViewAdapter2 extends RecyclerView.Adapter<RecyclerViewAdapter2.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter2";

    private ArrayList<String> planetNames;
    private ArrayList<String> fuelCosts;
    private ArrayList<SolarSystem> solarSystems;
    private Context mContext;
    private TravelActivity travelActivity;

    /**
     * Constructor for RecyclerViewAdapter2
     * @param mContext context
     * @param planetNames array list of planet names
     * @param fuelCosts array list of fuel costs
     * @param solarSystems array list of solarSystems
     * @param travelActivity travelActivity passed in to call activity methods
     */
    public RecyclerViewAdapter2(Context mContext, ArrayList<String> planetNames,
                                ArrayList<String> fuelCosts, ArrayList<SolarSystem> solarSystems,
                                TravelActivity travelActivity) {
        this.mContext = mContext;
        this.planetNames = planetNames;
        this.fuelCosts = fuelCosts;
        this.solarSystems = solarSystems;
        this.travelActivity = travelActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_travellistitem,
                parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int i) {

        holder.planetName.setText(planetNames.get(i));
        holder.fuelCost.setText(fuelCosts.get(i));

        holder.travelLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            /**
             * This code is run when a field in the recycler view is tapped by the user
             */
            public void onClick(View v) {
                //Toast.makeText(mContext, planetNames.get(i), Toast.LENGTH_SHORT).show();
                travelActivity.travel(solarSystems.get(i));
            }
        });

    }

    @Override
    public int getItemCount() {
        return planetNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView planetName;
        TextView fuelCost;
        RelativeLayout travelLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            planetName = itemView.findViewById(R.id.planetName);
            fuelCost = itemView.findViewById(R.id.fuelCost);
            travelLayout = itemView.findViewById(R.id.travelLayout);

        }
    }

}
