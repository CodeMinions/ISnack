package me.codeminions.bean.api;

import com.google.gson.annotations.Expose;
import me.codeminions.bean.db.Snack;
import me.codeminions.bean.db.User;


import java.util.Collections;
import java.util.List;

// 单条零食清单的bean
public class SnackListModel {

    @Expose
    private User user;
    @Expose
    private String title;
    @Expose
    private List<Snack> list;
    @Expose
    private String content;
    @Expose
    private String time;

    public static boolean check(SnackListModel model){
        return model != null
//                && !(model.getUser().getUserID() == 0)
                && !model.getTitle().isEmpty()
                && !model.getContent().isEmpty();
    }

    public SnackListModel(User user, String title, List<Snack> list, String content, String time) {
        this.user = user;
        this.title = title;
        this.list = list;
        this.content = content;
        this.time = time;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Snack> getList() {
        return list;
    }

    public void setList(List<Snack> list) {
        this.list = list;
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
