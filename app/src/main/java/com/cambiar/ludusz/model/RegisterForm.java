package com.cambiar.ludusz.model;

/**
 * Created by vibes on 6/5/16.
 */
public class RegisterForm {
    private String userName;
    private String userEmail;
    private String userCountry;
    private String userMobile;
    private String userPassword;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserCountry() {
        return userCountry;
    }

    public void setUserCountry(String userCountry) {
        this.userCountry = userCountry;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Override
    public String toString() {
        return "{ UserName="
                + userName
                + ",UserPassword="
                + userPassword
                + ",UserEmail="
                + userEmail
                + ",UserCountry="
                + userCountry
                + "UserMobile=" + userMobile
                + "userPassword=" + userPassword;
    }
}
