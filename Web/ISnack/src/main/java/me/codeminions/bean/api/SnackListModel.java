package me.codeminions.bean.api;

import com.google.gson.annotations.Expose;

public class SnackListModel {
    @Expose
    private int user_id;

    @Expose
    private String title;

    @Expose
    private String content;

    public static boolean check(SnackListModel model){
        return model != null
                && !(model.user_id == 0)
                && !model.getTitle().isEmpty()
                && !model.getContent().isEmpty();
    }

    //测试使用
    public SnackListModel(){}

    public SnackListModel(int user_id,String title,String content){
        this.user_id=user_id;
        this.title=title;
        this.content=content;
    }

    @Override
    public String toString(){
        return "SnackListModel{" +
                ", user_id=" + user_id +
                ", title="+title+
                ", content='" + content + "\'" +
                '}';
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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
}
