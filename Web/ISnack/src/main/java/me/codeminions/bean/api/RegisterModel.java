package me.codeminions.bean.api;

import com.google.gson.annotations.Expose;

// TODO: 19-6-11 Model类中的所有构造方法都是用于测试 
public class RegisterModel {

    @Expose
    private String name;

    @Expose
    private String pwd;

    @Expose
    private String sex;

    @Expose
    private String birth;

    public static boolean check(RegisterModel model) {
        return model != null
                && !model.getName().isEmpty()
                && (model.getSex().equals("famale") || model.getSex().equals("male"))
                && !model.getPwd().isEmpty()
                && !model.getBirth().isEmpty();
    }

    @Override
    public String toString() {
        return "User{" +
                ", name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                ", sex=" + sex + '\'' +
                ", birth=" + birth + '\'' +
                '}';
    }

    // 测试使用

    public RegisterModel() {
    }

    public RegisterModel(String name, String pwd, String sex, String birth, String portrait) {
        this.name = name;
        this.pwd = pwd;
        this.sex = sex;
        this.birth = birth;
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
}
