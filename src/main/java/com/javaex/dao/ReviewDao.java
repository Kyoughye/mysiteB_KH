package com.javaex.dao;

import java.util.List;

import com.javaex.vo.ReviewVo;
public interface ReviewDao {
	//서블릿 - 리뷰 작성 가능 여부 확인 (회원 여부 확인 -> 구매 내역에 해당 상품 있는지 확인)
	public int insert(ReviewVo vo);//리뷰 작성 (내용 + 사진)
	public int update(ReviewVo vo);//리뷰 수정 (내용 + 사진)
	public int delete(int revNo);//리뷰 삭제 (리뷰 번호로)
	public List<ReviewVo> getList(int page, int limit,int proNo, String orderBy);//리뷰 보여주기 (정렬: 별점 순, 작성날짜 순)
	public int getListCount(int proNo);//리뷰 갯수 세기
	public void updateHit(int revHit);//조회수 올리기
	public float rateAvg(int proNum ); //별점 평균
	public List<ReviewVo> getPrivateList(int page, int limit, int memNo);
	public int privateReviewCount(int memNo);
}

