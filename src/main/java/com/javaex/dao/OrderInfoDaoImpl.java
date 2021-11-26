package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.OrderInfoVo;

public class OrderInfoDaoImpl implements OrderInfoDao {

	
	
	public OrderInfoDaoImpl() {
		
	}


	private static OrderInfoDao instance;
	@Override
	public int delete(int orderNo) {
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0;
	
		try {
			conn = getConnection();
	
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "delete from orderInfo o, orderhasproduct op "
					     + "where o.orderNo = op.orderNo "
					     + "and o.orderNo = ? ";
			pstmt = conn.prepareStatement(query);
	
			pstmt.setInt(1, orderNo);
	
			count = pstmt.executeUpdate();
	
			// 4.결과처리
			System.out.println(count + "건 orderInfo 삭제");
	
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

	public static OrderInfoDao getInstance() {
		if (instance == null)
			instance = new OrderInfoDaoImpl();
		return instance;
	}
	
	
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
	public int insert(OrderInfoVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0;

		try {
			conn = getConnection();

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "insert into orderInfo values (seq_orderinfo_no.nextval, to_char(sysdate, 'YY-MM-DD HH24:MI') , ?, ? )";
			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, vo.getTotalPrice());
			pstmt.setInt(2, vo.getMemNo());

			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "  건 orderInfo 등록");

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
	public List<OrderInfoVo> getDetail(int orderNo) {
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<OrderInfoVo> list = new ArrayList<OrderInfoVo>();
		try {
			conn = getConnection();

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "select o.orderno , p.proName , o.orderdate, p.proprice "
					+ "from orderhasproduct op , orderinfo o, product p , regmember r "
					+ "where op.orderno = o.orderno " + "and op.prono = p.prono " + "and o.memno = r.memno "
					+ "and o.orderno = ?";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, orderNo);

			rs = pstmt.executeQuery();
			// 4.결과처리
			while (rs.next()) {
				int orderNo1 = rs.getInt("orderNo");
				String proName = rs.getString("proName");
				String orderDate = rs.getString("orderDate");
				int proPrice = rs.getInt("proPrice");

				OrderInfoVo vo = new OrderInfoVo(orderNo1, proName, orderDate, proPrice);
				list.add(vo);
			}

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

		return list;

	}

	@Override
	public List<OrderInfoVo> getList(int page , int limit, int memNo , String searchDate1, String searchDate2 ) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int total_record = getListCount(searchDate1,searchDate2 ,memNo);
		int start = (page - 1) * limit;
		int index = start + 1;
		String sql;
		
		if(searchDate1 == null || searchDate1 == "") {
			sql =     "select o.orderno ,  sum(p.proprice) price "
					+ "from orderhasproduct op , orderinfo o, product p , regmember r " + " where op.orderno = o.orderno "
					+ "and op.prono = p.prono " 
					+ "and o.memno = r.memno " 
					+ "and r.memno = " + memNo
					+ " group by o.orderno ";
		}else {
			sql = "select o.orderno ,  sum(p.proprice) price "
					+ "from orderhasproduct op , orderinfo o, product p , regmember r " + "where op.orderno = o.orderno "
					+ "and op.prono = p.prono " 
					+ "and o.memno = r.memno " 
					+ "and r.memno = " + memNo
					+ " and o.orderdate BETWEEN "  + searchDate1
					+ " and " + searchDate2
					+ " group by o.orderno";
			
		}
		

		List<OrderInfoVo> list = new ArrayList<OrderInfoVo>();

		try {
			conn = getConnection();

			// 3. SQL문 준비 / 바인딩 / 실행

			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				int orderNo = rs.getInt("orderNo");
				String proName = repProName(orderNo);
				String orderDate = repDate(orderNo);
				int proPrice = rs.getInt("price");
				System.out.println(orderNo  + "  "+ orderDate + proName + proPrice + "dmdjdjjdjdjdjdjdjjdjdjj ");
				OrderInfoVo vo = new OrderInfoVo(orderNo, proName, orderDate, proPrice);
				list.add(vo);
				if (index < (start + limit) && index <= total_record)
					index++;
				else
					break;
			}
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

		return list;

	}
	
	public int detailCount(int orderInfono) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0;
		ResultSet rs = null;

		try {
		  conn = getConnection();
		  
		  
		  
      
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "SELECT COUNT(*) no "
					+ "from orderhasproduct op , orderinfo o, product p , regmember r " 
					+ "where op.orderno = o.orderno "
					+ "and op.prono = p.prono " 
					+ "and o.memno = r.memno " 
					+ "and r.memno = " + orderInfono;
			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, orderInfono);
			
			rs = pstmt.executeQuery();

			
			// 4.결과처리
			while (rs.next()) {
				
				
				count = rs.getInt("no");
			
			}
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
	
public String repProName(int orderInfono) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String repProName = null;

		try {
		  conn = getConnection();
		  
		  
		  
      
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "select p.proname "
					     + "from orderhasproduct op , orderinfo o, product p , regmember r "
					     + "where op.orderno = o.orderno "
					     + "and op.prono = p.prono "
						 + "and o.memno = r.memno "
						 + "and o.orderno = ? "
						 + "and rownum = 1 "
						 + "order by p.proprice";
			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, orderInfono);
			
			rs = pstmt.executeQuery();

			
			// 4.결과처리
			while (rs.next()) {
				repProName = rs.getString("proname");
			}
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
		return repProName;

	}
public String repDate(int orderInfono) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String repDate = null;

		try {
		  conn = getConnection();
		  
		  
		  
      
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "select o.orderDate "
					     + "from orderhasproduct op , orderinfo o, product p , regmember r "
					     + "where op.orderno = o.orderno "
					     + "and op.prono = p.prono "
						 + "and o.memno = r.memno "
						 + "and o.orderno = ? "
						 + "and rownum = 1 "
						 + "order by p.proprice";
			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, orderInfono);
			
			rs = pstmt.executeQuery();

			
			// 4.결과처리
			while (rs.next()) {

				repDate = rs.getString("orderDate");
			}
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
		return repDate;

	}
	
	
	@Override
	public int getListCount(String searchDate1, String searchDate2, int memNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		int x = 0;
		if (searchDate1 == null && searchDate2 == null)
			sql = "select  count(*) from orderinfo where memNo = " + memNo;
		else
			sql = "SELECT   count(*) FROM (select b.no, b.title, b.hit, b.reg_date, b.user_no, u.name "
					+ "from board b join users u "
					+ "on b.user_no = u.no "
					+ "where "+searchDate1+" Like '%"+searchDate2+"%' order by no desc)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) 
				x = rs.getInt(1);
			
		} catch (Exception ex) {
			System.out.println("getListCount() 에러: " + ex);
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
		return x;
	
	}
	
	public int getProductCount(int memNo, int orederNo ) {
        Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int x = 0;
		String sql =  "select  count(o.orderNo) "
					+ "from orderhasproduct op , orderinfo o, product p , regmember r " + "where op.orderno = o.orderno "
					+ "and op.prono = p.prono " 
					+ "and o.memno = r.memno " 
					+ "and r.memno = " + memNo
					+ "and o.oder"
					+ " group by o.orderno";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) 
				x = rs.getInt(1);
			
		} catch (Exception ex) {
			System.out.println("getListCount() 에러: " + ex);
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
		return x;
	
	} 
	
}
