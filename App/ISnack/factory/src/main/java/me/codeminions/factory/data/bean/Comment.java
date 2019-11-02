package me.codeminions.factory.data.bean;

import java.io.Serializable;

public class Comment implements Serializable {    //对应表t_comment

    private int commentID;
    private String comment;
    private int send_id;
    private int snack_id;
    private float star;
    private int likeCount;
    private String time;


    public int getCommentID() {
        return commentID;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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

    public float getStar() {
        return star;
    }

    public void setStar(float star) {
        this.star = star;
    }

    public int getLikecount() {
        return likeCount;
    }

    public void setLikecount(int likeCount) {
        this.likeCount = likeCount;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
