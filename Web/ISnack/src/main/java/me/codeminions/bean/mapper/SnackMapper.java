package me.codeminions.bean.mapper;

import me.codeminions.bean.db.Snack;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

public interface SnackMapper {
    public Snack getSnackByName(String name);     //通过零食的名字，获取零食产地等信息
    public int getSnackIdByName(String name);     //通过零食名字获取零食id

<<<<<<< HEAD
    public List<Snack> getAllSnack();               // 获取所有零食信息
=======
    public void updateSnackMark(@Param("mark") float mark,@Param("id") int id);     //通过零食id修改对零食的打分
    public ArrayList<Snack> recommend();     //按评分推荐
>>>>>>> d7ff55358ad3b6703eb42620e9e3c7a6dbab6062
}
