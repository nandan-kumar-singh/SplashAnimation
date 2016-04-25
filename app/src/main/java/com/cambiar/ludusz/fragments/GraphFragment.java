package com.cambiar.ludusz.fragments;

/**
 * Created by vibes on 29/10/15.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cambiar.ludusz.R;


public class GraphFragment extends Fragment {
    private static final String TAG = "GraphFragment";
    private Toolbar mToolbar;
    private static GraphFragment profileFragment;

    //private Typeface tf;
    public static GraphFragment getInstance() {
        if (profileFragment == null) {
            profileFragment = new GraphFragment();
        }
        return profileFragment;
    }

    public GraphFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (savedInstanceState != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_graph, container, false);

        return rootView;
    }

    // A place has been received; use requestCode to track the request.
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}

