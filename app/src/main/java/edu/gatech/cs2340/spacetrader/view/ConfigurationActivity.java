package edu.gatech.cs2340.spacetrader.view;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
<<<<<<< HEAD
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
=======
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.EditText;
>>>>>>> master

import edu.gatech.cs2340.spacetrader.entity.GameDifficulty;
import edu.gatech.cs2340.spacetrader.viewmodel.ConfigurationViewModel;
import edu.gatech.cs2340.spacetrader.R;

public class ConfigurationActivity extends AppCompatActivity {

    private ConfigurationViewModel viewModel;

    private ConfigurationViewModel configVM;

<<<<<<< HEAD
    private EditText nameField;
    private EditText pilotField;
    private EditText engineerField;
    private EditText traderField;
    private EditText fighterField;
    private Spinner difficultySpinner;
=======
    private EditText nameEntry;
    private EditText pilotPt;
    private EditText engineerPt;
    private EditText traderPt;
    private EditText fighterPt;
    private Spinner difficultySelect;
>>>>>>> master

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.config);

<<<<<<< HEAD
        nameField = findViewById(R.id.enter_name);
        pilotField = findViewById(R.id.pilot_point_entry);
        engineerField = findViewById(R.id.engineer_point_entry);
        traderField = findViewById(R.id.trader_point_entry);
        fighterField = findViewById(R.id.fighter_point_entry);
        difficultySpinner = findViewById(R.id.difficulty_spinner);

        ArrayAdapter<GameDifficulty> difficultyAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, GameDifficulty.values());
        difficultyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        difficultySpinner.setAdapter(difficultyAdapter);

        viewModel = ViewModelProviders.of(this).get(ConfigurationViewModel.class);
=======
        nameEntry = findViewById(R.id.enter_name);
        pilotPt = findViewById(R.id.pilot_point_entry);
        engineerPt = findViewById(R.id.engineer_point_entry);
        traderPt = findViewById(R.id.trader_point_entry);
        fighterPt = findViewById(R.id.fighter_point_entry);
        difficultySelect = findViewById(R.id.difficulty_spinner);
>>>>>>> master
    }

    public void onAddPressed(View view) {
        Log.d("Edit", "Create Player Pressed");
        GameDifficulty difficulty = (GameDifficulty) difficultySpinner.getSelectedItem();
        viewModel.initGame(difficulty, nameField.getText().toString(), convertToInt(pilotField),
                convertToInt(engineerField), convertToInt(traderField), convertToInt(fighterField));
    }

    private int convertToInt(android.widget.EditText widget) {
        return Integer.parseInt(widget.getText().toString());
    }


}
