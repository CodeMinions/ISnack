package me.codeminions.bean.api.base;

import com.google.gson.annotations.Expose;

// TODO: 19-6-11 Model类中的所有构造方法都是用于测试 
public class RegisterModel {
    @Expose
    private int id;

    @Expose
    private String name;

    @Expose
    private String pwd;

    public static boolean check(RegisterModel model) {
        return model != null
                && !model.getPassword().isEmpty();
    }

    @Override
    public String toString() {
        return "{\n" +
                "        \"id\":" + id + ",\n" +
                "        \"name\":" + name + ",\n" +
                "        \"password\":" + pwd + "\n";
//                "        }";
    }

    // 测试使用

    public RegisterModel() {
    }
    public RegisterModel(int id, String name, String pwd) {
        this.id = id;
        this.name = name;
        this.pwd = pwd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return pwd;
    }

    public void setPassword(String password) {
        this.pwd = password;
    }

}
