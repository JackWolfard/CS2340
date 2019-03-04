package edu.gatech.cs2340.spacetrader.view;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.view.View;

import edu.gatech.cs2340.spacetrader.viewmodel.PlanetViewModel;
import edu.gatech.cs2340.spacetrader.R;

public class PlanetActivity extends AppCompatActivity {

    private PlanetViewModel viewModel;

    private PlanetViewModel configVM;

    private TextView planetName;
    private TextView resourceLevelNum;
    private TextView techLevelNum;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.planet);

        planetName = findViewById(R.id.planetName);
        resourceLevelNum = findViewById(R.id.resourceLevelNum);
        techLevelNum = findViewById(R.id.techLevelNum);

        planetName.setText("Deshaan");
        resourceLevelNum.setText("Temporary");
        techLevelNum.setText("Temporary");

        viewModel = ViewModelProviders.of(this).get(PlanetViewModel.class);

    }

    public void onMarketPressed(View view) {
        Log.d("Edit", "Create Player Pressed");

    }
}
