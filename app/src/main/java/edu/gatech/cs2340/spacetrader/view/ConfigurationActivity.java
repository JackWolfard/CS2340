package edu.gatech.cs2340.spacetrader.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.EditText;

import edu.gatech.cs2340.spacetrader.viewmodel.ConfigurationViewModel;
import edu.gatech.cs2340.spacetrader.R;

public class ConfigurationActivity extends AppCompatActivity {

    private ConfigurationViewModel configVM;

    private EditText nameEntry;
    private EditText pilotPt;
    private EditText engineerPt;
    private EditText traderPt;
    private EditText fighterPt;
    private Spinner difficultySelect;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.config);

        nameEntry = findViewById(R.id.enter_name);
        pilotPt = findViewById(R.id.pilot_point_entry);
        engineerPt = findViewById(R.id.engineer_point_entry);
        traderPt = findViewById(R.id.trader_point_entry);
        fighterPt = findViewById(R.id.fighter_point_entry);
        difficultySelect = findViewById(R.id.difficulty_spinner);
    }
}
