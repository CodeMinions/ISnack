package me.codeminions.bean.mapper;

import me.codeminions.bean.db.Snack;

import java.util.ArrayList;
import java.util.List;

public interface SnackMapper {
    public Snack getSnackByName(String name);     //通过零食的名字，获取零食产地等信息

    public int getSnackIdByName(String name);     //通过零食名字获取零食id

    public List<Snack> getAllSnack();               // 获取所有零食信息
}
