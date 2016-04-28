package com.cambiar.ludusz.fragments;

/**
 * Created by vibes on 29/10/15.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cambiar.ludusz.R;
import com.cambiar.ludusz.activities.MainActivity;
import com.cambiar.ludusz.adapter.PBViewPagerAdapter;
import com.cambiar.ludusz.interfaces.LandPage;
import com.cambiar.ludusz.model.Ludusz;
import com.cambiar.ludusz.userrole.PlayerLandPage;
import com.viewpagerindicator.CirclePageIndicator;
import com.viewpagerindicator.PageIndicator;

import java.lang.ref.WeakReference;

import butterknife.ButterKnife;

public class HomeFragment extends Fragment {
    private static final String TAG = "HomeFragment";
    private Toolbar mToolbar;
    private static WeakReference<HomeFragment> homeFragmentWeakReference;

    public static HomeFragment getInstance() {
        if (homeFragmentWeakReference == null) {
            homeFragmentWeakReference = new WeakReference<>(new HomeFragment());
        }
        return homeFragmentWeakReference.get();
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

    }

    public void setUpToolbar(View view) {
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_location);
        mToolbar.setPadding(0, 0, 0, 0);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        // ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(null);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        View mCustomView = getLayoutInflater(null).inflate(R.layout.layout_landing_page_toolbar, null);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setCustomView(mCustomView);
        mToolbar.addView(mCustomView);
        MainActivity.drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) getActivity().findViewById(R.id.drawer_layout), mToolbar);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            Intent intent = new Intent(getActivity(), SettingsActivity.class).setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
            getActivity().overridePendingTransition(R.anim.right_to_left_animation, R.anim.left_to_right_animation);

        }*/
        return false;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_landing_page_player, menu);
        //MenuItem myMenuItem = menu.findItem(R.id.action_notification);
        super.onCreateOptionsMenu(menu, inflater);
    }

}
