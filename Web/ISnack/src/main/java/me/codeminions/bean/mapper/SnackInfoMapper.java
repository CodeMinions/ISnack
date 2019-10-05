package me.codeminions.bean.mapper;

import me.codeminions.bean.db.SnackInfo;

public interface SnackInfoMapper {
    public SnackInfo getSnackInfoById(int ID);      //通过零食id获取零食的能量表
}
