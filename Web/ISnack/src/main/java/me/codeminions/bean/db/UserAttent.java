package me.codeminions.bean.db;

import com.google.gson.annotations.Expose;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UserAttent {     //对应表t_user_attent

    @Expose
    private int accountId;

    @Expose
    private int user_id;

    @Expose
    private int attent_id;

    @Expose
    private String time;

    public UserAttent(int accountId,int user_id,int attent_id){
        this.accountId=accountId;
        this.user_id=user_id;
        this.attent_id=attent_id;
    }

    public UserAttent(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.time = df.format(new Date());
    }

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
