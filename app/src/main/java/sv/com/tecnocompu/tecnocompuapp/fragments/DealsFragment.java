package sv.com.tecnocompu.tecnocompuapp.fragments;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
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
import sv.com.tecnocompu.tecnocompuapp.adapters.DealAdapter;
import sv.com.tecnocompu.tecnocompuapp.pojos.Deal;
import sv.com.tecnocompu.tecnocompuapp.pojos.Deals;
import sv.com.tecnocompu.tecnocompuapp.utils.RecyclerListener;
import sv.com.tecnocompu.tecnocompuapp.webapi.TecnoCompuAPI;
import sv.com.tecnocompu.tecnocompuapp.webapi.TecnoCompuAPIControler;

import static sv.com.tecnocompu.tecnocompuapp.utils.Constants.BUNDLE_DEAL;


public class DealsFragment extends Fragment implements SearchView.OnQueryTextListener, RecyclerListener {

    private Unbinder unbinder;

    private TecnoCompuAPI tc;
    private OnFragmentInteractionListener mListener;
    private List<Deal> deals = new ArrayList<>();
    DealAdapter dealAdapter;

    @BindView(R.id.deals_recycler)
    RecyclerView recyclerView;

    @BindView(R.id.empty_message)
    TextView emptyMessage;

    @BindView(R.id.refresh_container)
    SwipeRefreshLayout swipeRefreshLayout;

    Call<Deals> call;

    public DealsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_deals, container, false);
        unbinder = ButterKnife.bind(this, view);


        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this.getActivity(), 2));
        tc = TecnoCompuAPIControler.getClient();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                startQuery();
            }
        });
        swipeRefreshLayout.setRefreshing(true);
        startQuery();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);
        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(this);
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
        if (call != null)
            call.cancel();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (dealAdapter != null) {
            dealAdapter.getFilter().filter(newText);
        }
        return true;
    }

    @Override
    public void onClickListener(Object object) {
        if (object != null && object instanceof Deal) {
            mListener.onFragmentInteraction(object, BUNDLE_DEAL);
        }
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Object object, String flag);
    }

    public void startQuery() {
        call = tc.getDeals();
        call.enqueue(new Callback<Deals>() {
            @Override
            public void onResponse(Call<Deals> call, Response<Deals> response) {
                if (response.isSuccessful()) {
                    workResponse(response.body());
                } else {
                    generateMessage(0);
                    swipeRefreshLayout.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<Deals> call, Throwable t) {
                generateMessage(0);
                swipeRefreshLayout.setRefreshing(false);
            }
        });

    }

    private void workResponse(Deals body) {
        dealAdapter = new DealAdapter(body.getDeals(), this.getActivity(), this);
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(this.getActivity(), R.anim.grid_layout_animation_from_bottom);
        recyclerView.setLayoutAnimation(animation);
        recyclerView.setAdapter(dealAdapter);
        generateMessage(body.getDeals().size());
        swipeRefreshLayout.setRefreshing(false);
    }

    private void generateMessage(int i) {
        if (i == 0) {
            emptyMessage.setVisibility(View.VISIBLE);
        } else {
            emptyMessage.setVisibility(View.INVISIBLE);
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
