package edu.gatech.cs2340.spacetrader.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.view.View;

import edu.gatech.cs2340.spacetrader.viewmodel.PlanetViewModel;
import edu.gatech.cs2340.spacetrader.R;

public class PlanetActivity extends AppCompatActivity {

    private PlanetViewModel viewModel;

    private TextView planetName;
    private TextView resourceLevelNum;
    private TextView techLevelNum;

    @Override
    /**
     * This method gets called when the instance is changed to planet
     */
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.planet);

        viewModel = ViewModelProviders.of(this).get(PlanetViewModel.class);

        planetName = findViewById(R.id.planetName);
        resourceLevelNum = findViewById(R.id.resourceLevelNum);
        techLevelNum = findViewById(R.id.techLevelNum);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initTextFields();
    }

    /**
     * Initializes the text fields that show planet name, tech level, and resource level
     */
    private void initTextFields() {
        String[] info = viewModel.initPlanetView();

        planetName.setText(info[0]);
        techLevelNum.setText(info[1]);
        resourceLevelNum.setText(info[2]);
    }

    /**
     * Method runs when the go to marketplace button is pressed by the user.
     * It changes the intent to MarketActivity
     * @param view
     */
    public void onGoPressed(View view) {
        Log.d("Edit", "Go to marketplace pressed");

        Intent intent = new Intent(this, MarketActivity.class);
        startActivity(intent);
    }

    /**
     * Method runs when the travel button is pressed by the user.
     * It changes the intent to TravelActivity
     * @param view
     */
    public void onTravelPressed(View view) {
        Intent intent = new Intent(this, TravelActivity.class);
        startActivity(intent);
    }
}
