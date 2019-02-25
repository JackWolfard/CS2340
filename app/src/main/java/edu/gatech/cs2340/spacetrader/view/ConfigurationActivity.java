package edu.gatech.cs2340.spacetrader.view;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import edu.gatech.cs2340.spacetrader.entity.GameDifficulty;
import edu.gatech.cs2340.spacetrader.viewmodel.ConfigurationViewModel;
import edu.gatech.cs2340.spacetrader.R;

public class ConfigurationActivity extends AppCompatActivity {

    private ConfigurationViewModel viewModel;

    private ConfigurationViewModel configVM;

    private EditText nameField;
    private EditText pilotField;
    private EditText engineerField;
    private EditText traderField;
    private EditText fighterField;
    private Spinner difficultySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.config);

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
    }

    public void onAddPressed(View view) {
        Log.d("Edit", "Create Player Pressed");
        GameDifficulty difficulty = (GameDifficulty) difficultySpinner.getSelectedItem();
        int totalPointsUsed = convertToInt(pilotField) +
                convertToInt(engineerField) + convertToInt(traderField) + convertToInt(fighterField);

        if (nameField.getText().toString().length() == 0) {
            Toast toast = Toast.makeText(getApplicationContext(), "Enter a valid name", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP, 0, 0);
            toast.show();
        } else if (totalPointsUsed != 16) {
            Toast toast = Toast.makeText(getApplicationContext(), "Point selection does not add up to 16", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP, 0, 0);
            toast.show();
        } else {
                viewModel.initGame(difficulty, nameField.getText().toString(), convertToInt(pilotField),
                        convertToInt(engineerField), convertToInt(traderField), convertToInt(fighterField));
                Toast toast = Toast.makeText(getApplicationContext(), "Successfully created player", Toast.LENGTH_SHORT);

                setContentView(R.layout.transition);

                toast.setGravity(Gravity.TOP, 0, 0);
                toast.show();
        }
    }

    private int convertToInt(android.widget.EditText widget) {
        return Integer.parseInt(widget.getText().toString());
    }


}
