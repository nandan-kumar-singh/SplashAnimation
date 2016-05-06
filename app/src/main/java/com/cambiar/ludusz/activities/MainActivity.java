package com.cambiar.ludusz.activities;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.transition.Fade;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;

import com.cambiar.ludusz.R;
import com.cambiar.ludusz.fragments.DrawerFragment;
import com.cambiar.ludusz.fragments.GraphFragment;
import com.cambiar.ludusz.fragments.LandingPageFragmentCoach;
import com.cambiar.ludusz.fragments.LandingPageFragmentCoordinator;
import com.cambiar.ludusz.fragments.LandingPageFragmentEventOrganizer;
import com.cambiar.ludusz.fragments.LandingPageFragmentPlayer;
import com.cambiar.ludusz.fragments.ProfileFragment;
import com.cambiar.ludusz.model.Ludusz;

public class MainActivity extends BaseActivity implements DrawerFragment.FragmentDrawerListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    public static DrawerFragment drawerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setUp drawer layout
        Ludusz.setUser(Ludusz.User.PLAYER);
        drawerFragment = (DrawerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setDrawerListener(this);
        displayView(0, Ludusz.getUser());
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position, Ludusz.getUser());
    }

    public void displayView(int position, Ludusz.User user) {
        Fragment fragment = null;
        switch (user) {
            case PLAYER: {
                switch (position) {
                    case 0:
                        fragment = LandingPageFragmentPlayer.getInstance();
                        break;
                    case 1:
                        fragment = ProfileFragment.getInstance();
                        break;
                    case 2:
                        fragment = GraphFragment.getInstance();
                        break;
                    default:
                        break;
                }
                break;
            }
            case COACH: {
                switch (position) {
                    case 0:
                        fragment = LandingPageFragmentCoach.getInstance();
                        break;
                    default:
                        break;
                }
                break;
            }
            case CO_ORDINATOR: {
                switch (position) {
                    case 0:
                        fragment = LandingPageFragmentCoordinator.getInstance();
                        break;
                    default:
                        break;
                }
                break;
            }
            case EVENT_ORGANISER: {
                switch (position) {
                    case 0:
                        fragment = LandingPageFragmentEventOrganizer.getInstance();
                        break;
                    default:
                        break;
                }
                break;
            }
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
            MainActivity.this.finish();
            /*AlertDialog.Builder builder = new AlertDialog.Builder(this);
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
*/
        }

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleClickToExit = !doubleClickToExit;
            }
        }, 700);

        //check if the current fragment is LandingPageFragmentPlayer or not
        switch (Ludusz.getUser())
        {
            case PLAYER:{
                if (LandingPageFragmentPlayer.getInstance().isVisible()) {
                    //Toast.makeText(this,"Hello LandingPageFragmentPlayer",Toast.LENGTH_SHORT).show();
                } else {
                    getSupportFragmentManager().popBackStack();
                }
                break;
            }
            case COACH:{
                if (LandingPageFragmentCoach.getInstance().isVisible()) {
                    //Toast.makeText(this,"Hello LandingPageFragmentPlayer",Toast.LENGTH_SHORT).show();
                } else {
                    getSupportFragmentManager().popBackStack();
                }
                break;
            }
            case CO_ORDINATOR:{
                if (LandingPageFragmentCoordinator.getInstance().isVisible()) {
                    //Toast.makeText(this,"Hello LandingPageFragmentPlayer",Toast.LENGTH_SHORT).show();
                } else {
                    getSupportFragmentManager().popBackStack();
                }
                break;
            }
            case EVENT_ORGANISER:{
                if (LandingPageFragmentEventOrganizer.getInstance().isVisible()) {
                    //Toast.makeText(this,"Hello LandingPageFragmentPlayer",Toast.LENGTH_SHORT).show();
                } else {
                    getSupportFragmentManager().popBackStack();
                }
                break;
            }
            default:
                break;
        }
        doubleClickToExit = !doubleClickToExit;
    }

}
