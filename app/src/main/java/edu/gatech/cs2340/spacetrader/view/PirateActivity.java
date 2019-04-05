package edu.gatech.cs2340.spacetrader.view;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import edu.gatech.cs2340.spacetrader.R;
import edu.gatech.cs2340.spacetrader.viewmodel.PirateViewModel;

public class PirateActivity extends AppCompatActivity {

    private PirateViewModel viewModel;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pirate_encounter);
        viewModel = ViewModelProviders.of(this).get(PirateViewModel.class);
    }

}
