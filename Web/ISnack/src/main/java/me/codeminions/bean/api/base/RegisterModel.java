package me.codeminions.bean.api.base;

import com.google.gson.annotations.Expose;

// TODO: 19-6-11 Model类中的所有构造方法都是用于测试 
public class RegisterModel {
    @Expose
    private int userID;

    @Expose
    private String name;

    @Expose
    private String pwd;

    @Expose
    private String sex;

    @Expose
    private String birth;

    @Expose
    private String portrait;

    public static boolean check(RegisterModel model) {
        return model != null
                && model.getUserID() != 0
                && !model.getName().isEmpty()
                && (model.getSex().equals("女") || model.getSex().equals("男"))
                && !model.getPwd().isEmpty()
                && !model.getBirth().isEmpty();
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + userID + '\'' +
                ", name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                ", sex=" + sex + '\'' +
                ", birth=" + birth + '\'' +
                ", portrait=" + portrait + '\'' +
                '}';
    }

    // 测试使用

    public RegisterModel() {
    }

    public RegisterModel(int userID, String name, String pwd, String sex, String birth, String portrait) {
        this.userID = userID;
        this.name = name;
        this.pwd = pwd;
        this.sex = sex;
        this.birth = birth;
        this.portrait = portrait;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }
}
