package me.codeminions.bean.db;

import com.google.gson.annotations.Expose;

public class Snack {       //对应表t_snack

    @Expose
    private int snackID;
    @Expose
    private String name;
    @Expose
    private String origin;
    @Expose
    private String shelf;
    @Expose
    private float mark;
    @Expose
    private String img;   // 图片的url

    public Snack(String name) {
        this.name = name;
    }

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

    public int getSnackID() {
        return snackID;
    }

    public void setSnackID(int snackID) {
        this.snackID = snackID;
    }
}