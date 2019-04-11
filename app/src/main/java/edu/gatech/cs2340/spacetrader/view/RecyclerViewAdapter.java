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

import java.util.List;

import edu.gatech.cs2340.spacetrader.R;
import edu.gatech.cs2340.spacetrader.entity.Goods;

/**
 * RecyclerViewAdapter that controls the recyclerView for the market UI screen
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    private List<String> goodNames;
    private List<String> quantities;
    private List<String> prices;
    private List<String> sellPrices;
    private List<String> amountOwned;
    private Context mContext;
    private MarketActivity marketActivity;

    /**
     * Constructor for the RecyclerViewAdapter
     * @param mContext context
     * @param goodNames ArrayList of good names
     * @param quantities ArrayList of quantities of each good
     * @param prices ArrayList of the price of each good
     * @param sellPrices ArrayList of sell prices
     * @param amountOwned ArrayList of the amount owned of each good
     * @param marketActivity marketActivity passed in to call activity methods
     */
    public RecyclerViewAdapter(Context mContext, List<String> goodNames,
                               List<String> quantities, List<String> prices,
                               List<String> sellPrices, List<String> amountOwned,
                               MarketActivity marketActivity) {
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem,
                parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        Log.d(TAG, "onBindViewHolder: called.");

        viewHolder.goodName.setText(goodNames.get(viewHolder.getAdapterPosition()));
        viewHolder.quantityText.setText(quantities.get(viewHolder.getAdapterPosition()));
        viewHolder.priceText.setText(prices.get(viewHolder.getAdapterPosition()));
        viewHolder.sellText.setText(sellPrices.get(viewHolder.getAdapterPosition()));
        viewHolder.amountOwnedText.setText(amountOwned.get(viewHolder.getAdapterPosition()));

        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            /**
             * This code is run when a field in the recycler view is tapped by the user
             */
            public void onClick(View v) {
                if (marketActivity.isBuy) {
                    try {
                        marketActivity.buyGood(Goods.valueOf(goodNames.get(viewHolder.getAdapterPosition()).toUpperCase()));
                        Toast toast = Toast.makeText(mContext, goodNames.get(viewHolder.getAdapterPosition()) + " bought",
                                Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.TOP, 0, 0);
                        toast.show();
                        marketActivity.refresh();
                    } catch (IndexOutOfBoundsException e) {
                        Toast toast = Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.TOP, 0, 0);
                        toast.show();
                    }
                } else if (marketActivity.isSell) {
                    try {
                        marketActivity.sellGood(Goods.valueOf(goodNames.get(viewHolder.getAdapterPosition()).toUpperCase()));
                        Toast toast = Toast.makeText(mContext, goodNames.get(viewHolder.getAdapterPosition()) + " sold",
                                Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.TOP, 0, 0);
                        toast.show();
                        marketActivity.refresh();
                    } catch (IndexOutOfBoundsException e) {
                        Toast toast = Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.TOP, 0, 0);
                        toast.show();
                    }
                } else {
                    Toast toast = Toast.makeText(mContext, "Select buy or sell",
                            Toast.LENGTH_SHORT);
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

        /**
         * ViewHolder constructor sets the variables to UI itemViews
         * @param itemView
         */
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
