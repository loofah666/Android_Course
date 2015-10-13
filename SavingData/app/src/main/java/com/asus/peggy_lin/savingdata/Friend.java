package com.asus.peggy_lin.savingdata;

/**
 * Created by Peggy_Lin on 2015/10/13.
 */
public class Friend {
    public String name, phone, fav;
    public Integer gender;

    public Friend(String name, Integer gender, String phone, String fav) {
        this.name = name;
        this.gender = gender;
        this.phone = phone;
        this.fav = fav;
    }
}
