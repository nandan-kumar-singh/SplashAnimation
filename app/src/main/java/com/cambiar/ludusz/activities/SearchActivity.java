package com.cambiar.ludusz.activities;

/**
 * Created by vibes on 29/10/15.
 */

import android.Manifest;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.cambiar.ludusz.R;
import com.cambiar.ludusz.adapter.SearchViewPagerAdapter;
import com.cambiar.ludusz.fragments.SearchListFragment;
import com.cambiar.ludusz.model.Ludusz;
import com.cambiar.ludusz.model.UserData;
import com.cambiar.ludusz.util.LuduszConstants;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;

public class SearchActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = SearchActivity.class.getSimpleName();
    private Ludusz ludusz;
    private TextView textViewUserLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ludusz = (Ludusz) getApplicationContext();
        initViews();
        setUpToolBar();
    }

    @Override
    protected void onStart() {
        super.onStart();
        UserData userData = ludusz.getUserData();
        if (userData != null) {
            textViewUserLocation.setText(userData.getUserAddress());
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                this.finish();

            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void setUpToolBar() {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        View mCustomView = getLayoutInflater().inflate(R.layout.layout_landing_page_toolbar, null);

        mCustomView.findViewById(R.id.btn_player_location).setOnClickListener(this);
        mCustomView.findViewById(R.id.img_btn_search).setVisibility(View.GONE);
        mCustomView.findViewById(R.id.img_btn_notification).setOnClickListener(this);
        mCustomView.findViewById(R.id.img_btn_notification).setVisibility(View.GONE);
        textViewUserLocation = (TextView) mCustomView.findViewById(R.id.tv_user_location);
        getSupportActionBar().setCustomView(mCustomView);
        mToolbar.addView(mCustomView);
    }

    private void initViews() {
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout_search);
        if (tabLayout != null)
            tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpagerContainer);
        if (viewPager != null) {
            viewPager.setScrollContainer(false);
            viewPager.setHorizontalScrollBarEnabled(false);
            viewPager.setOffscreenPageLimit(0);
        }

        setupViewPager(viewPager);

        if (tabLayout != null)
            tabLayout.setupWithViewPager(viewPager);

        if (getIntent().hasExtra("tab_id")) {
            if (tabLayout != null)
                tabLayout.setScrollPosition(getIntent().getIntExtra("tab_id", 0), 0f, true);
            if (viewPager != null)
                viewPager.setCurrentItem(getIntent().getIntExtra("tab_id", 0));
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        SearchViewPagerAdapter searchViewPagerAdapter = new SearchViewPagerAdapter(getSupportFragmentManager());

        for (String tabItem : getResources().getStringArray(R.array.search_tab_items)) {
            SearchListFragment searchListFragment = new SearchListFragment();
            searchViewPagerAdapter.addFragment(searchListFragment, tabItem);
        }
        viewPager.setAdapter(searchViewPagerAdapter);
        //viewPagerAdapter.notifyDataSetChanged();
        Log.d("TAG", "setupViewPager");

    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_btn_search: {
                break;
            }
            case R.id.btn_player_location: {
                Toast.makeText(this, "Hello Location", Toast.LENGTH_SHORT).show();
                callPlaceAutocompleteActivityIntent();
                break;
            }
            default:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        // Retrieve the SearchView and plug it into SearchManager
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        Log.d(TAG, searchView.getQuery().toString());
        return true;
    }

    private void callPlaceAutocompleteActivityIntent() {
        try {
            Intent intent =
                    new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                            .build(this);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivityForResult(intent, LuduszConstants.Field.PLACE_AUTOCOMPLETE_REQUEST_CODE);
        } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
            // TODO: Handle the error.
        }

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == LuduszConstants.Field.PLACE_AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(this, data);

                UserData userData = new UserData();
                userData.setUserAddress(place.getName().toString() + "");
                userData.setUserLocationLatitude(place.getLatLng().latitude);
                userData.setUserLocationLongitude(place.getLatLng().longitude);
                ((Ludusz) getApplicationContext()).setUserData(userData);

                textViewUserLocation.setText(place.getName());
                Log.i(TAG, "Place:" + place.toString());
            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(this, data);
                Log.i(TAG, status.getStatusMessage());
            } else if (requestCode == Activity.RESULT_CANCELED) {

            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == LuduszConstants.Field.ACTION_CALL_REQUEST_CODE) {
            for (int i = 0; i < permissions.length; i++) {
                String permission = permissions[i];
                int grantResult = grantResults[i];

                if (permission.equals(Manifest.permission.CALL_PHONE)) {
                    if (grantResult == PackageManager.PERMISSION_GRANTED) {

                    } else {

                    }
                }
            }
        }


    }
}

