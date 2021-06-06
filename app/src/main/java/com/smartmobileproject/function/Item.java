package com.smartmobileproject.function;

public class Item {

    String imgPath;
    //longtitude;
    // latitude;
    String email;

    public Item(String imgPath, String email) {
        this.imgPath = imgPath;
        this.email = email;
    }

    public String getImgPath() {

        return imgPath;
    }
    public void setImgPath(String imgPath) {

        this.imgPath = imgPath;
    }


    public String getEmail() {

        return email;
    }
    public void setEmail(String email) {

        this.email = email;
    }

}