package com.cambiar.ludusz.fragments;

/**
 * Created by vibes on 29/10/15.
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.cambiar.ludusz.R;
import com.cambiar.ludusz.activities.PlayerProfilePage;
import com.cambiar.ludusz.adapter.SearchRecyclerViewAdapter;
import com.cambiar.ludusz.model.SearchFilter;
import com.cambiar.ludusz.userModel.SearchListPage;

public class SearchListFragment extends Fragment implements View.OnClickListener {
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
        SearchListPage searchListPage = new SearchListPage(getContext());
        SearchRecyclerViewAdapter searchRecyclerViewAdapter = new SearchRecyclerViewAdapter(getContext(), searchListPage.getCoachSearchList("", new SearchFilter()));
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_list_search_data);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setNestedScrollingEnabled(true);
        recyclerView.setAdapter(searchRecyclerViewAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new DrawerFragment.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                startActivity(new Intent(getContext(), PlayerProfilePage.class));
            }

            @Override
            public void onLongClick(View view, int position) {
                String title = "Click to go to this screen";

            }
        }));
    }

    // A place has been received; use requestCode to track the request.
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {

    }

    static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private DrawerFragment.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final DrawerFragment.ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }

    }
}

