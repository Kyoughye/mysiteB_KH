package com.javaex.dao;


public interface OrderHasProductDao {
	public int insert(int orderNo, int proNo);
	public int delete(int orderNo);
}
