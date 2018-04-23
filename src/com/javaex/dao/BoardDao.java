package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.BoardVo;

public class BoardDao {

	public List<BoardVo> getList() {
		
		//0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BoardVo> list = new ArrayList<BoardVo>();
		
		try {
			//1. JDBC 드라이버(Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			//3. SQL문 준비/바인딩/실행
			String query = "select b.no, b.title, b.hit, b.reg_date, b.user_no, u.name "
							+ "from board b, users u "
							+ "where b.user_no = u.no "
							+ "order by no desc";
			pstmt = conn.prepareStatement(query);
			
			rs = pstmt.executeQuery();
			
			//4. 결과처리
			while(rs.next()) {
				int no = rs.getInt("no");
				String title = rs.getString("title");
				int hit = rs.getInt("hit");
				String regDate = rs.getString("reg_date");
				int userNo = rs.getInt("user_no");
				String userName = rs.getString("name");
				
				BoardVo vo = new BoardVo(no,title,hit,regDate,userNo, userName);
				list.add(vo);
			}
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
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
	
	public BoardVo getBoard(int no) {
		
		//0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVo boardVo = null;
		
		try {
			//1. JDBC 드라이버(Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			//3. SQL문 준비/바인딩/실행
			String query =  "select b.no, b.title, b.content, b.hit, b.reg_date, b.user_no, u.name "
							+ "from board b, users u "
							+ "where b.user_no = u.no "
							+ "and b.no = ?";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			//4. 결과처리
			while(rs.next()) {
				String title = rs.getString("title");
				String content = rs.getString("content");
				int hit = rs.getInt("hit");
				String regDate = rs.getString("reg_date");
				int userNo = rs.getInt("user_no");
				String userName = rs.getString("name");
				
				boardVo = new BoardVo(no, title, content, hit, regDate, userNo, userName);
			}
			
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
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
		System.out.println(boardVo);
		return boardVo;
	}
	
	public int insert(BoardVo vo) {
		//0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0;
		
		try {
			//1. JDBC 드라이버(Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "insert into board values (seq_board_no.nextval, ?, ?, 0, sysdate, ?)";
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setInt(3, vo.getUserNo());

			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건 등록");

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
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
	
	public int delete(int no) {
		//0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0;
		
		try {
			//1. JDBC 드라이버(Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "delete from board where no = ?";
			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, no);

			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건 삭제");
			
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		return count;
	}
	
	public int update(BoardVo vo) {
		//0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0;
		
		try {
			//1. JDBC 드라이버(Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "update board set title = ?, content = ? where no = ? ";
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setInt(3, vo.getNo());

			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건 수정");
			
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		return count;
	}
}
