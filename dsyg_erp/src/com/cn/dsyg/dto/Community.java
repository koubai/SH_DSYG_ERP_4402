package com.cn.dsyg.dto;

import java.io.Serializable;

public class Community implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getActivity_entry() {
		return activity_entry;
	}

	public void setActivity_entry(int activity_entry) {
		this.activity_entry = activity_entry;
	}

	public int getActivity_update() {
		return activity_update;
	}

	public void setActivity_update(int activity_update) {
		this.activity_update = activity_update;
	}

	public int getBlog_entry() {
		return blog_entry;
	}

	public void setBlog_entry(int blog_entry) {
		this.blog_entry = blog_entry;
	}

	public int getBlog_comments() {
		return blog_comments;
	}

	public void setBlog_comments(int blog_comments) {
		this.blog_comments = blog_comments;
	}

	public int getFeed() {
		return feed;
	}

	public void setFeed(int feed) {
		this.feed = feed;
	}

	public int getBookmark() {
		return bookmark;
	}

	public void setBookmark(int bookmark) {
		this.bookmark = bookmark;
	}

	public int getFile() {
		return file;
	}

	public void setFile(int file) {
		this.file = file;
	}

	public int getForum_topic() {
		return forum_topic;
	}

	public void setForum_topic(int forum_topic) {
		this.forum_topic = forum_topic;
	}

	public int getForum_reply() {
		return forum_reply;
	}

	public void setForum_reply(int forum_reply) {
		this.forum_reply = forum_reply;
	}

	public int getWiki() {
		return wiki;
	}

	public void setWiki(int wiki) {
		this.wiki = wiki;
	}

	public int getiRam() {
		return iRam;
	}

	public void setiRam(int iRam) {
		this.iRam = iRam;
	}

	public int getLiquid() {
		return liquid;
	}

	public void setLiquid(int liquid) {
		this.liquid = liquid;
	}

	public int getUser() {
		return user;
	}

	public void setUser(int user) {
		this.user = user;
	}

	private int id;
	  private int version;
	  private String remark;
	  private int activity_entry;
	  private int activity_update;
	  private int blog_entry;
	  private int blog_comments;
	  private int feed;
	  private int bookmark;
	  private int file;
	  private int forum_topic;
	  private int forum_reply;
	  private int wiki;
	  private int iRam;
	  private int liquid;
	  private int user;

	  public int[] getValueArray() {
	      int[] array = { activity_entry, activity_update, blog_entry,
	              blog_comments, feed, bookmark, file, forum_topic, forum_reply,
	              wiki, iRam, liquid };
	      return array;
	  }
}
