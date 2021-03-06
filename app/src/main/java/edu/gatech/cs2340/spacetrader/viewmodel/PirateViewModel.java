package edu.gatech.cs2340.spacetrader.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import java.util.List;
import java.util.Random;

import edu.gatech.cs2340.spacetrader.model.Model;
import edu.gatech.cs2340.spacetrader.model.Pirate;
import edu.gatech.cs2340.spacetrader.model.Universe;

public class PirateViewModel extends AndroidViewModel {

    private final Universe universe;
    private final List<Pirate> pirateList;
    private final Pirate currentPirate;
    private final Random piratePicker = new Random();
    private final int randomInt;

    public PirateViewModel(@NonNull Application application) {
        super(application);
        universe = Model.getInstance().getGame().getUniverse();
        pirateList = universe.getPirateList();
        randomInt = piratePicker.nextInt(10);
        currentPirate = pirateList.get(randomInt);
    }

    public String[] initText() {
        String[] text = new String[] {currentPirate.getName(),
                String.valueOf(currentPirate.getPower())};
        return text;
    }

    public int getPiratePower() {
        return currentPirate.getPower();
    }
}
