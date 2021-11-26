package com.javaex.dao;

import java.util.ArrayList;
import com.javaex.vo.QnaBoardVo;

public interface QnaBoardDao {
	public int insert(QnaBoardVo vo);
	public int delete(int no);
	//public ArrayList<QnaBoardVo> getList();
	public ArrayList<QnaBoardVo> getList(String type, int page);
	public int getBoardCount();
	public int getBoardCount(String type);
	public QnaBoardVo getBoard(int no);
	public int update(QnaBoardVo vo);
	

}
