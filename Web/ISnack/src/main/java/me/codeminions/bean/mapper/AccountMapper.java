package me.codeminions.bean.mapper;

import me.codeminions.bean.db.Account;
import org.apache.ibatis.annotations.Param;

public interface AccountMapper {
    public Account getAccount(String name);

    public Account getAccountByIAP(@Param("name") String name, @Param("pwd") String pwd);

    public void addAccount(Account account);
}
