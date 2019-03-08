package edu.gatech.cs2340.spacetrader.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import edu.gatech.cs2340.spacetrader.viewmodel.MarketViewModel;
import edu.gatech.cs2340.spacetrader.R;
import edu.gatech.cs2340.spacetrader.entity.Goods;

public class MarketActivity extends AppCompatActivity {

    private static final String TAG = "MarketActivity";

    private MarketViewModel viewModel;

    private ArrayList<String> goodNames;
    private ArrayList<String> quantities;
    private ArrayList<String> prices;
    private ArrayList<String> sellPrices;

    private Button buyButton;
    private Button sellButton;

    public static boolean isBuy;
    public static boolean isSell;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.market);

        viewModel = ViewModelProviders.of(this).get(MarketViewModel.class);

        TextView credits = findViewById(R.id.creditText);
        buyButton = findViewById(R.id.buyButton);
        sellButton = findViewById(R.id.sellButton);
        TextView inventory = findViewById(R.id.inventoryText);

        ArrayList<ArrayList<String>> info = viewModel.initMarket();

        goodNames = info.get(0);
        quantities = info.get(1);
        prices = info.get(2);
        sellPrices = info.get(3);

        credits.setText(String.valueOf(viewModel.initCredits()));
        isBuy = false;
        isSell = false;

        int[] inventoryInfo = viewModel.initInventory();
        inventory.setText(String.valueOf(inventoryInfo[1]) + "/" + String.valueOf(inventoryInfo[0]));

        initRecyclerView();

    }

    public void refresh() {

        startActivity(new Intent(MarketActivity.this, MarketActivity.class));
        this.overridePendingTransition(0, 0);
    }

    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: init recyclerview");
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, goodNames, quantities, prices, sellPrices, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void onBackPressed(View view) {
        Log.d("Edit", "Go to planet screen pressed");
        Intent intent = new Intent(MarketActivity.this, PlanetActivity.class);
        MarketActivity.this.startActivity(intent);
    }

    public void onSellPressed(View view) {
        isSell = true;
        isBuy = false;
        sellButton.setBackgroundColor(Color.GREEN);
        buyButton.setBackgroundResource(android.R.drawable.btn_default);
    }

    public void onBuyPressed(View view) {
        isBuy = true;
        isSell = false;
        buyButton.setBackgroundColor(Color.GREEN);
        sellButton.setBackgroundResource(android.R.drawable.btn_default);
    }

    public void buyGood(Goods good) {
        viewModel = ViewModelProviders.of(this).get(MarketViewModel.class);
        viewModel.buyGood(good);
    }

    public void sellGood(Goods good) {
        viewModel = ViewModelProviders.of(this).get(MarketViewModel.class);
        viewModel.sellGood(good);
    }

}
