package edu.gatech.cs2340.spacetrader.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import edu.gatech.cs2340.spacetrader.R;
import edu.gatech.cs2340.spacetrader.entity.Goods;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<String> goodNames;
    private ArrayList<String> quantities;
    private ArrayList<String> prices;
    private ArrayList<String> sellPrices;
    private ArrayList<String> amountOwned;
    private Context mContext;
    private MarketActivity marketActivity;

    public RecyclerViewAdapter(Context mContext, ArrayList<String> goodNames, ArrayList<String> quantities, ArrayList<String> prices, ArrayList<String> sellPrices, ArrayList<String> amountOwned, MarketActivity marketActivity) {
        this.mContext = mContext;
        this.goodNames = goodNames;
        this.quantities = quantities;
        this.prices = prices;
        this.sellPrices = sellPrices;
        this.amountOwned = amountOwned;
        this.marketActivity = marketActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        Log.d(TAG, "onBindViewHolder: called.");

        viewHolder.goodName.setText(goodNames.get(i));
        viewHolder.quantityText.setText(quantities.get(i));
        viewHolder.priceText.setText(prices.get(i));
        viewHolder.sellText.setText(sellPrices.get(i));
        viewHolder.amountOwnedText.setText(amountOwned.get(i));

        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MarketActivity.isBuy) {
                    try {
                        marketActivity.buyGood(Goods.valueOf(goodNames.get(i).toUpperCase()));
                        Toast toast = Toast.makeText(mContext, goodNames.get(i) + " bought", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.TOP, 0, 0);
                        toast.show();
                        marketActivity.refresh();
                    } catch (IndexOutOfBoundsException e) {
                        Toast toast = Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.TOP, 0, 0);
                        toast.show();
                    }
                } else if (MarketActivity.isSell) {
                    try {
                        marketActivity.sellGood(Goods.valueOf(goodNames.get(i).toUpperCase()));
                        Toast toast = Toast.makeText(mContext, goodNames.get(i) + " sold", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.TOP, 0, 0);
                        toast.show();
                        marketActivity.refresh();
                    } catch (IndexOutOfBoundsException e) {
                        Toast toast = Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.TOP, 0, 0);
                        toast.show();
                    }
                } else {
                    Toast toast = Toast.makeText(mContext, "Select buy or sell", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP, 0, 0);
                    toast.show();
                }


            }
        });
    }

    @Override
    public int getItemCount() {
        return goodNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView goodName;
        TextView quantityText;
        TextView priceText;
        TextView sellText;
        TextView amountOwnedText;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            goodName = itemView.findViewById(R.id.goodName);
            quantityText = itemView.findViewById(R.id.quantityText);
            priceText = itemView.findViewById(R.id.priceText);
            sellText = itemView.findViewById(R.id.sellText);
            amountOwnedText = itemView.findViewById(R.id.amountOwnedText);
            parentLayout = itemView.findViewById(R.id.parentLayout);
        }
    }

}
