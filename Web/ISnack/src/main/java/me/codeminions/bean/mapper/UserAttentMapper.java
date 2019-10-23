package me.codeminions.bean.mapper;

import me.codeminions.bean.db.UserAttent;

import java.util.List;

public interface UserAttentMapper {
    public List<UserAttent> getUserAttent();    //获取关注用户的信息
}
