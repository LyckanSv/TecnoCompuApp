package sv.com.tecnocompu.tecnocompuapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import sv.com.tecnocompu.tecnocompuapp.fragments.DealDetailFragment;
import sv.com.tecnocompu.tecnocompuapp.fragments.ProductDetailFragment;
import sv.com.tecnocompu.tecnocompuapp.pojos.Deal;
import sv.com.tecnocompu.tecnocompuapp.pojos.Product;

import static sv.com.tecnocompu.tecnocompuapp.utils.Constants.BUNDLE_DEAL;
import static sv.com.tecnocompu.tecnocompuapp.utils.Constants.BUNDLE_KEY;
import static sv.com.tecnocompu.tecnocompuapp.utils.Constants.BUNDLE_PRODUCT;
import static sv.com.tecnocompu.tecnocompuapp.utils.Constants.FLAG;
import static sv.com.tecnocompu.tecnocompuapp.utils.Constants.PICTURE_URL;

public class DetailActivity extends AppCompatActivity {

    private String FRAGMENT_TO_LOAD;
    private Deal deal;
    private Product product;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tab_header)
    ImageView tabHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        if (getIntent().getExtras() != null) {
            switch (getIntent().getStringExtra(FLAG)) {
                case BUNDLE_PRODUCT:
                    FRAGMENT_TO_LOAD = BUNDLE_PRODUCT;
                    product = getIntent().getParcelableExtra(BUNDLE_KEY);
                    prepareProduct(product);
                    break;

                case BUNDLE_DEAL:
                    FRAGMENT_TO_LOAD = BUNDLE_PRODUCT;
                    deal = getIntent().getParcelableExtra(BUNDLE_KEY);
                    prepareDeal(deal);
                    break;


            }
        }

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void prepareDeal(Deal deal) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, DealDetailFragment.newInstance(deal)).commit();
        Picasso.with(this).load(PICTURE_URL + deal.getImageUrl()).error(R.drawable.nodisponible).into(tabHeader);
    }

    private void prepareProduct(Product product) {
        Picasso.with(this).load(PICTURE_URL + product.getPictureUrl()).error(R.drawable.nodisponible).into(tabHeader);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, ProductDetailFragment.newInstance(product)).commit();
    }
}
