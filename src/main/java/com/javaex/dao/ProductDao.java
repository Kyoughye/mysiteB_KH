package com.javaex.dao;

import java.util.ArrayList;


import com.javaex.vo.ProductVo;

public interface ProductDao {
	
	public ArrayList<ProductVo> getList();
	public int getListCount();
	public int getListCount(String searchfrom, String kwd);
	public ArrayList<ProductVo> getList(int page, String searchFrom, String kwd, String orderBy);

}
