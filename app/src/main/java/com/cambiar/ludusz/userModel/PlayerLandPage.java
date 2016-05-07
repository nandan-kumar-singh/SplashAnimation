package com.cambiar.ludusz.userModel;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.cambiar.ludusz.interfaces.Banner;
import com.cambiar.ludusz.interfaces.Blog;
import com.cambiar.ludusz.interfaces.LandPage;
import com.cambiar.ludusz.model.NewsFeed;
import com.cambiar.ludusz.model.PlayerBlog;
import com.cambiar.ludusz.model.PromotionalBanner;
import com.cambiar.ludusz.util.AndroidUtil;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by vibes on 16/4/16.
 */
public class PlayerLandPage implements LandPage {
    private Context context;
    private List<Banner> promotionalBanners;
    private String profileImageUrl;
    private String uName;
    private LatLng uLocation;
    private String address;


    public PlayerLandPage(@NonNull Context context) {
        this.context = context;
        promotionalBanners = new ArrayList<>();
        profileImageUrl = new String();
        uName = new String();
        uLocation = new LatLng(2121212.121, 12121.122);
        address = new String();
    }

    @Nullable
    @Override
    public List<Banner> getPromotionalBanner() {
        Banner banner1;
        banner1 = (PromotionalBanner) AndroidUtil.getObject(context, "banner1");
        if (banner1 != null) {
            promotionalBanners.add(banner1);
        } else {
            // banner1=new PromotionalBanner("Header1", "Description New", "http://www.emiratesrobotics.me/images/menu-img-4.jpg", "StartDate", "EndDate");
        }
        Banner banner2 = new PromotionalBanner("Header2", "Description2", "http://www.emiratesrobotics.me/images/menu-img-4.jpg", "StartDate", "EndDate");
        Banner banner3 = new PromotionalBanner("Header3", "Description3", "http://www.emiratesrobotics.me/images/menu-img-4.jpg", "StartDate", "EndDate");
        Banner banner4 = new PromotionalBanner("Header4", "Description4", "http://www.emiratesrobotics.me/images/menu-img-4.jpg", "StartDate", "EndDate");
        Banner banner5 = new PromotionalBanner("Header5", "Description5", "http://www.emiratesrobotics.me/images/menu-img-4.jpg", "StartDate", "EndDate");

        promotionalBanners.add(banner2);
        promotionalBanners.add(banner3);
        promotionalBanners.add(banner4);
        promotionalBanners.add(banner5);
        /*if (AndroidUtil.saveObject(context, banner1, "banner1")) {
            Toast.makeText(context, "Data Saved!!", Toast.LENGTH_LONG).show();
        } else
            Toast.makeText(context, "Error!!", Toast.LENGTH_LONG).show();*/
        return promotionalBanners;
    }

    @Override
    public String getProfileImageUrl() {
        return null;

    }

    @Override
    public String getPlayerName() {
        return null;
    }

    @Override
    public LatLng getLocation() {
        return null;
    }

    @Override
    public String getAddress() {
        return null;
    }

    public List<NewsFeed> getLocalNewsFeeds() {
        return null;
    }

    public List<NewsFeed> getGlobalNewsFeeds() {
        return null;
    }

    public List<Blog> getPlayersFavoriteBlog() {

        PlayerBlog playerBlog = new PlayerBlog();
        playerBlog.setBlogCreationDate(new Date().toString());
        playerBlog.setBlogHeader("Hello Nandan!!");
        playerBlog.setBlogDescription("Hello Mr. Nandan!, welcome to yor first blog.");

        List<Blog> blogs = new ArrayList<>();
        blogs.add(playerBlog);
        blogs.add(playerBlog);
        blogs.add(playerBlog);
        blogs.add(playerBlog);
        blogs.add(playerBlog);
        blogs.add(playerBlog);
        blogs.add(playerBlog);
        blogs.add(playerBlog);
        blogs.add(playerBlog);
        blogs.add(playerBlog);

        return blogs;
    }
}
