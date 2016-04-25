package com.cambiar.ludusz.fragments;

/**
 * Created by vibes on 29/10/15.
 */

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.cambiar.ludusz.R;
import com.cambiar.ludusz.activities.MainActivity;
import com.cambiar.ludusz.adapter.PBViewPagerAdapter;
import com.cambiar.ludusz.interfaces.LandPage;
import com.cambiar.ludusz.model.Ludusz;
import com.cambiar.ludusz.userrole.PlayerLandPage;
import com.viewpagerindicator.CirclePageIndicator;
import com.viewpagerindicator.PageIndicator;

import butterknife.ButterKnife;

public class HomeFragment extends Fragment {
    private static final String TAG = "HomeFragment";
    private Toolbar mToolbar;
    private static HomeFragment homeFragment;

    public static HomeFragment getInstance() {
        if (homeFragment == null) {
            homeFragment = new HomeFragment();
        }
        return homeFragment;
    }

    public HomeFragment() {
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

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(getActivity(), rootView);
        setUpToolbar(rootView);
        initViews(rootView);
        // Inflate the layout for this fragment
        return rootView;
    }

    private void initViews(View view) {
        if (Ludusz.getUser() == Ludusz.User.PLAYER) {
            Toast.makeText(getContext(), "Hello PLAYER", Toast.LENGTH_LONG).show();
        } else
            Toast.makeText(getContext(), Ludusz.getUser().name(), Toast.LENGTH_LONG).show();

        LandPage landPage = new PlayerLandPage(getContext());
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewPagerHome);
        viewPager.setAdapter(new PBViewPagerAdapter(getContext(), landPage.getPromotionalBanner()));
        PageIndicator titleIndicator = (CirclePageIndicator) view.findViewById(R.id.page_indicator);
        titleIndicator.setViewPager(viewPager);
//tab layout for feeds
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tablayout_feeds);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));

        View view1 = new TextView(getContext());
        view1.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        view1.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
        ((TextView) view1).setText("Local");

        tabLayout.addTab(tabLayout.newTab().setText("Local").setCustomView(view1));
        tabLayout.setSelectedTabIndicatorHeight(5);
        tabLayout.setTabTextColors(ContextCompat.getColor(getContext(), R.color.dark_black), ContextCompat.getColor(getContext(), R.color.colorPrimary));
        tabLayout.addTab(tabLayout.newTab().setText("Global"));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }


    public void setUpToolbar(View view) {
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        //mToolbar.setNavigationIcon(R.drawable.ic_navigation_menu);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.app_name);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        MainActivity.drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) getActivity().findViewById(R.id.drawer_layout), mToolbar);
    }


}
