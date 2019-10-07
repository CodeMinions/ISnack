package me.codeminions.bean.mapper;

import me.codeminions.bean.db.SnackList;

import java.util.List;

public interface SnackListMapper {
    public List<SnackList> getSnackListByUser(int id);
}
