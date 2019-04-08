package edu.gatech.cs2340.spacetrader.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import edu.gatech.cs2340.spacetrader.R;
public class LoadRecyclerViewAdapter extends RecyclerView.Adapter<LoadRecyclerViewAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<String> players;
    private ArrayList<String> credits;
    private ArrayList<String> planets;
    private ArrayList<String> lastSaveds;

    public LoadRecyclerViewAdapter(Context mContext, ArrayList<String> players,
                                   ArrayList<String> credits, ArrayList<String> planets,
                                   ArrayList<String> lastSaveds) {
        this.mContext = mContext;
        this.players = players;
        this.credits = credits;
        this.planets = planets;
        this.lastSaveds = lastSaveds;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_loadlistitem,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int i) {

    }

    @Override
    public int getItemCount() {
        return planetNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView player;
        TextView credits;
        TextView planet;
        TextView lastSaved;
        RelativeLayout loadLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            player = itemView.findViewById(R.id.player);
            credits = itemView.findViewById(R.id.credits);
            planet = itemView.findViewById(R.id.planet);
            lastSaved = itemView.findViewById(R.id.lastSaved);
            loadLayout = itemView.findViewById(R.id.loadLayout);
        }
    }

}
