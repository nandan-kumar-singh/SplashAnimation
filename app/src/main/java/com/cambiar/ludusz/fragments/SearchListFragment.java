package com.cambiar.ludusz.fragments;

/**
 * Created by vibes on 29/10/15.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cambiar.ludusz.R;
import com.cambiar.ludusz.adapter.SearchRecyclerViewAdapter;
import com.cambiar.ludusz.userrole.PlayerLandPage;


public class SearchListFragment extends Fragment {
    private static final String TAG = SearchListFragment.class.getSimpleName();

    public SearchListFragment() {

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
        View rootView = inflater.inflate(R.layout.fragment_list_search_data, container, false);
        initViews(rootView);
        return rootView;
    }

    private void initViews(View view) {
        SearchRecyclerViewAdapter searchRecyclerViewAdapter = new SearchRecyclerViewAdapter(getContext(), new PlayerLandPage(getContext()).getPlayersFavoriteBlog());
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_list_search_data);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setNestedScrollingEnabled(true);
        recyclerView.setAdapter(searchRecyclerViewAdapter);
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

