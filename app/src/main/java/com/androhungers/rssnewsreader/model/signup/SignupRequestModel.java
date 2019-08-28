package com.androhungers.rssnewsreader.model.signup;

public class SignupRequestModel {
    private String user_name = null;
    private String password = null;
    private String name = null;
    private String age = null;


    public SignupRequestModel() {
    }

    public SignupRequestModel(String user_name, String password, String name, String age) {
        this.user_name = user_name;
        this.password = password;
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
