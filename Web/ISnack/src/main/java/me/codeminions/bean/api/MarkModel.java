package me.codeminions.bean.api;

import com.google.gson.annotations.Expose;

public class MarkModel {
    @Expose
    private int user_id;

    @Expose
    private int snack_id;

    @Expose
    private float mark;

    public static boolean check(MarkModel model) {
        return model != null
                && !(model.getUser_id() == 0)
                && !(model.getSnack_id() == 0);
    }

    //测试
    public MarkModel() {
    }

    public MarkModel(int user_id, int snack_id, float mark) {
        this.user_id = user_id;
        this.snack_id = snack_id;
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "MarkModel{" +
                "user_id=" + user_id +
                ", snack_id=" + snack_id +
                ", mark=" + mark +
                '}';
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getSnack_id() {
        return snack_id;
    }

    public void setSnack_id(int snack_id) {
        this.snack_id = snack_id;
    }

    public float getMark() {
        return mark;
    }

    public void setMark(float mark) {
        this.mark = mark;
    }
}
