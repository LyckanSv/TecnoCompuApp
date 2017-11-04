package sv.com.tecnocompu.tecnocompuapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import sv.com.tecnocompu.tecnocompuapp.fragments.DealDetailFragment;
import sv.com.tecnocompu.tecnocompuapp.fragments.ProductDetailFragment;
import sv.com.tecnocompu.tecnocompuapp.pojos.Deal;
import sv.com.tecnocompu.tecnocompuapp.pojos.Product;

import static sv.com.tecnocompu.tecnocompuapp.utils.Constants.BUNDLE_DEAL;
import static sv.com.tecnocompu.tecnocompuapp.utils.Constants.BUNDLE_KEY;
import static sv.com.tecnocompu.tecnocompuapp.utils.Constants.BUNDLE_PRODUCT;
import static sv.com.tecnocompu.tecnocompuapp.utils.Constants.FLAG;

public class DetailActivity extends AppCompatActivity {

    private String FRAGMENT_TO_LOAD;
    private Deal deal;
    private Product product;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
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
    }

    private void prepareDeal(Deal deal) {

    }

    private void prepareProduct(Product product) {
    }
}
