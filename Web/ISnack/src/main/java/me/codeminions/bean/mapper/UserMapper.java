package me.codeminions.bean.mapper;

import me.codeminions.bean.db.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    public User getUserById(int userID);

    public void addUser(User user);

    public void deleteUserById(int userID);

    public List<User> getUserByName(@Param("name") String name);

    public void setNameById(@Param("id") int userID, @Param("name") String name);

}
