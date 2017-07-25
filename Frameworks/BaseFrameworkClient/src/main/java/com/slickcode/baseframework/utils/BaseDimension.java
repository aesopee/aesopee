package com.slickcode.baseframework.utils;

public class BaseDimension {
	private int width;
	private int height;
	
	public BaseDimension(int width, int height) {
		this.width = width;
		this.height = height;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public void setSize(int width, int height) {
		this.width = width;
		this.height = height;
	}
	@Override
	public String toString() {
		return "BaseDimension [width=" + width + ", height=" + height + "]";
	}
	
	
}
