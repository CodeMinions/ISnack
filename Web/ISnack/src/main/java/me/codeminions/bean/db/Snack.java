package me.codeminions.bean.db;

import java.util.HashMap;

/**
 * 访问路径 localhost:8080/isnack/api/
 */
public class Snack {       //对应表t_snack

    private String snackID;
    private String name;
    private String origin;
    private String shelf;
    private float mark;
    private String img;   // 图片的url


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getShelf() {
        return shelf;
    }

    public void setShelf(String shelf) {
        this.shelf = shelf;
    }

    public float getMark() {
        return mark;
    }

    public void setMark(float mark) {
        this.mark = mark;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getSnackID() {
        return snackID;
    }

    public void setSnackID(String snackID) {
        this.snackID = snackID;
    }
}