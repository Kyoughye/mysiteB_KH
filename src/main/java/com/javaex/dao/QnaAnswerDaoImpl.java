package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.javaex.vo.QnaAnswerVo;

public class QnaAnswerDaoImpl implements QnaAnswerDao {

	private Connection getConnection() throws SQLException { 
	    Connection conn = null;
	    try {
	      Class.forName("oracle.jdbc.driver.OracleDriver");
	      String dburl = "jdbc:oracle:thin:@localhost:1521:xe";
	      conn = DriverManager.getConnection(dburl, "mysiteB", "1234");
	    } catch (ClassNotFoundException e) {
	      System.err.println("JDBC 드라이버 로드 실패!"); 
	    }
	    return conn;
	}

	@Override
	public ArrayList<QnaAnswerVo> getAnsList(int qnano) {
		Connection conn = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 ArrayList<QnaAnswerVo> list = new ArrayList<QnaAnswerVo>();
		 
		 try {
			 conn = getConnection();
			 
			String query = "select qa.* "
					+ "from qnaanswer qa, qnaboard qb "
					+ "where qa.qnano = qb.qnano "
					+ "and qa.qnano = ? ";

			 pstmt = conn.prepareStatement(query);
			 pstmt.setInt(1, qnano);
			 rs = pstmt.executeQuery();
			 
			 while(rs.next()) {
				 QnaAnswerVo vo = new QnaAnswerVo();
				 vo.setAnsNo(rs.getInt("ansno"));
				 vo.setQnaNo(rs.getInt("qnano"));
				 vo.setMemNo(rs.getInt("memno"));
				 vo.setAnswer(rs.getString("answer"));
				 vo.setRegDate(rs.getString("regdate"));
				 vo.setAnswerCk(rs.getInt("answerck"));
				 
				 list.add(vo);
			 }
		 } catch (SQLException e) {
			 System.out.println("error:" + e);
		 }finally {
			 try {
				 if(pstmt != null) pstmt.close();
				 if(conn != null) conn.close();
			 } catch (SQLException e) {
				 System.out.println("error:" + e);
				 
			 }
		 }
		
		return list;
		
	}

	@Override
	public int insertAns(QnaAnswerVo vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteAns(QnaAnswerVo vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateAns(QnaAnswerVo vo) {
		// TODO Auto-generated method stub
		return 0;
	}
}

