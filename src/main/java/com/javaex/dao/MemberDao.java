package com.javaex.dao;

import java.util.ArrayList;

import com.javaex.vo.MemberVo;
import com.javaex.vo.OrderInfoVo;





public interface MemberDao {
	public int insert(MemberVo vo);

	public MemberVo getMember( String memId, String memPass );

	public MemberVo getMember(int memNo);

	public int update(MemberVo vo);
	
	public ArrayList<OrderInfoVo> getList(int memNo);
	
	public int delete(MemberVo vo);
}