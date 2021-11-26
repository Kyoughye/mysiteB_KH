package com.javaex.dao;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.ProductVo;



public class ProductDaoImpl implements ProductDao {
	
	
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
	
	
	public ArrayList<ProductVo> getList() {
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ProductVo> list = new ArrayList<ProductVo>();

		try {
			conn = getConnection();

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "select * from product order by prono";
			pstmt = conn.prepareStatement(query);

			rs = pstmt.executeQuery();
			// 4.결과처리
			while (rs.next()) {
				ProductVo vo = new ProductVo();
				vo.setProNo(rs.getInt("proNo"));
				vo.setProName(rs.getString("proName"));
				vo.setProCateg(rs.getString("proCateg"));
				vo.setProStock(rs.getInt("proStock"));
				vo.setProPrice(rs.getInt("proPrice"));
				vo.setProDesc(rs.getString("proDesc"));
				vo.setProOnSale(rs.getInt("proOnSale"));
				vo.setProFileName(rs.getString("proFileName"));
				vo.setProDate(rs.getString("proDate"));
				vo.setProHit(rs.getInt("proHit"));
				
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
	
	public int getListCount() {
		return getListCount("prono","");
	}
	
	public int getListCount(String searchfrom, String kwd) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int count = 0;
		
		String query;
		
		if (kwd== null)
			query = "select count(*) from product";
			
		else
			query = "select count(*) from product where "+searchfrom+" like '%"+ kwd + "%'";
			
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			if (rs.next())
				count = rs.getInt("count");
			System.out.println(count);
		} catch (Exception ex)	{
			System.out.println("getListCount() : " + ex);
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
	
	
	public ArrayList<ProductVo> getList(int page, String searchFrom, String kwd, String orderBy){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<ProductVo> list = new ArrayList<ProductVo>();
		String sql;
		try {
			conn = getConnection();
			 
			if (searchFrom == "" || kwd == "") {
				sql = "select pdt.*\r\n"
						+ "from (select rownum num, pd.* "
						+ "from (select * from product)pd "
						+ ")pdt "
						+ "where num between ? and ? ";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, 1 + (page - 1) * 5);
				pstmt.setInt(2, page * 5);
				rs = pstmt.executeQuery();
			}
			else { sql = "select pdt.* \r\n"
					+ "from (select rownum num, pd.*\r\n"
					+ "from (select * from product\r\n"
					+ "where instr("+searchFrom+", ?) > 0)pd\r\n"
					+ "order by "+orderBy+")pdt\r\n"
					+ "where num between ? and ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, kwd);
			pstmt.setInt(2, 1 + (page - 1) * 5);
			pstmt.setInt(3, page * 5);
			rs = pstmt.executeQuery();
			}
			while (rs.next()) {
				ProductVo vo = new ProductVo();
				vo.setProNo(rs.getInt("proNo"));
				vo.setProName(rs.getString("proName"));
				vo.setProCateg(rs.getString("proCateg"));
				vo.setProStock(rs.getInt("proStock"));
				vo.setProPrice(rs.getInt("proPrice"));
				vo.setProDesc(rs.getString("proDesc"));
				vo.setProOnSale(rs.getInt("proOnSale"));
				vo.setProFileName(rs.getString("proFileName"));
				vo.setProDate(rs.getString("proDate"));
				vo.setProHit(rs.getInt("proHit"));
				
				list.add(vo);

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
