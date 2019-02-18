package edu.gatech.cs2340.spacetrader.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import edu.gatech.cs2340.spacetrader.viewmodel.ConfigurationViewModel;
import edu.gatech.cs2340.spacetrader.R;

public class ConfigurationActivity extends AppCompatActivity {

    private ConfigurationViewModel configVM;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.config);
    }
}
