package me.codeminions.bean.mapper;

import me.codeminions.bean.db.Message;

import java.util.List;

public interface MessageMapper {
    public List<Message> getMessageByUnlook(int isLook);  //获取当前未读信息

    public List<Message> getMessageById(int id);    //获取自己给出的评价

    public void updateIsLook(int id);      //通过用户id，观察该消息是否被查看
}
