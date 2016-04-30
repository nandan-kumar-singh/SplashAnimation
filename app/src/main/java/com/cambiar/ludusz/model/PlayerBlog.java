package com.cambiar.ludusz.model;

import com.cambiar.ludusz.interfaces.Blog;

/**
 * Created by vibes on 28/4/16.
 */
public class PlayerBlog implements Blog {
    private String blogHeader;
    private String blogDescription;
    private String blogImageUrl;
    private String blogCreationDate;
    private int blogLikeCount;

    @Override
    public String getBlogHeader() {
        return blogHeader;
    }

    public void setBlogHeader(String blogHeader) {
        this.blogHeader = blogHeader;
    }

    @Override
    public String getBlogDescription() {
        return blogDescription;
    }

    public void setBlogDescription(String blogDescription) {
        this.blogDescription = blogDescription;
    }

    @Override
    public String getBlogImageUrl() {
        return blogImageUrl;
    }

    public void setBlogImageUrl(String blogBannerImageUrl) {
        this.blogImageUrl = blogBannerImageUrl;
    }

    @Override
    public String getBlogCreationDate() {
        return blogCreationDate;
    }

    public void setBlogCreationDate(String blogCreationDate) {
        this.blogCreationDate = blogCreationDate;
    }

    @Override
    public int getBlogLikeCount() {
        return blogLikeCount;
    }

    public void setBlogLikeCount(int blogLikeCount) {
        this.blogLikeCount = blogLikeCount;
    }


}
