package beer.brew.vendingmachine.ui.beer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import beer.brew.vendingmachine.R;
import beer.brew.vendingmachine.data.model.Beer;
import butterknife.BindView;
import butterknife.ButterKnife;

public class BeerAdapter extends RecyclerView.Adapter<BeerAdapter.BeerViewHolder> {

    private List<Beer> beers = new ArrayList<>();
    private Context context;

    public BeerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public BeerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_beer, parent, false);
        return new BeerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BeerViewHolder holder, int position) {
        Beer beer = beers.get(position);
        Picasso.with(context).load(beer.getImage()).into(holder.beerAvatarView);
        holder.beerNameView.setText(beer.getName());
        holder.beerPriceView.setText(beer.getPrice());
    }

    @Override
    public int getItemCount() {
        return beers.size();
    }

    public void setBeers(List<Beer> newBeers) {
        beers.clear();
        beers.addAll(newBeers);
        notifyDataSetChanged();
    }

    class BeerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.beer_name)
        TextView beerNameView;

        @BindView(R.id.beer_price)
        TextView beerPriceView;

        @BindView(R.id.beer_avatar)
        ImageView beerAvatarView;

        public BeerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
