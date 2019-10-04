package me.codeminions.bean.api.base;

import com.google.gson.annotations.Expose;

/**
 * 用户登录model，使用用户昵称和密码登录（故用户名不得重复，注册时检验）
 */
public class LoginModel {
    @Expose
    private String userName;

    @Expose
    private String pwd;

    public static boolean check(LoginModel model){
        return model != null
                && !model.getPwd().isEmpty()
                && !model.getUserName().isEmpty();
    }

    @Override
    public String toString() {
        return "{\n" +
                "        \"userID\":" + userName + ",\n" +
                "        \"password\":" + pwd + "\n" +
                "        }";
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
