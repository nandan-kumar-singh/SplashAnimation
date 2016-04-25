package com.cambiar.ludusz.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cambiar.ludusz.R;
import com.cambiar.ludusz.interfaces.Banner;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

/**
 * Created by vibes on 12/4/16.
 */

public class PBViewPagerAdapter extends PagerAdapter {
    private static final String TAG = PBViewPagerAdapter.class.getSimpleName();
    private Context context;
    private List<Banner> bannerList = Collections.EMPTY_LIST;

    public PBViewPagerAdapter(Context context, List<Banner> promotionalBanners) {
        this.context = context;
        this.bannerList = promotionalBanners;
    }

    @Override
    public int getCount() {
        return bannerList.size();
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.layout_promotional_banner, container, false);

        ((TextView) layout.findViewById(R.id.tv_banner_header)).setText(bannerList.get(position).getBHeader());
        ((TextView) layout.findViewById(R.id.tv_banner_description)).setText(bannerList.get(position).getBDescription());

        ((TextView) layout.findViewById(R.id.tv_banner_description)).setText(bannerList.get(position).getBDescription());

        Picasso.with(context).load(bannerList.get(position).getBImageUrl()).into(((ImageView) layout.findViewById(R.id.iv_banner_image)));
        container.addView(layout);
        return layout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}

