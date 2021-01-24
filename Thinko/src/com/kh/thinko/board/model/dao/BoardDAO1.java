package com.kh.thinko.board.model.dao;

import static com.kh.thinko.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.kh.thinko.board.model.vo.VBoard;

public class BoardDAO1 {

	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rset = null;

	private Properties prop = null;

	public BoardDAO1() {
		String fileName = BoardDAO1.class.getResource("/com/kh/thinko/sql/board/board-query1.xml").getPath();
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream(fileName));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 게시글 상세조회 DAO
	 * 
	 * @param conn
	 * @param boardNo
	 * @return board
	 * @throws Exception
	 */
	public VBoard selectBoard(Connection conn, int boardNo) throws Exception {
		VBoard board = null;

		String query = prop.getProperty("selectBoard");

		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, boardNo);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				board = new VBoard(rset.getInt("BOARD_NO"), rset.getString("BOARD_TITLE"),
						rset.getString("BOARD_CONTENT"), rset.getString("MEMBER_ID"), rset.getInt("READ_COUNT"),
						rset.getTimestamp("BOARD_CREATE_DT"), rset.getTimestamp("BOARD_MODIFY_DT"),
						rset.getString("BOARD_STATUS"), rset.getString("BOARD_TYPE_NM"),
						rset.getString("BOARD_CATEGORY_NM"), rset.getInt("LIKE_COUNT"), rset.getInt("REPLY_COUNT"));
			}

		} finally {
			close(rset);
			close(pstmt);
		}

		return board;
	}

	/**  조회수 증가 DAO
	 * @param conn
	 * @param boardNo
	 * @return result
	 * @throws Exception
	 */
	public int increaseReadCount(Connection conn, int boardNo) throws Exception {
		int result = 0;
		
		String query = prop.getProperty("increaseReadCount");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	/** 게시글 신고 기능 DAO
	 * @param conn
	 * @param boardNo
	 * @param memberId
	 * @return result
	 * @throws Exception
	 */
	public int reportBoard(Connection conn, int boardNo, int memberNo, int reportCategoryNo) throws Exception {
		int result = 0;
		
		String query = prop.getProperty("reportBoard");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			pstmt.setInt(2, boardNo);
			pstmt.setInt(3, reportCategoryNo);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	/** 중복 신고 검사 DAO
	 * @param conn
	 * @param boardNo
	 * @param memberNo
	 * @return findReport
	 * @throws Exception
	 */
	public int findReport(Connection conn, int boardNo, int memberNo) throws Exception {
		int findReport = 0;
		
		String query = prop.getProperty("findReport");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			pstmt.setInt(2, boardNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				findReport = rset.getInt(1);
			}
			
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return findReport;
	}

	/** 좋아요 중복검사 DAO
	 * @param conn
	 * @param boardNo
	 * @param memberNo
	 * @return checkLike
	 * @throws Exception
	 */
	public int checkLike(Connection conn, int boardNo, int memberNo) throws Exception {
		int checkLike = 0;
		
		String query = prop.getProperty("checkLike");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			pstmt.setInt(2, memberNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				checkLike = rset.getInt(1);
			}
			
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return checkLike;
	}
	 
	/** 좋아요 상태 조회 DAO
	 * @param conn
	 * @param boardNo
	 * @param memberNo
	 * @return fl
	 * @throws Exception
	 */
	public String getLikeFl(Connection conn, int boardNo, int memberNo) throws Exception {
		String fl = null;
		
		String query = prop.getProperty("getLikeFl");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			pstmt.setInt(2, memberNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				fl = rset.getString(1);
			}
			
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return fl;
	}

	
	/** 좋아요 상태 N으로 DAO
	 * @param conn
	 * @param boardNo
	 * @param memberNo
	 * @return result
	 * @throws Exception
	 */
	public int updateLikeToN(Connection conn, int boardNo, int memberNo) throws Exception{
		int result = 0;
		
		String query = prop.getProperty("updateLikeToN");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			pstmt.setInt(2, memberNo);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	
	/** 좋아요 상태 Y로 DAO
	 * @param conn
	 * @param boardNo
	 * @param memberNo
	 * @return result
	 * @throws Exception
	 */
	public int updateLikeToY(Connection conn, int boardNo, int memberNo) throws Exception{
		int result = 0;
		
		String query = prop.getProperty("updateLikeToY");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			pstmt.setInt(2, memberNo);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	/**
	 * @param conn
	 * @param boardNo
	 * @param memberNo
	 * @return insertLike
	 * @throws Exception
	 */
	public int insertLike(Connection conn, int boardNo, int memberNo) throws Exception {
		int insertLike = 0;
		
		String query = prop.getProperty("insertLike");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			pstmt.setInt(2, boardNo);
			
			insertLike = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return insertLike;
	}

	/** 위지청보 가져오기 DAO
	 * @param conn
	 * @param boardNo
	 * @return map
	 * @throws Exception
	 */
	public Map<String, Object> selectLocation(Connection conn, int boardNo) throws Exception {
		Map<String, Object> map = null;
		
		String query = prop.getProperty("selectLocation");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			
			rset = pstmt.executeQuery();
			
			map = new HashMap<String, Object>();
			
			if(rset.next()) {
				map.put("lat", rset.getString("LAT"));
				map.put("lng", rset.getString("LNG"));
			}
			
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return map;
	}

}
