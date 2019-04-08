package edu.gatech.cs2340.spacetrader.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import edu.gatech.cs2340.spacetrader.model.Model;

public class LoadViewModel extends AndroidViewModel {

    private Model model;

    public LoadViewModel(@NonNull Application application) {
        super(application);
        model = Model.getInstance();
    }


}
