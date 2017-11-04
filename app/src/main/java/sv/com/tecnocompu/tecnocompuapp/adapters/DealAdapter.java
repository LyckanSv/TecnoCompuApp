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

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import sv.com.tecnocompu.tecnocompuapp.R;
import sv.com.tecnocompu.tecnocompuapp.pojos.Deal;
import sv.com.tecnocompu.tecnocompuapp.utils.RecyclerListener;

import static sv.com.tecnocompu.tecnocompuapp.utils.Constants.PICTURE_URL;

public class DealAdapter extends RecyclerView.Adapter<DealAdapter.AdapterViewHolder> implements Filterable {
    private Context context;
    private List<Deal> items = new ArrayList<>();
    private List<Deal> itemsFilter = new ArrayList<>();
    private RecyclerListener listener;

    public DealAdapter(List<Deal> items, Context context, RecyclerListener listener) {
        this.items = items;
        this.context = context;
        this.itemsFilter = items;
        this.listener = listener;
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
                    ArrayList<Deal> filteredList = new ArrayList<>();
                    for (Deal d : items) {
                        if (d.getTitle().toLowerCase().contains(constraint)) {
                            filteredList.add(d);
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
                itemsFilter = (ArrayList<Deal>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public class AdapterViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.title)
        TextView title;

        @BindView(R.id.item_thumbnail)
        ImageView itemThumbnail;

        public AdapterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public AdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_deals_item, parent, false);
        return new AdapterViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AdapterViewHolder holder, int position) {
        final Deal deal = itemsFilter.get(position);
        holder.title.setText(deal.getTitle());
        Picasso.with(context).load(PICTURE_URL + deal.getImageUrl()).error(R.drawable.nodisponible).into(holder.itemThumbnail);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClickListener(deal);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemsFilter.size();
    }


}
