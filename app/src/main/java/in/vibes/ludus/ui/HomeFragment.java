package in.vibes.ludus.ui;

/**
 * Created by vibes on 29/10/15.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.viewpagerindicator.CirclePageIndicator;
import com.viewpagerindicator.PageIndicator;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.vibes.ludus.R;
import in.vibes.ludus.model.Celebrity;

public class HomeFragment extends Fragment {
    private static final String TAG = "HomeFragment";
    private Toolbar mToolbar;
    private static HomeFragment homeFragment;
    private WeakReference<HomeViewPagerAdapter> viewPagerAdapterWeakReference;
    private LinearLayoutManager layoutManager;
    @Bind(R.id.viewPagerHome)
    ViewPager viewPagerHome;
    @Bind(R.id.recyclerViewHome)
    RecyclerView recyclerViewHome;


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
        ButterKnife.bind(this, rootView);
        setUpToolbar(rootView);
        initInstances(rootView);
        // Inflate the layout for this fragment
        return rootView;
    }

    private void initInstances(View view) {
        if (viewPagerAdapterWeakReference == null) {
            viewPagerAdapterWeakReference = new WeakReference<>(new HomeViewPagerAdapter());
        }
        viewPagerHome.setAdapter(viewPagerAdapterWeakReference.get());

        PageIndicator titleIndicator = (CirclePageIndicator) view.findViewById(R.id.titles);
        titleIndicator.setViewPager(viewPagerHome);

        layoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewHome.setLayoutManager(layoutManager);
        recyclerViewHome.setAdapter(new HomeRecyclerViewAdapter(getContext()));

        recyclerViewHome.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                viewPagerHome.setRotationX(dx);
            }

    });
                //placePicker();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mGoogleApiClient != null)
            mGoogleApiClient.connect();
    }

    @Override
    public void onStop() {
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
        super.onStop();
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

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                Place place = PlacePicker.getPlace(getContext(), data);
                String toastMsg = String.format("Place: %s", place.getName());
                Toast.makeText(getContext(), toastMsg, Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(getActivity(), SettingsActivity.class).setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
            getActivity().overridePendingTransition(R.anim.right_to_left_animation, R.anim.left_to_right_animation);

        }
        return true;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_home, menu);
        super.onCreateOptionsMenu(menu, inflater);
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

    //adapter for home ViewPager
    private class HomeViewPagerAdapter extends PagerAdapter {
        public HomeViewPagerAdapter() {

        }

        @Override
        public int getCount() {
            return 10;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView;
            imageView = new ImageView(getContext());
            imageView.setBackgroundColor(Color.BLUE + Color.GRAY);
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_people));
            ((ViewPager) container).addView(imageView);
            return imageView;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((ImageView) object);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView((ImageView) object);
        }
    }

    class HomeRecyclerViewAdapter extends RecyclerView.Adapter<HomeRecyclerViewAdapter.MyViewHolder> {

        private LayoutInflater inflater;
        private Context context;

        public HomeRecyclerViewAdapter(Context context) {
            this.context = context;
            inflater = LayoutInflater.from(this.context);

        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.layout_recyclar_view, null, false);
            MyViewHolder holder = new MyViewHolder(view);

            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {


        }

        @Override
        public void onViewRecycled(MyViewHolder holder) {
            super.onViewRecycled(holder);
            Log.d(TAG, "onViewRecycled");
        }

        @Override
        public int getItemCount() {
            return 10;
        }


        class MyViewHolder extends RecyclerView.ViewHolder {

            public MyViewHolder(View itemView) {
                super(itemView);


            }
        }
    }

    public List<Celebrity> readJsonStream(InputStream in) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        Gson gson = new Gson();
        List<Celebrity> messages = new ArrayList<>();
        reader.beginArray();
        while (reader.hasNext()) {
            Celebrity message = gson.fromJson(reader, Celebrity.class);
            messages.add(message);
        }
        reader.endArray();
        reader.close();
        return messages;
    }
}

