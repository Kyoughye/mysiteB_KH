package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.javaex.vo.QnaBoardVo;

public class QnaBoardDaoImpl implements QnaBoardDao {
	
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
	public int insert(QnaBoardVo vo) {
		Connection conn = null;
		 PreparedStatement pstmt = null;
		 int count = 0;
		 
		 try {
			 conn = getConnection();
			 
			 String query = "insert into qnaboard values (seq_qnaboard_no.nextval, ?, ?, ?, ?, ?, ?, sysdate,?, 1 )";
			 pstmt = conn.prepareStatement(query);
			 
			 pstmt.setInt(1, vo.getMemNo());
			 pstmt.setString(2, vo.getNickname());
			 pstmt.setString(3, vo.getPass());
			 pstmt.setString(4, vo.getTitle());
			 pstmt.setString(5, vo.getType());
			 pstmt.setString(6, vo.getContent());
			 pstmt.setInt(7, vo.getPriv());
			
			 
			 count = pstmt.executeUpdate();
			 
			 System.out.println(count + "건 등록");
			 
			 System.out.println("문의 유형 " + vo.getType());
			 System.out.println("제목 " + vo.getTitle());
			 
			 
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
		return count;
	}

	@Override
	public int delete(int no) {
		Connection conn = null;
		 PreparedStatement pstmt = null;
		 int count = 0;
		 
		 try {
			 conn = getConnection();
			 
			 String query = "update qnaboard "
			 		+ "set qnabck=0 "
			 		+ "where qnano = ? ";
			 pstmt = conn.prepareStatement(query);
			 
			 pstmt.setInt(1, no);
			 
			 count = pstmt.executeUpdate();
			 
			 System.out.println(count + "건 삭제");
			 
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
		return count;
	}

	@Override
	public ArrayList<QnaBoardVo> getList(String type, int page) {
		 Connection conn = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 ArrayList<QnaBoardVo> list = new ArrayList<QnaBoardVo>();
		 
		 try {
			 conn = getConnection();
			 
			 if(type.equals("전체 문의")) {
			 
			String query = "select qna.* "
					+ "from( "
					+ "select rownum num, qn.* "
					+ "from (select q.*, m.memName "
					+ "from qnaboard q, regmember m  "
					+ "where q.memno = m.memno "
					+ "and qnabck=1 "
					+ "order by qnano desc) qn) qna "
					+ "where num between ? and ? ";

			 pstmt = conn.prepareStatement(query);
			 pstmt.setInt(1, 1+(page-1)*5 );
			 pstmt.setInt(2, page*5);
			 
			 } else {
				 
				 String query = "select qna.*  "
				 		+ "from(  "
				 		+ "select rownum num, qn.*  "
				 		+ "from (select q.*, m.memName  "
				 		+ "from qnaboard q, regmember m  "
				 		+ "where q.memno = m.memno  "
				 		+ "and qnabck=1 "
				 		+ "and type like ? "    
				 		+ "order by qnano desc) qn) qna  "
				 		+ "where num between ? and ? ";

					 pstmt = conn.prepareStatement(query);
					 pstmt.setString(1, "%"+type+"%" );
					 pstmt.setInt(2, 1+(page-1)*5 );
					 pstmt.setInt(3, page*5);
				 
			 }
			 			 
			 rs = pstmt.executeQuery();
			 
			 while(rs.next()) {
				 QnaBoardVo vo = new QnaBoardVo();
				 vo.setQnaNo(rs.getInt("qnano"));
				 vo.setMemNo(rs.getInt("memno"));
				 vo.setMemName(rs.getString("memname"));
				 vo.setNickname(rs.getString("nickname"));
				 vo.setPass(rs.getString("pass"));
				 vo.setTitle(rs.getString("title"));
				 vo.setType(rs.getString("type"));
				 vo.setContent(rs.getString("content"));
				 vo.setRegDate(rs.getString("regdate"));
				 vo.setPriv(rs.getInt("priv"));
				 vo.setQnabCk(rs.getInt("qnabck"));
				 
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
	public int getBoardCount() {
		return getBoardCount("전체 문의");
	}

	@Override
	public int getBoardCount(String type) {
		Connection conn = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 ArrayList<QnaBoardVo> list = new ArrayList<QnaBoardVo>();
		 int count = 0;
		 
		 try {
			 conn = getConnection();
			 
			 if(type.equals("전체 문의")) {
			 
			 String query = "select count(qnano) from qnaboard where qnabck =1  ";
			 
			 pstmt = conn.prepareStatement(query);
			 
			 } else {
				 
				 String query = "select count(qnano) from( "
					 		+ "select rownum num, q.* "
					 		+ "from( "
					 		+ "select * from qnaboard "
					 		+ "where type like ? "
					 		+ "and qnabck= 1 "
					 		+ "order by qnano desc)q) ";
					 
					 pstmt = conn.prepareStatement(query);
					 pstmt.setString(1, "%"+type+"%" );			 
				 
			 }
			 rs = pstmt.executeQuery();
			 
			 if(rs.next()) {
				 count = rs.getInt("count(qnano)");
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
		
		return count;
	}

	@Override
	public QnaBoardVo getBoard(int no) {

		 Connection conn = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 QnaBoardVo  vo = new QnaBoardVo();
		 
		 try {
			 conn = getConnection();
			 
			 String query = "select q.*, m.memName "
			 		+ "from qnaboard q, regmember m "
			 		+ "where q.memno = m.memno and qnano = ? ";
			 pstmt = conn.prepareStatement(query);
			 pstmt.setInt(1,  no);
			 
			 rs = pstmt.executeQuery();
			 
			 if(rs.next()) {
			
				 vo.setQnaNo(rs.getInt("qnano"));
				 vo.setMemNo(rs.getInt("memno"));
				 vo.setMemName(rs.getString("memname"));
				 vo.setNickname(rs.getString("nickname"));
				 vo.setPass(rs.getString("pass"));
				 vo.setTitle(rs.getString("title"));
				 vo.setType(rs.getString("type"));
				 vo.setContent(rs.getString("content"));
				 vo.setRegDate(rs.getString("regdate"));
				 vo.setPriv(rs.getInt("priv"));
				 vo.setQnabCk(rs.getInt("qnabck"));
			
				// System.out.println(vo.toString());
		 
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
		return vo;
	}

	@Override
	public int update(QnaBoardVo vo) {
		Connection conn = null;
		 PreparedStatement pstmt = null;
		 int count = 0;
		
		 
		 try {
			 conn = getConnection();
			 
			 String query = "update qnaboard "
			 		+ "set title= ? , type= ?, content = ?, pass = ?,  priv= ? "
			 		+ "where qnano = ? ";
			 pstmt = conn.prepareStatement(query);
			 
			 pstmt.setString(1, vo.getTitle());
			 pstmt.setString(2, vo.getType());
			 pstmt.setString(3, vo.getContent());
			 pstmt.setString(4, vo.getPass());
			 pstmt.setInt(5, vo.getPriv());
			 pstmt.setInt(6, vo.getQnaNo());
			 
			 
			 count = pstmt.executeUpdate();
			 
			 System.out.println(count + "건 수정");
			 
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
		return count;
	}


	@Override
	public QnaBoardVo getBoard(int qnano, String password) {
		 Connection conn = null;
		 PreparedStatement pstmt = null;
		 ResultSet rs = null;
		 QnaBoardVo  vo = new QnaBoardVo();
		 
		 try {
			 conn = getConnection();
			 
			 String query = "select q.*, m.memName from qnaboard q, regmember m  "
			 		+ "where qnano = ?  "
			 		+ "and pass = ? "
			 		+ "and q.memno = m.memno ";
			 
			 pstmt = conn.prepareStatement(query);
			 pstmt.setInt(1,  qnano);
			 pstmt.setString(2, password);
			 
			 rs = pstmt.executeQuery();
			 
			 if(rs.next()) {
			
				 vo.setQnaNo(rs.getInt("qnano"));
				 vo.setMemNo(rs.getInt("memno"));
				 vo.setMemName(rs.getString("memname"));
				 vo.setNickname(rs.getString("nickname"));
				 vo.setPass(rs.getString("pass"));
				 vo.setTitle(rs.getString("title"));
				 vo.setType(rs.getString("type"));
				 vo.setContent(rs.getString("content"));
				 vo.setRegDate(rs.getString("regdate"));
				 vo.setPriv(rs.getInt("priv"));
				 vo.setQnabCk(rs.getInt("qnabck"));
			
				// System.out.println(vo.toString());
		 
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
		return vo;
	}

}
