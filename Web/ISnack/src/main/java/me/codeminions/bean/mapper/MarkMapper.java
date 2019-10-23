package me.codeminions.bean.mapper;

import me.codeminions.bean.db.SnackMark;

import java.util.ArrayList;

public interface MarkMapper {
    public void setMark(SnackMark snackMark);    //对零食进行打分

    public ArrayList<SnackMark> getSnackMark(int ID);     //通过零食id，获取零食分数信息
}
