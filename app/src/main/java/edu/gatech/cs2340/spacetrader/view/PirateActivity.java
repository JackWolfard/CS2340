package edu.gatech.cs2340.spacetrader.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import edu.gatech.cs2340.spacetrader.R;
import edu.gatech.cs2340.spacetrader.viewmodel.PirateViewModel;

public class PirateActivity extends AppCompatActivity {

    private PirateViewModel viewModel;

    private TextView pirateName;
    private TextView piratePower;
    private TextView description;

    //private String[] pirateInfo;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pirate_encounter);
        viewModel = ViewModelProviders.of(this).get(PirateViewModel.class);

        pirateName = findViewById(R.id.pirateName);
        piratePower = findViewById(R.id.piratePower);
        description = findViewById(R.id.pirateDescription);

        //pirateInfo = viewModel.initText();
        initTextFields();
    }

    private void initTextFields() {
        String[] pirateInfo = viewModel.initText();
        int powerInt = viewModel.getPiratePower();

        String tempText = "The pirate " + pirateInfo[0] + " approaches!";
        pirateName.setText(tempText);

        tempText = "Power: " + pirateInfo[1];
        piratePower.setText(tempText);

        if (powerInt <= 30) {
            tempText = "A small pirate ship approaches. It appear to be weak, but you can " +
                    "never be too careful. Will you fight or flee?";
        } else if (powerInt > 30 && powerInt <= 75) {
            tempText = "A mid-size pirate ship approaches. It looks like it could pose a " +
                    "serious threat to your ship. Will you fight or flee?";
        } else if (powerInt == 100) {
            tempText = "A large pirate ship approaches. It looks very dangerous, and " +
                    "should be approached with great caution. Will you fight or flee?";
        } else {
            tempText = "A massive ship appears in the distance. You've heard rumors of this " +
                    "ship, and you already know how dangerous it is. You've been unlucky enough" +
                    " to run into Blackbeard, the deadliest pirate in the galaxy. Will you " +
                    "fight or flee?";
        }
        description.setText(tempText);
    }

    public void onFleePressed(View view) {
        Log.d("PirateActivity", "Flee is pressed. Going to planet.");
        finish();
    }

}
