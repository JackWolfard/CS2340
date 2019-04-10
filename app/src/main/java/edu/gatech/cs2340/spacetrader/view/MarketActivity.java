package edu.gatech.cs2340.spacetrader.view;

import android.arch.lifecycle.ViewModelProviders;
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
    private ArrayList<String> amountOwned;

    private TextView credits;
    private TextView inventory;
    private Button backButton;
    private Button buyButton;
    private Button sellButton;

    public static boolean isBuy;
    public static boolean isSell;

    /**
     * Method gets called when the instance is changed to MarketActivity
     * @param savedInstanceState
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.market);

        viewModel = ViewModelProviders.of(this).get(MarketViewModel.class);

        credits = findViewById(R.id.creditText);
        backButton = findViewById(R.id.backButton);
        buyButton = findViewById(R.id.buyButton);
        sellButton = findViewById(R.id.sellButton);
        inventory = findViewById(R.id.inventoryText);
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.refreshMarket();
        refresh();
    }

    public void refresh() {
        ArrayList<ArrayList<String>> info = viewModel.initMarket();

        goodNames = info.get(0);
        quantities = info.get(1);
        prices = info.get(2);
        sellPrices = info.get(3);
        amountOwned = info.get(4);

        credits.setText(String.valueOf(viewModel.initCredits()));

        backButton.setBackgroundResource(android.R.drawable.btn_default);
        sellButton.setBackgroundResource(android.R.drawable.btn_default);
        buyButton.setBackgroundResource(android.R.drawable.btn_default);
        if (isBuy) {
            buyButton.setBackgroundColor(Color.GREEN);
            sellButton.setBackgroundResource(android.R.drawable.btn_default);
        } else if (isSell) {
            sellButton.setBackgroundColor(Color.GREEN);
            buyButton.setBackgroundResource(android.R.drawable.btn_default);
        }

        int[] inventoryInfo = viewModel.initInventory();
        inventory.setText(String.valueOf(inventoryInfo[1]) + "/"
                + String.valueOf(inventoryInfo[0]));

        initRecyclerView();

    }

    /**
     * This method initializes the contents of the recyclerView
     */
    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: init recyclerview");
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, goodNames, quantities,
                prices, sellPrices, amountOwned, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     * This method is called when the back button is pressed.
     * It changes the active intent to PlanetActivity
     * @param view
     */
    public void onBackPressed(View view) {
        Log.d("Edit", "Go to planet screen pressed");
        finish();
    }

    /**
     * This method is called when the sell button is pressed.
     * It sets the button's color to green, indicating the sell mode is active
     * @param view
     */
    public void onSellPressed(View view) {
        isSell = true;
        isBuy = false;
        sellButton.setBackgroundColor(Color.GREEN);
        buyButton.setBackgroundResource(android.R.drawable.btn_default);
    }

    /**
     * This method is called when the buy button is pressed.
     * It sets the button's color to green, indicating the buy mode is active
     * @param view
     */
    public void onBuyPressed(View view) {
        isBuy = true;
        isSell = false;
        buyButton.setBackgroundColor(Color.GREEN);
        sellButton.setBackgroundResource(android.R.drawable.btn_default);
    }

    /**
     * This method calls the MarketViewModel's buyGood method
     * This method is called by the recyclerViewAdapter
     * @param good
     */
    public void buyGood(Goods good) {
        viewModel.buyGood(good);
    }

    /**
     * This method calls the MarketViewModel's sellGood method
     * This method is called by the recyclerViewAdapter
     * @param good
     */
    public void sellGood(Goods good) {
        viewModel.sellGood(good);
    }

}
