package sv.com.tecnocompu.tecnocompuapp.fragments;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import android.support.design.widget.NavigationView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import sv.com.tecnocompu.tecnocompuapp.R;


public class ContactFragment extends Fragment {

    private OnFragmentInteractionListener mListener;


    public ContactFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contact, container, false);
        WebView webview = (WebView) view.findViewById(R.id.map);
        webview.setWebViewClient(new WebViewClient());
        webview.getSettings().setJavaScriptEnabled(true);

        webview.loadUrl("https://www.google.es/maps/place/TECNO-COMPU/@13.8764702," +
                "-88.6272012,19z/data=!3m1!4b1!4m13!1m7!3m6!1s0x8f64bd2d9575edbb:0xc2" +
                "12434648ef85b7!2sSensuntepeque!3b1!8m2!3d13.8699846!4d-88.6289598!3m4!" +
                "1s0x8f64bd31d298f595:0xc9c8795ec3a7ce31!8m2!3d13.8764689!4d-88.626654");


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
}
