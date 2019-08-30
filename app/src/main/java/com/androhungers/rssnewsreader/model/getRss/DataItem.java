package com.androhungers.rssnewsreader.model.getRss;

import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("link")
	private String link;

	@SerializedName("id")
	private String id;

	@SerializedName("feed_name")
	private String feedName;

	public void setLink(String link){
		this.link = link;
	}

	public String getLink(){
		return link;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setFeedName(String feedName){
		this.feedName = feedName;
	}

	public String getFeedName(){
		return feedName;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"link = '" + link + '\'' + 
			",id = '" + id + '\'' + 
			",feed_name = '" + feedName + '\'' + 
			"}";
		}
}