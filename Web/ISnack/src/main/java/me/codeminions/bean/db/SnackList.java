package me.codeminions.bean.db;

import com.google.gson.annotations.Expose;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SnackList {     //对应表t_snack_list

    @Expose
    private int listID;

    @Expose
    private int list_id;

    @Expose
    private int user_id;

    @Expose
    private int snack_id;

    @Expose
    private String title;

    @Expose
    private String content;

    @Expose
    private String time;

    public SnackList(int list_id,int user_id,int snack_id,String title,String content,String time){
        this.list_id=list_id;
        this.user_id=user_id;
        this.snack_id=snack_id;
        this.title=title;
        this.content=content;
        this.time=time;
    }

    public SnackList() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.time = df.format(new Date());
    }

    public int getListID() {
        return listID;
    }

    public void setListID(int listID) {
        this.listID = listID;
    }

    public int getList_id() {
        return list_id;
    }

    public void setList_id(int list_id) {
        this.list_id = list_id;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
