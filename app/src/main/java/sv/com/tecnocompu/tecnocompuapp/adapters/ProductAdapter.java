package sv.com.tecnocompu.tecnocompuapp.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import sv.com.tecnocompu.tecnocompuapp.R;
import sv.com.tecnocompu.tecnocompuapp.pojos.Product;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> implements Filterable {

    private List<Product> items = new ArrayList<>();
    private List<Product> itemsFilter = new ArrayList<>();
    private Context context;

    public ProductAdapter(List<Product> productAdapters, Context context) {
        this.items = productAdapters;
        this.context = context;
        this.itemsFilter = productAdapters;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();

                if (charString.isEmpty()) {
                    itemsFilter = items;
                } else {
                    ArrayList<Product> filteredList = new ArrayList<>();
                    for (Product p : items) {
                        if (p.getName().toLowerCase().contains(constraint)) {
                            filteredList.add(p);
                        }
                    }
                    itemsFilter = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = itemsFilter;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                itemsFilter = (ArrayList<Product>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title)
        TextView name;

        @BindView(R.id.item_thumbnail)
        ImageView thumbnail;

        public ProductViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_products_items, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        Product product = itemsFilter.get(position);
        holder.name.setText(product.getName());
        Picasso.with(context).load(product.getPictureUrl()).error(R.drawable.nodisponible).into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return itemsFilter.size();
    }


}
