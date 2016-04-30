package com.cambiar.ludusz.model;

import com.cambiar.ludusz.interfaces.Banner;

/**
 * Created by vibes on 16/4/16.
 */
public class PromotionalBanner implements Banner {
    private String bHeader;
    private String bDescription;
    private String bImageUrl;
    private String bStartDate;
    private String bEndDate;

    public PromotionalBanner(String bHeader, String bDescription, String bImageUrl, String bStartDate, String bEndDate) {
        this.bHeader = bHeader;
        this.bDescription = bDescription;
        this.bImageUrl = bImageUrl;
        this.bStartDate = bStartDate;
        this.bEndDate = bEndDate;
    }

    @Override
    public String getBannerHeader() {
        return bHeader;
    }

    public void setBHeader(String bHeader) {
        this.bHeader = bHeader;
    }

    @Override
    public String getBannerDescription() {
        return bDescription;
    }

    public void setBDescription(String bDescription) {
        this.bDescription = bDescription;
    }

    @Override
    public String getBannerImageUrl() {
        return bImageUrl;
    }

    public void setBImageUrl(String bImageUrl) {
        this.bImageUrl = bImageUrl;
    }

    @Override
    public String getBannerStartDate() {
        return bStartDate;
    }

    public void setBStartDate(String bStartDate) {
        this.bStartDate = bStartDate;
    }

    @Override
    public String getBannerEndDate() {
        return bEndDate;
    }

    public void setbEndDate(String bEndDate) {
        this.bEndDate = bEndDate;
    }
}
