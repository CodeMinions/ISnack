package me.codeminions.factory.data.model.baseModel;

import java.io.Serializable;
import java.util.List;

import me.codeminions.factory.data.bean.Snack;
import me.codeminions.factory.data.bean.User;

// 单条零食清单的bean
public class SnackListModel implements Serializable {

    private User user;

    private String title;

    private List<Snack> list;

    private String content;

    private String time;

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
