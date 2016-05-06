package com.cambiar.ludusz.fragments;

/**
 * Created by vibes on 29/10/15.
 */

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cambiar.ludusz.R;
import com.cambiar.ludusz.activities.MainActivity;
import com.cambiar.ludusz.activities.SearchActivity;
import com.cambiar.ludusz.adapter.LandingPageFavoriteBlogAdapter;
import com.cambiar.ludusz.adapter.PBViewPagerAdapter;
import com.cambiar.ludusz.model.Ludusz;
import com.cambiar.ludusz.userrole.PlayerLandPage;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.viewpagerindicator.CirclePageIndicator;
import com.viewpagerindicator.PageIndicator;

import java.lang.ref.WeakReference;

import butterknife.ButterKnife;

public class LandingPageFragmentEventOrganizer extends Fragment implements View.OnClickListener {
    private static final String TAG = LandingPageFragmentEventOrganizer.class.getSimpleName();

    private static WeakReference<LandingPageFragmentEventOrganizer> homeFragmentWeakReference;
    private LandingPageFavoriteBlogAdapter landingPageFavoriteBlogAdapter;

    public static LandingPageFragmentEventOrganizer getInstance() {
        if (homeFragmentWeakReference == null) {
            homeFragmentWeakReference = new WeakReference<>(new LandingPageFragmentEventOrganizer());
        }
        return homeFragmentWeakReference.get();
    }

    public LandingPageFragmentEventOrganizer() {
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

        View rootView = inflater.inflate(R.layout.fragment_landing_page_event_organizer, container, false);
        ButterKnife.bind(getActivity(), rootView);
        setUpToolbar(rootView);
        initViews(rootView);
        // Inflate the layout for this fragment
        return rootView;
    }

    private void initViews(View view) {

        if (Ludusz.getUser() == Ludusz.User.PLAYER) {
            Toast.makeText(getContext(), "Hello PLAYER", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getContext(), Ludusz.getUser().name(), Toast.LENGTH_LONG).show();
        }

        PlayerLandPage landPage = new PlayerLandPage(getContext());


        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewPagerHome);
        viewPager.setAdapter(new PBViewPagerAdapter(getContext(), landPage.getPromotionalBanner()));
        PageIndicator titleIndicator = (CirclePageIndicator) view.findViewById(R.id.page_indicator);
        titleIndicator.setViewPager(viewPager);

        landingPageFavoriteBlogAdapter = new LandingPageFavoriteBlogAdapter(getContext(), landPage.getPlayersFavoriteBlog());
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_favorite_blog);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(landingPageFavoriteBlogAdapter);

        Log.d(TAG, String.format("Total Item %s %s", landPage.getPlayersFavoriteBlog().size(), recyclerView.getChildCount()));
//set listener
        view.findViewById(R.id.ll_blog).setOnClickListener(this);
        view.findViewById(R.id.ll_calendar).setOnClickListener(this);
        view.findViewById(R.id.ll_my_performance).setOnClickListener(this);
        view.findViewById(R.id.ll_near_by).setOnClickListener(this);
        view.findViewById(R.id.ll_share_experiance).setOnClickListener(this);

        view.findViewById(R.id.btn_explore_coach).setOnClickListener(this);
        view.findViewById(R.id.btn_explore_institute).setOnClickListener(this);
        view.findViewById(R.id.btn_explore_player).setOnClickListener(this);
        view.findViewById(R.id.btn_explore_event).setOnClickListener(this);
    }

    public void setUpToolbar(View view) {
        Toolbar mToolbar;
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        mToolbar.setPadding(0, 0, 0, 0);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        // ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(null);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        View mCustomView = getLayoutInflater(null).inflate(R.layout.layout_landing_page_toolbar, null);

        mCustomView.findViewById(R.id.btn_player_location).setOnClickListener(this);
        mCustomView.findViewById(R.id.img_btn_search).setOnClickListener(this);
        mCustomView.findViewById(R.id.img_btn_notification).setOnClickListener(this);

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
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView = (SearchView) searchItem.getActionView();
        MenuItemCompat.setOnActionExpandListener(searchItem, new MenuItemCompat.OnActionExpandListener() {

            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                // Set styles for expanded state here
                if (((MainActivity) getActivity()).getSupportActionBar() != null) {
                    ((MainActivity) getActivity()).getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.RED));
                }
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                // Set styles for collapsed state here
                if (((MainActivity) getActivity()).getSupportActionBar() != null) {
                    ((MainActivity) getActivity()).getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));
                }
                return true;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mGoogleApiClient != null)
            mGoogleApiClient.connect();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    int PLACE_PICKER_REQUEST = 1;
    GoogleApiClient mGoogleApiClient;

    void placePicker() {
        mGoogleApiClient = new GoogleApiClient
                .Builder(getContext())
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                    @Override
                    public void onConnected(Bundle bundle) {

                    }

                    @Override
                    public void onConnectionSuspended(int i) {

                    }
                })
                .addOnConnectionFailedListener(new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(ConnectionResult connectionResult) {

                    }
                })
                .build();
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
        try {
            startActivityForResult(builder.build(getActivity()), PLACE_PICKER_REQUEST);
        } catch (Exception e) {
            Log.e("Exception", e.getMessage());
        }

    }

    /*private void callPlaceAutocompleteActivityIntent() {
        try {
            Intent intent =
                    new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                            .build(getActivity());
            startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
//PLACE_AUTOCOMPLETE_REQUEST_CODE is integer for request code
        } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
            // TODO: Handle the error.
        }

    }*/

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                Place place = PlacePicker.getPlace(data, getContext());
                String toastMsg = String.format("Place: %s", place.getAddress().subSequence(0, 20));
                Toast.makeText(getContext(), toastMsg, Toast.LENGTH_LONG).show();
            }
            /*if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
                if (resultCode == Activity.RESULT_OK) {
                    Place place = PlaceAutocomplete.getPlace(getContext(), data);
                    Log.i(TAG, "Place:" + place.toString());
                } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                    Status status = PlaceAutocomplete.getStatus(getContext(), data);
                    Log.i(TAG, status.getStatusMessage());
                } else if (requestCode == Activity.RESULT_CANCELED) {

                }
            }*/
        }
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_player_location: {
                Toast.makeText(getContext(), "Hello Location", Toast.LENGTH_SHORT).show();
                placePicker();
                //callPlaceAutocompleteActivityIntent();
                break;
            }
            case R.id.img_btn_search: {
                Toast.makeText(getContext(), "Hello Search", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.img_btn_notification: {
                Toast.makeText(getContext(), "Hello Notification", Toast.LENGTH_SHORT).show();
                break;
            }

            case R.id.ll_share_experiance: {
                Toast.makeText(getContext(), "Hello ll_share_experiance", Toast.LENGTH_SHORT).show();
                break;

            }
            case R.id.ll_near_by: {
                Toast.makeText(getContext(), "Hello ll_near_by", Toast.LENGTH_SHORT).show();
                break;

            }
            case R.id.ll_my_performance: {
                Toast.makeText(getContext(), "Hello ll_my_performance", Toast.LENGTH_SHORT).show();
                break;

            }
            case R.id.ll_calendar: {
                Toast.makeText(getContext(), "Hello ll_calendar", Toast.LENGTH_SHORT).show();
                break;

            }
            case R.id.ll_blog: {
                Toast.makeText(getContext(), "Hello ll_blog", Toast.LENGTH_SHORT).show();
                break;

            }
            case R.id.btn_explore_coach: {
                //Toast.makeText(getContext(), "Hello btn_explore_coach", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), SearchActivity.class);
                intent.putExtra("tab_id", 1);
                startActivity(intent);
                break;

            }
            case R.id.btn_explore_institute: {
                //Toast.makeText(getContext(), "Hello btn_explore_institute", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), SearchActivity.class);
                intent.putExtra("tab_id", 5);
                startActivity(intent);
                break;

            }
            case R.id.btn_explore_player: {
                //Toast.makeText(getContext(), "Hello btn_explore_player", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), SearchActivity.class);
                intent.putExtra("tab_id", 6);
                startActivity(intent);
                break;

            }
            case R.id.btn_explore_event: {
                //Toast.makeText(getContext(), "Hello btn_explore_event", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), SearchActivity.class);
                intent.putExtra("tab_id", 2);
                startActivity(intent);
                break;

            }

            default:
                break;
        }
    }
}
