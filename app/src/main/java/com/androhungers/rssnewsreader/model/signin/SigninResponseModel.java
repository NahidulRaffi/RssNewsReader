package com.androhungers.rssnewsreader.model.signin;

import com.google.gson.annotations.SerializedName;

public class SigninResponseModel{

	@SerializedName("data")
	private DataModel data;

	@SerializedName("success")
	private boolean success;

	@SerializedName("message")
	private String message;

	public void setData(DataModel data){
		this.data = data;
	}

	public DataModel getData(){
		return data;
	}

	public void setSuccess(boolean success){
		this.success = success;
	}

	public boolean isSuccess(){
		return success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
 	public String toString(){
		return 
			"SigninResponseModel{" + 
			"data = '" + data + '\'' + 
			",success = '" + success + '\'' +
			",message = '" + message + '\'' +
			"}";
		}
}