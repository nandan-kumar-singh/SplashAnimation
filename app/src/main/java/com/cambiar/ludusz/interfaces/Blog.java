package com.cambiar.ludusz.interfaces;

import java.io.Serializable;

/**
 * Created by vibes on 16/4/16.
 */
public interface Blog extends Serializable{
    String getBlogHeader();

    String getBlogDescription();

    String getBlogImageUrl();

    String getBlogCreationDate();

    int getBlogLikeCount();
}
