package com.cn.dsyg.dto;

public class Bar {
	
	  private static final long serialVersionUID = 1L;
	  private String name;
	  private int[] data;
	  public Bar() {
	  };
	  public Bar(String name, int[] data) {
	      this.name = name;
	      this.data = data;
	  }
	  public String getName() {
	      return name;
	  }
	  public void setName(String name) {
	      this.name = name;
	  }
	  public int[] getData() {
	      return data;
	  }
	  public void setData(int[] data) {
	      this.data = data;
	  }
}
