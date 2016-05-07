package com.cambiar.ludusz.model;

/**
 * Created by vibes on 6/5/16.
 */
public class Data {
    private String user_name;

    private String user_type;

    private String user_key;

    private String tag;

    private String user_mobile;

    private String user_email;

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getUser_key() {
        return user_key;
    }

    public void setUser_key(String user_key) {
        this.user_key = user_key;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getUser_mobile() {
        return user_mobile;
    }

    public void setUser_mobile(String user_mobile) {
        this.user_mobile = user_mobile;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    @Override
    public String toString() {
        return "[user_name = " + user_name + ", user_type = " + user_type + ", user_key = " + user_key + ", tag = " + tag + ", user_mobile = " + user_mobile + ", user_email = " + user_email + "]";
    }
}
