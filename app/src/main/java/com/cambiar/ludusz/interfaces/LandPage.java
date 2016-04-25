package com.cambiar.ludusz.interfaces;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;
import java.util.List;

/**
 * Created by vibes on 16/4/16.
 * </br>
 * <b>interface</b> LandPage is the <i>root</i> class of Landing page, has the all main features of landing page.
 */
public interface LandPage extends Serializable{
    List<Banner> getPromotionalBanner();

    String getProfileImageUrl();

    String getUName();

    /*in LatLong format*/
    LatLng getLocation();

    String getAddress();
}
