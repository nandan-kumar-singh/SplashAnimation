package com.cambiar.ludusz.fragments;

/**
 * Created by vibes on 29/10/15.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cambiar.ludusz.R;
import com.cambiar.ludusz.adapter.NavigationDrawerAdapter;
import com.cambiar.ludusz.model.Ludusz;
import com.cambiar.ludusz.model.NavDrawerItem;
import com.cambiar.ludusz.model.UserData;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.List;


public class DrawerFragment extends Fragment {

    private static String TAG = DrawerFragment.class.getSimpleName();
    private static String[] titles = null;
    private static int[] icons = null;
    private Ludusz ludusz;

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    //private

    private View containerView;
    private ImageView imageViewuserPic;
    private TextView textViewEmail, textViewName;
    private FragmentDrawerListener drawerListener;

    public DrawerFragment() {

    }

    public static List<NavDrawerItem> getData() {
        List<NavDrawerItem> data = new ArrayList<>();
        // preparing navigation drawer items
        for (int i = 0; i < titles.length; i++) {
            NavDrawerItem navItem = new NavDrawerItem();
            navItem.setTitle(titles[i]);
            navItem.setIcon(icons[i]);
            data.add(navItem);
        }
        return data;
    }

    public void setDrawerListener(FragmentDrawerListener listener) {
        this.drawerListener = listener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // drawer labels
        titles = getActivity().getResources().getStringArray(R.array.drawer_layout_labels);
        icons = new int[]{R.mipmap.ic_action_share,
                R.mipmap.ic_action_share,
                R.mipmap.ic_action_blog,
                R.mipmap.ic_action_calendar,
                R.mipmap.ic_action_share,
                R.mipmap.ic_action_share,
                R.mipmap.ic_action_share,
                R.mipmap.ic_action_share,
                R.mipmap.ic_action_share};
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflating view layout
        View layout = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        imageViewuserPic = (ImageView) layout.findViewById(R.id.iv_user_profile_pic);

        ludusz = (Ludusz) getContext().getApplicationContext();
        UserData userData = ludusz.getUserData();
        textViewName = ((TextView) layout.findViewById(R.id.tv_user_name));
        textViewEmail = ((TextView) layout.findViewById(R.id.tv_user_email));

        Picasso.with(getContext()).load(userData.getUserPic()).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                imageViewuserPic.setImageBitmap(bitmap);
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });


        RecyclerView recyclerView = (RecyclerView) layout.findViewById(R.id.drawerList);

        NavigationDrawerAdapter adapter = new NavigationDrawerAdapter(getActivity(), getData());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new

                LinearLayoutManager(getActivity()

        ));
        recyclerView.addOnItemTouchListener(new

                RecyclerTouchListener(getActivity(), recyclerView,

                new

                        ClickListener() {
                            @Override
                            public void onClick(View view, int position) {
                                drawerListener.onDrawerItemSelected(view, position);
                                mDrawerLayout.closeDrawer(containerView);
                            }

                            @Override
                            public void onLongClick(View view, int position) {
                                String title = "Click to go to this screen";

                            }
                        }

        ));

        return layout;
    }

    public void setUp(int fragmentId, DrawerLayout drawerLayout, final Toolbar toolbar) {
        containerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActivity().invalidateOptionsMenu();
                textViewName.setText(ludusz.getUserData().getUserName() + "");
                textViewEmail.setText(ludusz.getUserData().getUserMail() + "");
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                toolbar.setAlpha(1 - slideOffset / 2);


            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });

    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    public interface FragmentDrawerListener {
        void onDrawerItemSelected(View view, int position);
    }

    @Override
    public void onStart() {
        updateData();
        super.onStart();
    }

    private void updateData() {

    }

    static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener) {
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
