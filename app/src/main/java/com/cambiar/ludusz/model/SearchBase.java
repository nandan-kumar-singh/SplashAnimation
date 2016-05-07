package com.cambiar.ludusz.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by vibes on 6/5/16.
 * Base class for search Coach, player, Events, Co-ordinator
 */
public class SearchBase implements Serializable {

    private String searchResultName;

    private String searchResultId;

    private String searchResultAddress;

    private String searchResultLocation;

    private String searchResultProfilePicture;

    private int searchResultLikes;

    private String searchResultContactNumber;

    private String searchResultEmailAddress;

    private List<Reviews> searchResultReviews;

    private float searchResultRatingStar;

    private int getSearchResultConnectedPeopleCount;

    private List<String> getSearchResultImageGalaryURLs;

    public String getSearchResultName() {
        return searchResultName;
    }

    public void setSearchResultName(String searchResultName) {
        this.searchResultName = searchResultName;
    }

    public String getSearchResultId() {
        return searchResultId;
    }

    public void setSearchResultId(String searchResultId) {
        this.searchResultId = searchResultId;
    }

    public String getSearchResultAddress() {
        return searchResultAddress;
    }

    public void setSearchResultAddress(String searchResultAddress) {
        this.searchResultAddress = searchResultAddress;
    }

    public String getSearchResultLocation() {
        return searchResultLocation;
    }

    public void setSearchResultLocation(String searchResultLocation) {
        this.searchResultLocation = searchResultLocation;
    }

    public String getSearchResultProfilePicture() {
        return searchResultProfilePicture;
    }

    public void setSearchResultProfilePicture(String searchResultProfilePicture) {
        this.searchResultProfilePicture = searchResultProfilePicture;
    }

    public int getSearchResultLikes() {
        return searchResultLikes;
    }

    public void setSearchResultLikes(int searchResultLikes) {
        this.searchResultLikes = searchResultLikes;
    }

    public String getSearchResultContactNumber() {
        return searchResultContactNumber;
    }

    public void setSearchResultContactNumber(String searchResultContactNumber) {
        this.searchResultContactNumber = searchResultContactNumber;
    }

    public String getSearchResultEmailAddress() {
        return searchResultEmailAddress;
    }

    public void setSearchResultEmailAddress(String searchResultEmailAddress) {
        this.searchResultEmailAddress = searchResultEmailAddress;
    }

    public List<Reviews> getSearchResultReviews() {
        return searchResultReviews;
    }

    public void setSearchResultReviews(List<Reviews> searchResultReviews) {
        this.searchResultReviews = searchResultReviews;
    }

    public float getSearchResultRatingStar() {
        return searchResultRatingStar;
    }

    public void setSearchResultRatingStar(float searchResultRatingStar) {
        this.searchResultRatingStar = searchResultRatingStar;
    }

    public int getGetSearchResultConnectedPeopleCount() {
        return getSearchResultConnectedPeopleCount;
    }

    public void setGetSearchResultConnectedPeopleCount(int getSearchResultConnectedPeopleCount) {
        this.getSearchResultConnectedPeopleCount = getSearchResultConnectedPeopleCount;
    }

    public List<String> getGetSearchResultImageGalaryURLs() {
        return getSearchResultImageGalaryURLs;
    }

    public void setGetSearchResultImageGalaryURLs(List<String> getSearchResultImageGalaryURLs) {
        this.getSearchResultImageGalaryURLs = getSearchResultImageGalaryURLs;
    }
}