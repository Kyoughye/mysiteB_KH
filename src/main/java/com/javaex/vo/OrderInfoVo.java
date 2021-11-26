package com.javaex.vo;

public class OrderInfoVo {
	private int orderNo;
	private String orderDate;
	private int totalPrice;
	private int memNo;
	public int getMemNo() {
		return memNo;
	}

	public void setMemNo(int memNo) {
		this.memNo = memNo;
	}

	public OrderInfoVo(int orderNo) {
		super();
		this.orderNo = orderNo;
	}
	private String proName;
	private int proPrice;
	
	
	
	
	public OrderInfoVo(int orderNo,String proName, String orderDate, int proPrice) {
		super();
		this.orderNo = orderNo;
		this.orderDate = orderDate;
		this.proPrice = proPrice;
		this.proName = proName;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public int getProPrice() {
		return proPrice;
	}

	public void setProPrice(int proPrice) {
		this.proPrice = proPrice;
	}

	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public OrderInfoVo(int totalPrice, int memNo) {
		super();
		this.totalPrice = totalPrice;
		this.memNo = memNo;
	}

	public OrderInfoVo(String date, int price) {
		// TODO Auto-generated constructor stub
		this.orderDate = date;
		this.totalPrice = price;
	}

	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	
}
