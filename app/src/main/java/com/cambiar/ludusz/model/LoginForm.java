package com.cambiar.ludusz.model;

/**
 * Created by vibes on 25/4/16.
 */
public class LoginForm {
    private int status;

    private Data data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "[status = " + status + ", data = " + data + "]";
    }
}
