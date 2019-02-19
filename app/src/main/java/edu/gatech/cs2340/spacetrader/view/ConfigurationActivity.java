package edu.gatech.cs2340.spacetrader.view;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import edu.gatech.cs2340.spacetrader.entity.GameDifficulty;
import edu.gatech.cs2340.spacetrader.viewmodel.ConfigurationViewModel;
import edu.gatech.cs2340.spacetrader.R;

public class ConfigurationActivity extends AppCompatActivity {

    private ConfigurationViewModel viewModel;

    private ConfigurationViewModel configVM;

    private EditText pilotField;
    private EditText traderField;
    private EditText engineerField;
    private EditText fighterField;
    private Spinner difficultySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.config);

        pilotField = findViewById(R.id.pilot_point_entry);
        traderField = findViewById(R.id.engineer_point_entry);
        engineerField = findViewById(R.id.engineer_point_entry);

        viewModel = ViewModelProviders.of(this).get(ConfigurationViewModel.class);
    }

    public void onAddPressed(View view) {
        Log.d("Edit", "Create Player Pressed");
        GameDifficulty difficulty =
        initGame();
    }


}
