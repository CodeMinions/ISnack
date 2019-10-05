package me.codeminions.bean.db;

import com.google.gson.annotations.Expose;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SnackMark {     //对应表t_snack_mark

    @Expose
    private int markID;
    @Expose
    private int userID;
    @Expose
    private int snackID;
    @Expose
    private int mark;
    @Expose
    private String time;

    public SnackMark() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  //设置系统日期
        this.time = df.format(new Date());
    }

    public SnackMark(int userID, int snackID, int mark) {
        this.userID = userID;
        this.snackID = snackID;
        this.mark = mark;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  //设置系统日期
        this.time = df.format(new Date());
    }

    public int getMarkID() {
        return markID;
    }

    public void setMarkID(int markID) {
        this.markID = markID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getSnackID() {
        return snackID;
    }

    public void setSnackID(int snackID) {
        this.snackID = snackID;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
