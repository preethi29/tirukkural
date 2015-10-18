package com.android.tirukkural.models;

public class Couplet {
    private  int _id;
    private String line1;
    private String line2;
    private String enMeaning;
    private String taMeaning;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getEnMeaning() {
        return enMeaning;
    }

    public void setEnMeaning(String enMeaning) {
        this.enMeaning = enMeaning;
    }

    public String getTaMeaning() {
        return taMeaning;
    }

    public void setTaMeaning(String taMeaning) {
        this.taMeaning = taMeaning;
    }
}
