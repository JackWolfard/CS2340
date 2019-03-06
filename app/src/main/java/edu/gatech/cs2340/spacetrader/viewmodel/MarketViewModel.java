package edu.gatech.cs2340.spacetrader.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import edu.gatech.cs2340.spacetrader.model.Model;

public class MarketViewModel extends AndroidViewModel {

    private Model model;

    public MarketViewModel(@NonNull Application application) {
        super(application);
        model = Model.getInstance();
    }

}
