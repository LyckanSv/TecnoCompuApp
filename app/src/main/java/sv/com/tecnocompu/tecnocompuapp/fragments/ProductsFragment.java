package sv.com.tecnocompu.tecnocompuapp.fragments;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sv.com.tecnocompu.tecnocompuapp.R;
import sv.com.tecnocompu.tecnocompuapp.adapters.ProductAdapter;
import sv.com.tecnocompu.tecnocompuapp.pojos.Deal;
import sv.com.tecnocompu.tecnocompuapp.pojos.Product;
import sv.com.tecnocompu.tecnocompuapp.pojos.Products;
import sv.com.tecnocompu.tecnocompuapp.utils.RecyclerListener;
import sv.com.tecnocompu.tecnocompuapp.webapi.TecnoCompuAPI;
import sv.com.tecnocompu.tecnocompuapp.webapi.TecnoCompuAPIControler;

import static sv.com.tecnocompu.tecnocompuapp.utils.Constants.BUNDLE_PRODUCT;


public class ProductsFragment extends Fragment implements SearchView.OnQueryTextListener, RecyclerListener {

    private Unbinder unbinder;

    private OnFragmentInteractionListener mListener;

    private TecnoCompuAPI tc;
    private List<Product> products = new ArrayList<>();
    private ProductAdapter productAdapter;

    @BindView(R.id.products_recycler)
    RecyclerView recyclerView;

    @BindView(R.id.empty_message)
    TextView emptyMessage;

    @BindView(R.id.refresh_container)
    SwipeRefreshLayout refreshLayout;

    public ProductsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_products, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this.getActivity(), 2));

        tc = TecnoCompuAPIControler.getClient();

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                sendQuery();
            }
        });

        refreshLayout.setRefreshing(true);
        sendQuery();


    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);
        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(this);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            //mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (productAdapter != null) {
            productAdapter.getFilter().filter(newText);
        }
        return true;
    }

    @Override
    public void onClickListener(Object object) {
        if (object != null && object instanceof Product) {
            mListener.onFragmentInteraction(object, BUNDLE_PRODUCT);
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Object object, String flag);
    }

    public void sendQuery() {
        tc.getProducts().enqueue(new Callback<Products>() {
            @Override
            public void onResponse(Call<Products> call, Response<Products> response) {
                if (response.isSuccessful()) {
                    workResponse(response.body());
                } else {
                    generateMessage(0);
                    refreshLayout.setRefreshing(false);
                }

            }

            @Override
            public void onFailure(Call<Products> call, Throwable t) {
                generateMessage(0);
                refreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void workResponse(Products body) {
        products = body.getProducts();
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(this.getActivity(), R.anim.grid_layout_animation_from_bottom);
        recyclerView.setLayoutAnimation(animation);
        productAdapter = new ProductAdapter(products, this.getActivity(), this);
        recyclerView.setAdapter(productAdapter);
        generateMessage(body.getProducts().size());
        refreshLayout.setRefreshing(false);
    }


    private void generateMessage(int i) {
        if (i == 0) {
            emptyMessage.setVisibility(View.VISIBLE);
        } else {
            emptyMessage.setVisibility(View.INVISIBLE);
        }
    }
}
