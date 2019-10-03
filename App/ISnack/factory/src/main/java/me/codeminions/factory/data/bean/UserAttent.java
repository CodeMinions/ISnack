package me.codeminions.factory.data.bean;

public class UserAttent {     //对应表t_user_attent

    private int accountId;
    private int user_id;
    private int attent_id;
    private String time;


    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getAttent_id() {
        return attent_id;
    }

    public void setAttent_id(int attent_id) {
        this.attent_id = attent_id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
