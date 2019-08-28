package com.androhungers.rssnewsreader.model.signin;

import com.google.gson.annotations.SerializedName;

public class DataModel{

	@SerializedName("user_name")
	private String userName;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	@SerializedName("age")
	private int age;

	public DataModel() {
	}

	public DataModel(String userName, String name, int id, int age) {
		this.userName = userName;
		this.name = name;
		this.id = id;
		this.age = age;
	}

	public void setUserName(String userName){
		this.userName = userName;
	}

	public String getUserName(){
		return userName;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setAge(int age){
		this.age = age;
	}

	public int getAge(){
		return age;
	}

	@Override
 	public String toString(){
		return 
			"DataModel{" + 
			"user_name = '" + userName + '\'' + 
			",name = '" + name + '\'' + 
			",id = '" + id + '\'' + 
			",age = '" + age + '\'' + 
			"}";
		}
}