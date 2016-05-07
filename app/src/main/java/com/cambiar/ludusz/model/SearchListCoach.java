package com.cambiar.ludusz.model;

import java.util.List;

/**
 * Created by vibes on 7/5/16.
 */
public class SearchListCoach extends SearchBase {
    private String coachingOffered;
    private String offerCoachingTo;
    private String coachingFee;
    private String coachingExperience;

    public String getCoachingOffered() {
        return coachingOffered;
    }

    public void setCoachingOffered(String coachingOffered) {
        this.coachingOffered = coachingOffered;
    }

    public String getOfferCoachingTo() {
        return offerCoachingTo;
    }

    public void setOfferCoachingTo(String offerCoachingTo) {
        this.offerCoachingTo = offerCoachingTo;
    }

    public String getCoachingFee() {
        return coachingFee;
    }

    public void setCoachingFee(String coachingFee) {
        this.coachingFee = coachingFee;
    }

    public String getCoachingExperience() {
        return coachingExperience;
    }

    public void setCoachingExperience(String coachingExperience) {
        this.coachingExperience = coachingExperience;
    }

    @Override
    public void setGetSearchResultImageGalaryURLs(List<String> getSearchResultImageGalaryURLs) {
        super.setGetSearchResultImageGalaryURLs(getSearchResultImageGalaryURLs);
    }

    @Override
    public List<String> getGetSearchResultImageGalaryURLs() {
        return super.getGetSearchResultImageGalaryURLs();
    }

    @Override
    public void setGetSearchResultConnectedPeopleCount(int getSearchResultConnectedPeopleCount) {
        super.setGetSearchResultConnectedPeopleCount(getSearchResultConnectedPeopleCount);
    }

    @Override
    public int getGetSearchResultConnectedPeopleCount() {
        return super.getGetSearchResultConnectedPeopleCount();
    }

    @Override
    public void setSearchResultRatingStar(float searchResultRatingStar) {
        super.setSearchResultRatingStar(searchResultRatingStar);
    }

    @Override
    public float getSearchResultRatingStar() {
        return super.getSearchResultRatingStar();
    }

    @Override
    public void setSearchResultReviews(List<Reviews> searchResultReviews) {
        super.setSearchResultReviews(searchResultReviews);
    }

    @Override
    public List<Reviews> getSearchResultReviews() {
        return super.getSearchResultReviews();
    }

    @Override
    public void setSearchResultEmailAddress(String searchResultEmailAddress) {
        super.setSearchResultEmailAddress(searchResultEmailAddress);
    }

    @Override
    public String getSearchResultEmailAddress() {
        return super.getSearchResultEmailAddress();
    }

    @Override
    public void setSearchResultContactNumber(String searchResultContactNumber) {
        super.setSearchResultContactNumber(searchResultContactNumber);
    }

    @Override
    public String getSearchResultContactNumber() {
        return super.getSearchResultContactNumber();
    }

    @Override
    public void setSearchResultLikes(int searchResultLikes) {
        super.setSearchResultLikes(searchResultLikes);
    }

    @Override
    public int getSearchResultLikes() {
        return super.getSearchResultLikes();
    }

    @Override
    public void setSearchResultProfilePicture(String searchResultProfilePicture) {
        super.setSearchResultProfilePicture(searchResultProfilePicture);
    }

    @Override
    public String getSearchResultProfilePicture() {
        return super.getSearchResultProfilePicture();
    }

    @Override
    public void setSearchResultLocation(String searchResultLocation) {
        super.setSearchResultLocation(searchResultLocation);
    }

    @Override
    public String getSearchResultLocation() {
        return super.getSearchResultLocation();
    }

    @Override
    public void setSearchResultAddress(String searchResultAddress) {
        super.setSearchResultAddress(searchResultAddress);
    }

    @Override
    public String getSearchResultAddress() {
        return super.getSearchResultAddress();
    }

    @Override
    public void setSearchResultId(String searchResultId) {
        super.setSearchResultId(searchResultId);
    }

    @Override
    public String getSearchResultId() {
        return super.getSearchResultId();
    }

    @Override
    public void setSearchResultName(String searchResultName) {
        super.setSearchResultName(searchResultName);
    }

    @Override
    public String getSearchResultName() {
        return super.getSearchResultName();
    }
}
