package me.codeminions.bean.mapper;

import me.codeminions.bean.db.Comment;

import java.util.List;

public interface CommentMapper {
    public List<Comment> getComment(int ID);    //通过零食id获取零食所有评价

    public List<Comment> getCommentByUser(int ID);  //通过用户获取零食评价

    public void setComment(Comment comment);     //对零食进行评价

    public void updateLike(int ID);      //通过零食ID，改变该条的likeCount
}
