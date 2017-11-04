package sv.com.tecnocompu.tecnocompuapp.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import sv.com.tecnocompu.tecnocompuapp.R;
import sv.com.tecnocompu.tecnocompuapp.pojos.Deal;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DealDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DealDetailFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    Unbinder unbinder;

    // TODO: Rename and change types of parameters
    private Deal deal;

    @BindView(R.id.deal_title)
    TextView dealTitle;

    @BindView(R.id.deal_subtitle)
    TextView dealSubtitle;


    @BindView(R.id.deal_description)
    TextView dealDescription;


    public DealDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment DealDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DealDetailFragment newInstance(Deal param1) {
        DealDetailFragment fragment = new DealDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            deal = getArguments().getParcelable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_deal_detail, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (deal != null) {
            dealTitle.setText(deal.getTitle());
            dealSubtitle.setText(deal.getSubtitle());
            dealDescription.setText(deal.getDescription());
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
