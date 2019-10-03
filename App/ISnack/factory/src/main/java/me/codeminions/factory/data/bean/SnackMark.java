package me.codeminions.factory.data.bean;

public class SnackMark {     //对应表t_snack_mark

    private int markID;
    private int userID;
    private int snackID;
    private float mark;
    private String time;


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

    public float getMark() {
        return mark;
    }

    public void setMark(float mark) {
        this.mark = mark;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
