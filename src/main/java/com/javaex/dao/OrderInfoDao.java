package com.javaex.dao;

import java.util.List;

import com.javaex.vo.OrderInfoVo;

public interface OrderInfoDao {
	public int insert(OrderInfoVo vo);
	public int delete(int orderNo);
	public List<OrderInfoVo> getDetail(int orderNo);
	public List<OrderInfoVo> getList(int page , int limit, int memNo , String searchDate1, String searchDate2);
	public int getListCount(String searchDate1, String searchDate2 , int memNo);
}
