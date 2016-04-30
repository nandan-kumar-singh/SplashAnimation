package com.cambiar.ludusz.interfaces;

import java.io.Serializable;

/**
 * Created by vibes on 16/4/16.
 */
public interface Banner extends Serializable {
    String getBannerHeader();

    String getBannerDescription();

    String getBannerImageUrl();

    String getBannerStartDate();

    String getBannerEndDate();
}
