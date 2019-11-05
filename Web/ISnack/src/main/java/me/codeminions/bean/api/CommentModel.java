package me.codeminions.bean.api;

import com.google.gson.annotations.Expose;
import me.codeminions.bean.db.Comment;
import me.codeminions.bean.db.User;

public class CommentModel {
    @Expose
    private User send;

    @Expose
    private int snack_id;

    @Expose
    private String content;

    @Expose
    private float star;

    public static boolean check(CommentModel model) {
        return model != null
                && !model.getContent().isEmpty()
                && (model.getSend()  != null)
                && !(model.getSnack_id() == 0);
    }

    //测试使用
    public CommentModel() {
    }

    public CommentModel(Comment comment) {
        this.snack_id = comment.getSnack_id();
        this.content = comment.getComment();
        this.star = comment.getStar();
    }

    public CommentModel(User send, int snack_id, String content, int star) {
        this.send = send;
        this.snack_id = snack_id;
        this.content = content;
        this.star = star;
    }

    @Override
    public String toString() {
        return "SnackModel{" +
                "send=" + send +
                ", snack_id=" + snack_id +
                ", content='" + content + "\'" +
                ", star=" + star +
                '}';
    }


    public User getSend() {
        return send;
    }

    public void setSend(User send) {
        this.send = send;
    }

    public int getSnack_id() {
        return snack_id;
    }

    public void setSnack_id(int snack_id) {
        this.snack_id = snack_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public float getStar() {
        return star;
    }

    public void setStar(float star) {
        this.star = star;
    }
}
