package me.codeminions.factory.data.model;

import java.io.Serializable;

public class CommentModel implements Serializable {
    private int send_id;

    private int snack_id;

    private String content;

    private float star;

    public static boolean check(CommentModel model) {
        return model != null
                && !model.getContent().isEmpty()
                && !(model.getSend_id() == 0)
                && !(model.getSnack_id() == 0);
    }

    //测试使用
    public CommentModel() {
    }

    public CommentModel(int send_id, int snack_id, String content, float star) {
        this.send_id = send_id;
        this.snack_id = snack_id;
        this.content = content;
        this.star = star;
    }

    @Override
    public String toString() {
        return "SnackModel{" +
                "send_id=" + send_id +
                ", snack_id=" + snack_id +
                ", content='" + content + "\'" +
                ", star=" + star +
                '}';
    }


    public int getSend_id() {
        return send_id;
    }

    public void setSend_id(int send_id) {
        this.send_id = send_id;
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
