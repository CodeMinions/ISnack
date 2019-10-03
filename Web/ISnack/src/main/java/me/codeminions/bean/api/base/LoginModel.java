package me.codeminions.bean.api.base;


import com.google.gson.annotations.Expose;

public class LoginModel {
    @Expose
    private int userID;

    @Expose
    private String pwd;

    public static boolean check(LoginModel model){
        return model != null
                && !model.getUserID().toString().isEmpty()
                && !model.getPwd().isEmpty();
    }

    @Override
    public String toString() {
        return "{\n" +
                "        \"userID\":" + userID.toString() + ",\n" +
                "        \"password\":" + pwd + "\n" +
                "        }";
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
