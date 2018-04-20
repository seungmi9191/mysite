package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.GuestVo;

public class GuestbookDao {

	public void insert(GuestVo vo) {
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		// ResultSet rs = null;
		int count;

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "insert into guestbook values( seq_gusetbook_no.nextval, ?, ?, ?, sysdate)";
			pstmt = conn.prepareStatement(query);

			// 쿼리문의 ?를 문자열로 변경해주기
			pstmt.setString(1, vo.getName()); // (순서,밖에서 받오는 값)
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getContent());
			// pstmt.setString(4, vo.getRegDate());

			// db날려주기 - 성공 1, 실패0
			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건 등록");
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 " + e);
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
	} 
	
	public void list_delete(String no) {
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count;
		
		
			// 1. JDBC 드라이버 (Oracle) 로딩
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				 // 2. Connection 얻어오기
				String url = "jdbc:oracle:thin:@localhost:1521:xe";
				conn = DriverManager.getConnection(url, "webdb", "webdb");
				
				// 3. SQL문 준비 / 바인딩 / 실행
				String query = "delete from guestbook where no="+no;
				pstmt = conn.prepareStatement(query);
				
				pstmt.executeUpdate();
				
				//쿼리문의 ?를 문자열로 변경해주기
				//pstmt.setString(1, no); //(순서,밖에서 받오는 값)
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 

	}

	public List<GuestVo> getList() {
		//0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<GuestVo> list = new ArrayList<GuestVo>();
		
		try {
			 // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			// 3. SQL문 준비 / 바인딩 / 실행
			String query ="select no, name , password, content, reg_date "
						 + "from guestbook "
					     + "order by no desc ";
			pstmt = conn.prepareStatement(query);
			
			rs = pstmt.executeQuery();
		    
			//db날려주기 - 성공 1, 실패0
			while(rs.next()) {
				int no = rs.getInt("no");
				String name = rs.getString("name");
				String password = rs.getString("password");
				String content = rs.getString("content");
				String reg_date = rs.getString("reg_date");
				
				GuestVo vo = new GuestVo(no, name, password, content, reg_date);
				list.add(vo);
			}
			
			  // 4.결과처리

		} catch (ClassNotFoundException e) {
		    System.out.println("error: 드라이버 로딩 실패 " + e);
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} finally {
		   
			 // 5. 자원정리
		    try {
		       if (rs != null) {
		           rs.close();
		       }                
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
}
