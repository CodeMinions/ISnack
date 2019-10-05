package me.codeminions.bean.db;

import com.google.gson.annotations.Expose;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Comment {    //对应表t_comment

    @Expose
    private int commentID;
    @Expose
    private String comment;
    @Expose
    private int send_id;
    @Expose
    private int snack_id;
    @Expose
    private float star;
    @Expose
    private int likeCount;
    @Expose
    private String time;

    public Comment(String comment, int send_id, int snack_id, float star) {
        this();
        this.comment = comment;
        this.send_id = send_id;
        this.snack_id = snack_id;
        this.star = star;
        this.likeCount = 0;
    }

    public Comment() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.time = df.format(new Date());
    }


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
