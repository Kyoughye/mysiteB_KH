package com.javaex.dao;

import java.util.ArrayList;

import com.javaex.vo.QnaAnswerVo;

public interface QnaAnswerDao {
	public ArrayList<QnaAnswerVo> getAnsList(int qnano);
	public int insertAns(QnaAnswerVo vo);
	public int deleteAns(QnaAnswerVo vo);
	public int updateAns(QnaAnswerVo vo);

}
