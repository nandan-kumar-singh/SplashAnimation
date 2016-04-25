package com.cambiar.ludusz.activities;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.transition.Fade;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;

import com.cambiar.ludusz.R;
import com.cambiar.ludusz.fragments.DrawerFragment;
import com.cambiar.ludusz.fragments.GraphFragment;
import com.cambiar.ludusz.fragments.HomeFragment;
import com.cambiar.ludusz.fragments.ProfileFragment;
import com.cambiar.ludusz.model.Ludusz;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends BaseActivity implements DrawerFragment.FragmentDrawerListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    public static DrawerFragment drawerFragment;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Ludusz.setUser(Ludusz.User.PLAYER);
        //setUp drawer layout
        drawerFragment = (DrawerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setDrawerListener(this);
        displayView(0);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
    }

    public void displayView(int position) {
        Fragment fragment = null;

        String title = getString(R.string.app_name);
        switch (position) {
            case 0:
                fragment = HomeFragment.getInstance();
                break;
            case 1:
                fragment = ProfileFragment.getInstance();
                title = getString(R.string.nav_item_my_profile);
                break;
            case 2:
                fragment = GraphFragment.getInstance();
                title = getString(R.string.nav_item_my_profile);
                break;
            default:
                break;
        }

        if (fragment != null) {
            //for slide animation on fragment transition
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                // fragment.setEnterTransition(new Slide(Gravity.LEFT).setDuration(800));
                fragment.setEnterTransition(new Fade(Gravity.LEFT).setDuration(300));
                fragment.setExitTransition(new Slide(Gravity.RIGHT).setDuration(300));
            }

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.addToBackStack(null);
            //fragmentTransaction.setCustomAnimations(R.anim.zoom_in, R.anim.zoom_exit);
            fragmentTransaction.commit();

            // set the toolbar title
            // getSupportActionBar().setTitle(title);
        }
    }

    boolean doubleClickToExit = false;

    @Override
    public void onBackPressed() {

        if (doubleClickToExit) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Exit").setMessage("Do you want to exit?").setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            }).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    MainActivity.this.finish();
                }
            }).create().show();

        }

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleClickToExit = !doubleClickToExit;
            }
        }, 700);

        //check if the current fragment is HomeFragment or not
        if (HomeFragment.getInstance().isVisible()) {
            //Toast.makeText(this,"Hello HomeFragment",Toast.LENGTH_SHORT).show();
        } else {
            getSupportFragmentManager().popBackStack();
        }
        doubleClickToExit = !doubleClickToExit;
    }

}