
package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.ReviewVo;

public class ReviewDaoImpl implements ReviewDao{
	
	private static ReviewDao instance;
	public ReviewDaoImpl() {
		
	}
	public static ReviewDao getInstance() {
		if(instance == null) {
			instance = new ReviewDaoImpl();
		}
		return instance;
	}
	
	
	
	private Connection getConnection() throws SQLException {
	    Connection conn = null;
	    try {
	      Class.forName("oracle.jdbc.driver.OracleDriver");
	      String dburl = "jdbc:oracle:thin:@192.168.0.24:1521:xe";
	      conn = DriverManager.getConnection(dburl, "mysiteB", "1234");
	    } catch (ClassNotFoundException e) {
	      System.err.println("JDBC 드라이버 로드 실패!");
	    }
	    return conn;
	}
	
	@Override
	public int insert(ReviewVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int count = 0;

		try {
		  conn = getConnection();
		  
		  System.out.println("insert 리뷰 : ["+vo.getRevNo()+"]");
		  
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "insert into review\r\n"
					+ "values (seq_review_no.nextval, ?, ?, ?, ?, to_char(sysdate, 'YY-MM-DD HH24:MI'), ?, 0, ?, ? , 1)";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, vo.getMemNo());
			pstmt.setInt(2, vo.getProNo());
			pstmt.setString(3, vo.getTitle());
			pstmt.setString(4, vo.getContent());
			pstmt.setInt(5, vo.getRate());
			pstmt.setString(6, vo.getFile1());
			pstmt.setString(7, vo.getFile2());
			
			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건 등록");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 5. 자원정리
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}

		}

		return count;
	}

	@Override
	public int update(ReviewVo vo) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0;

		try {
		  conn = getConnection();

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "update review\r\n"
					+ "set title = ?, content = ?,  rate = ?, file1 = ?, file2 = ?\r\n"
					+ "where revNo = ?";
			pstmt = conn.prepareStatement(query);

			
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setInt(3, vo.getRate());
			pstmt.setString(4, vo.getFile1());
			pstmt.setString(5, vo.getFile2());
			pstmt.setInt(6, vo.getRevNo());
			
			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건 수정");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 5. 자원정리
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}

		}

		return count;
	}

	@Override
	public int delete(int revNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0;

		try {
		  conn = getConnection();

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "delete from review where revNo=?";
			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, revNo);

			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건 삭제");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 5. 자원정리
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}

		}

		return count;
	}

	@Override
	public List<ReviewVo> getList(int page, int limit, int proNo, String orderBy) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int total_record = getListCount(proNo);
		int start = (page - 1) * limit;
		int index = start + 1;
		String sql;
		if (orderBy == null || "".equals(orderBy)) {
			orderBy = "revDate";
		}
		
		List<ReviewVo> list = new ArrayList<ReviewVo>();
			
		if (proNo == -1) {
			sql = "select r.* , p.proname , m.memid from review r, product p, regmember m \r\n "
					+ "where r.proNo = p.proNo\r\n "
					+ "and r.memno = m.memno\r\n "
					+ " and r.revavail = 1\r\n "
					+ " order by r." + orderBy +" desc ";

		}
		else { 
			sql = "select r.* , p.proname , m.memid from review r, product p, regmember m \r\n "
					+ "where r.proNo = p.proNo\r\n "
					+ "and r.memno = m.memno\r\n "
					+ "and r.prono =  " + proNo 
					+ " and r.revavail = 1\r\n "
					+ " order by r." + orderBy +" desc ";

		}
		
		try {
			conn = getConnection();
			 
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = pstmt.executeQuery();
			while (rs.absolute(index)) {
				ReviewVo vo = new ReviewVo();
				vo.setRevNo(rs.getInt("RevNo"));
				vo.setMemNo(rs.getInt("MemNo"));
				vo.setProNo(rs.getInt("proNo"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setRevDate(rs.getString("REVDATE"));
				vo.setRate(rs.getInt("rate"));
				vo.setRevHit(rs.getInt("revhit"));
				vo.setFile1(rs.getString("file1"));
				vo.setFile2(rs.getString("file2"));
				vo.setProName(rs.getString("proName"));
				
				vo.setMemId(rs.getString("memid").substring(0,2) + "***"); 
			
				list.add(vo);
				if (index < (start + limit) && index <= total_record)
					index++;
			
				else 
					break;
			}
			
		} catch (Exception ex) {
			System.out.println("getList() error : " + ex);
		} finally {
			try {
				if (rs != null) 
					rs.close();							
				if (pstmt != null) 
					pstmt.close();				
				if (conn != null) 
					conn.close();
			} catch (Exception ex) {
				throw new RuntimeException(ex.getMessage());
			}			
		}
		return list;
	}

	@Override
	public int getListCount(int proNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int count = 0;
		
		String query;
		
		
		if(proNo == -1) {
			query = "select count(*) from review where revavail =1";
		}else {
			query = "select count(*) from review where proNo = " + proNo + " and  revavail =1";
		}
		
		try {
			
		
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			if (rs.next())
				count = rs.getInt(1);
			System.out.println(count);
		} catch (Exception ex)	{
			System.out.println("리스트 카운트 에러 " + ex);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception ex) {
				throw new RuntimeException(ex.getMessage());
			}
		}
		return count;
	}

	@Override
	public void updateHit(int revNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();

			String sql = "select revHit from review where revNo = " + revNo;
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			int hit = 0;

			if (rs.next())
				hit = rs.getInt("hit") + 1;
		
			sql = "update review set revHit=? where revNo= " + revNo;
			pstmt = conn.prepareStatement(sql);		
			pstmt.setInt(1, hit);

			pstmt.executeUpdate();
		} catch (Exception ex) {
			System.out.println("updateHit() : " + ex);
		} finally {
			try {
				if (rs != null) 
					rs.close();							
				if (pstmt != null) 
					pstmt.close();				
				if (conn != null) 
					conn.close();
			} catch (Exception ex) {
				throw new RuntimeException(ex.getMessage());
			}			
		}
	}
	
	public float rateAvg(int proNum ) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		float avg = 0;
		try {
			conn = getConnection();

			String sql = "select avg(rate)  from review where prono = " + proNum;
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			

			if (rs.next())
				avg = rs.getFloat("avg(rate)");
				
		} catch (Exception ex) {
			System.out.println("rateAvg() : " + ex);
		} finally {
			try {
				if (rs != null) 
					rs.close();							
				if (pstmt != null) 
					pstmt.close();				
				if (conn != null) 
					conn.close();
			} catch (Exception ex) {
				throw new RuntimeException(ex.getMessage());
			}			
		}
		return avg;
	}
	public int privateReviewCount(int memNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int count = 0;
		
		String query = "select count(*) from review where memNo = " + memNo;
		try {
			
			
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			if (rs.next())
				count = rs.getInt(1);
			System.out.println(count);
		} catch (Exception ex)	{
			System.out.println("리스트 카운트 에러 " + ex);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception ex) {
				throw new RuntimeException(ex.getMessage());
			}
		}
		return count;
	
	}
	public List<ReviewVo> getPrivateList(int page, int limit, int memNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int total_record = privateReviewCount(memNo);
		int start = (page - 1) * limit;
		int index = start + 1;
		String sql = "select * from review where memno = " + memNo + "and revavail = 1 order by revdate desc";
		
		
		List<ReviewVo> list = new ArrayList<ReviewVo>();
			
		
		try {
			conn = getConnection();
			 
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = pstmt.executeQuery();
			while (rs.absolute(index)) {
				ReviewVo vo = new ReviewVo();
				vo.setRevNo(rs.getInt("RevNo"));
				vo.setMemNo(rs.getInt("MemNo"));
				vo.setProNo(rs.getInt("proNo"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setRevDate(rs.getString("REVDATE"));
				vo.setRate(rs.getInt("rate"));
				vo.setRevHit(rs.getInt("revhit"));
				vo.setFile1(rs.getString("file1"));
				vo.setFile2(rs.getString("file2"));
				vo.setProName(rs.getString("proName"));
				
			
				list.add(vo);
				if (index < (start + limit) && index <= total_record)
					index++;
			
				else 
					break;
			}
			
		} catch (Exception ex) {
			System.out.println("getList() error : " + ex);
		} finally {
			try {
				if (rs != null) 
					rs.close();							
				if (pstmt != null) 
					pstmt.close();				
				if (conn != null) 
					conn.close();
			} catch (Exception ex) {
				throw new RuntimeException(ex.getMessage());
			}			
		}
		return list;
	}
	
}
