package edu.gatech.cs2340.spacetrader.view;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import edu.gatech.cs2340.spacetrader.R;
import edu.gatech.cs2340.spacetrader.viewmodel.LoadViewModel;

public class LoadActivity extends AppCompatActivity {

    private LoadViewModel viewModel;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.load);

        viewModel = ViewModelProviders.of(this).get(LoadViewModel.class);

        TextView savedDataText = findViewById(R.id.savedDataText);
        savedDataText.setText("0");

        initRecyclerView();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.loadRecyclerView);
        LoadRecyclerViewAdapter adapter = new LoadRecyclerViewAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void onBackPressed(View view) {
        Log.d("LoadActivity", "Back pressed");
        finish();
    }
}
