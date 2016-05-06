package com.cambiar.ludusz.activities;

/**
 * Created by vibes on 29/10/15.
 */

import android.app.SearchManager;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.cambiar.ludusz.R;
import com.cambiar.ludusz.adapter.SearchViewPagerAdapter;
import com.cambiar.ludusz.fragments.SearchListFragment;

public class SearchActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = SearchActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initViews();
        setUpToolBar();
    }

    @Override
    public boolean onNavigateUp() {
        this.finish();
        return true;

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
        return true;
    }
}

