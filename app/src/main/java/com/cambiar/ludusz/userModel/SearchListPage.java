package com.cambiar.ludusz.userModel;

import android.content.Context;

import com.cambiar.ludusz.model.SearchBase;
import com.cambiar.ludusz.model.SearchFilter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vibes on 7/5/16.
 */
public class SearchListPage {
    private Context context;

    public SearchListPage(Context context) {
        this.context = context;
    }

    public List<SearchBase> getCoachSearchList(String searchTag, SearchFilter searchFilter) {
        List<SearchBase> searchListCoaches = new ArrayList<>();
        SearchBase searchBase = new SearchBase();

        searchBase.setSearchResultName("Nandan Kumar Singh");
        searchBase.setSearchResultAddress("R-143 Vani Vihar, Uttam Nagar, New Delhi-59");
        searchBase.setSearchResultProfilePicture("https://media.licdn.com/mpr/mpr/shrink_100_100/AAEAAQAAAAAAAAaFAAAAJDY3NTZlNTRhLWEyZWUtNGQ5OC1iNTkwLTQ0ZmY3ZGI0MDRlMg.jpg");
        searchBase.setCoachingOffered("Cricket, Football");
        searchBase.setOfferCoachingTo("Beginner");
        searchBase.setCoachingFee("500 INR");
        searchBase.setSearchResultContactNumber("+917503138215");
        searchBase.setSearchResultEmailAddress("nandan.singh@vibescom.in");
        searchBase.setSearchResultLikes(230);


        searchListCoaches.add(searchBase);
        searchListCoaches.add(searchBase);
        searchListCoaches.add(searchBase);
        searchListCoaches.add(searchBase);
        searchListCoaches.add(searchBase);
        searchListCoaches.add(searchBase);
        searchListCoaches.add(searchBase);
        searchListCoaches.add(searchBase);
        return searchListCoaches;
    }
}
