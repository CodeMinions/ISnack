package me.codeminions.bean.mapper;

import me.codeminions.bean.db.SnackList;

import java.util.List;

public interface SnackListMapper {
    public int getListIdMax();        //获取list_id的最大值

    public void setSnackList(SnackList snackList);     //发送零食清单

    public List<SnackList> getSnackListByUser(int id);      //获取已发送的清单

    public List<SnackList> getAllSnackList();
}
