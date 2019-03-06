package edu.gatech.cs2340.spacetrader.view;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import edu.gatech.cs2340.spacetrader.viewmodel.MarketViewModel;
import edu.gatech.cs2340.spacetrader.R;

public class MarketActivity extends AppCompatActivity {

    private MarketViewModel viewModel;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.market);

        viewModel = ViewModelProviders.of(this).get(MarketViewModel.class);
    }

}
