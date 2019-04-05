package edu.gatech.cs2340.spacetrader.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import edu.gatech.cs2340.spacetrader.R;
import edu.gatech.cs2340.spacetrader.model.Pirate;
import edu.gatech.cs2340.spacetrader.model.SolarSystem;
import edu.gatech.cs2340.spacetrader.model.Trader;
import edu.gatech.cs2340.spacetrader.viewmodel.TravelViewModel;

public class TravelActivity extends AppCompatActivity {

    private TravelViewModel viewModel;

    private ArrayList<String> planetNames;
    private ArrayList<String> fuelCosts;
    private ArrayList<SolarSystem> solarSystems;

    private Random randomEvent = new Random();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.travel);

        viewModel = ViewModelProviders.of(this).get(TravelViewModel.class);

        TextView fuelRemaining = findViewById(R.id.fuelText);

        ArrayList<ArrayList<String>> info = viewModel.initTravel();

        fuelRemaining.setText(info.get(2).get(0));

        planetNames = info.get(0);
        fuelCosts = info.get(1);

        solarSystems = viewModel.getSolarSystems();

        initRecyclerView();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView2);
        RecyclerViewAdapter2 adapter = new RecyclerViewAdapter2(this, planetNames, fuelCosts, solarSystems, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void onBackPressed(View view) {
        Log.d("Edit", "Back pressed");
        Intent intent = new Intent(TravelActivity.this, PlanetActivity.class);
        TravelActivity.this.startActivity(intent);
    }

    public void travel(SolarSystem s) {
        viewModel.travel(s);

        int eventNum = randomEvent.nextInt(4);
        // pirates only so far. I don't know how to implement the traders yet
        // when traders are implemented they will be based on a '1', and pirates will be a '0'
        // '2' and '3' are no events- 50% chance of traveling with no random events taking place
        if (eventNum == 0 || eventNum == 1) {
            Intent intent = new Intent(TravelActivity.this, PirateActivity.class);
            TravelActivity.this.startActivity(intent);
        } /*else if (eventNum == 1) {
            Intent intent = new Intent(TravelActivity.this, TraderActivity.class);
            TravelActivity.this.startActivity(intent);
        }*/else {
            Intent intent = new Intent(TravelActivity.this, PlanetActivity.class);
            TravelActivity.this.startActivity(intent);
        }
    }

//    public boolean randomEvent() {
//        int eventNum = randomEvent.nextInt(4);
//        if (eventNum == 0) {
//            int pirateNum = randomEvent.nextInt(10);
//            currentPirate = model.getGame().getUniverse().getPirateList().get(pirateNum);
//        } else if (eventNum == 1) {
//            int traderNum = randomEvent.nextInt(8);
//            currentTrader = model.getGame().getUniverse().getTraderList().get(traderNum);
//        }
//    }
}
