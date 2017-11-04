package sv.com.tecnocompu.tecnocompuapp.fragments;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sv.com.tecnocompu.tecnocompuapp.R;
import sv.com.tecnocompu.tecnocompuapp.adapters.DealAdapter;
import sv.com.tecnocompu.tecnocompuapp.pojos.Deal;
import sv.com.tecnocompu.tecnocompuapp.pojos.Deals;
import sv.com.tecnocompu.tecnocompuapp.webapi.TecnoCompuAPI;
import sv.com.tecnocompu.tecnocompuapp.webapi.TecnoCompuAPIControler;


public class DealsFragment extends Fragment {

    private TecnoCompuAPI tc;
    private OnFragmentInteractionListener mListener;
    private List<Deal> deals = new ArrayList<>();
    private RecyclerView recyclerView;

    public DealsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_deals, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.deals_recycler);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        tc = TecnoCompuAPIControler.getClient();
        startQuery();
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
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

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void startQuery() {
        tc.getDeals().enqueue(new Callback<Deals>() {
            @Override
            public void onResponse(Call<Deals> call, Response<Deals> response) {
                DealAdapter dealAdapter = new DealAdapter(response.body().getDeals());
                recyclerView.setAdapter(dealAdapter);
            }

            @Override
            public void onFailure(Call<Deals> call, Throwable t) {

            }
        });

    }
}
