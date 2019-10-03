package me.codeminions.bean.api.base;

import com.google.gson.annotations.Expose;

public class ChangeModel {
    @Expose
    private int userID;

    @Expose
    private String name;

    public static  boolean check(ChangeModel model){
        return model.name != null
                && model.name.length() <= 20;
    }

    @Override
    public String toString() {
        return "{\n" +
                "        \"id\":" + userID + ",\n" +
                "        \"name\":" + name + ",\n" +
                "        }";
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
