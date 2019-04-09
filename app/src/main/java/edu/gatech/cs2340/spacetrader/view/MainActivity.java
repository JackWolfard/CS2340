package edu.gatech.cs2340.spacetrader.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import edu.gatech.cs2340.spacetrader.R;
import edu.gatech.cs2340.spacetrader.entity.GameDifficulty;
import edu.gatech.cs2340.spacetrader.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity {

    private MainViewModel viewModel;

    private enum ChildActivity {
        NEW, LOAD;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent resultIntent) {
        ChildActivity childActivity = ChildActivity.values()[requestCode];
        switch (childActivity) {
            case NEW:
                if (resultCode == RESULT_OK) {
                    GameDifficulty difficulty = (GameDifficulty) resultIntent.getSerializableExtra(
                            "difficulty");
                    String name = resultIntent.getStringExtra("name");
                    int pilotPt = resultIntent.getIntExtra("pilotPt", 0);
                    int engPt = resultIntent.getIntExtra("engPt", 0);
                    int tradePt = resultIntent.getIntExtra("tradePt", 0);
                    int fightPt = resultIntent.getIntExtra("fightPt", 0);
                    viewModel.initGame(difficulty, name, pilotPt, engPt, tradePt, fightPt);

                    Intent intent = new Intent(this, PlanetActivity.class);
                    startActivity(intent);
                }
                break;

            default:
                break;
        }
    }

    public void onNewPressed(View view) {
        Log.d("MainActivity", "New Game Pressed");
        Intent intent = new Intent(MainActivity.this, ConfigurationActivity.class);
        startActivityForResult(intent, ChildActivity.NEW.ordinal());
    }

    public void onLoadPressed(View view) {
        Log.d("MainActivity", "Load Game Pressed");
        if (viewModel.loadGame(this)) {
            Toast toast = Toast.makeText(this, "Successfully Loaded Game", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP, 0, 0);
            toast.show();
        } else {
            Toast toast = Toast.makeText(this, "Cannot Load Game", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP, 0, 0);
            toast.show();
        }
    }

    public void onSavePressed(View view) {
        Log.d("MainActivity", "Save Game Pressed");
        if (viewModel.saveGame(this)) {
            Toast toast = Toast.makeText(getApplicationContext(), "Successfully Saved Game",
                    Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP, 0, 0);
            toast.show();
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "Cannot Save Game",
                    Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP, 0, 0);
            toast.show();
        }
    }

    public void onResumePressed(View view) {
        Log.d("MainActivity", "Resume Game Pressed");

        if (viewModel.getGameStarted()) {
            Intent intent = new Intent(MainActivity.this, PlanetActivity.class);
            startActivity(intent);
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "No Game Available",
                    Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP, 0, 0);
            toast.show();
        }
    }


}
