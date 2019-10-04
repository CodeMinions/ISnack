package me.codeminions.bean.db;

public class User {       //对应表t_user

    private int userID;
    private String name;
    private String pwd;
    private String sex;
    private String birth;
    private String portrait;
    private Account account;

    public User() {
    }

    public User(int userID, String name, String pwd, String sex, String birth, String portrait) {
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
