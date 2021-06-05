package com.smartmobileproject.function;

public class Item {

    String imgPath;
    String longtitude;
    String latitude;
    String email;

    public Item() {
    }

    public Item(String imgPath, String longtitude, String latitude, String email) {
        this.imgPath = imgPath;
        this.longtitude = longtitude;
        this.latitude = latitude;
        this.email = email;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(String longtitude) {
        this.longtitude = longtitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}