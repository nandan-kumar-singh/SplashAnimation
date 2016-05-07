package com.cambiar.ludusz.model;

/**
 * Created by vibes on 7/5/16.
 */
public class SearchFilter {
    private String category;

    private String location;
    private String rating;
    private String fee;

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "ClassPojo [category = " + category + ", location = " + location + "]";
    }
}
