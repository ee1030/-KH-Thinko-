package com.kh.thinko.board.model.dao;

import static com.kh.thinko.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.thinko.board.model.vo.VBoard;

public class MainDAO {

	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rset = null;
	private Properties prop = null;
	
	public MainDAO(){
	      String fileName = MainDAO.class.getResource("/com/kh/thinko/sql/main/main-query.xml").getPath();
	      try {
	         prop = new Properties();
	         prop.loadFromXML(new FileInputStream(fileName)); 
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	   }
	// 메인 
	public List<VBoard> publicBoardList(Connection conn, int type) throws Exception{
		
		List<VBoard> bList = null;
		
		String query = prop.getProperty("publicBoardList");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			 
			bList = new ArrayList<VBoard>();
			
			while(rset.next()) {
				VBoard board = new VBoard();
				board.setBoardNo(rset.getInt("BOARD_NO"));
				board.setBoardTitle(rset.getString("BOARD_TITLE"));
				board.setMemberId(rset.getString("MEMBER_ID"));
				board.setReplyCount(rset.getInt("REPLY_COUNT"));
				board.setLikeCount(rset.getInt("LIKE_COUNT"));
				board.setReadCount(rset.getInt("READ_COUNT"));
				board.setBoardCreateDate(rset.getTimestamp("BOARD_CREATE_DT"));
				
				bList.add(board);
			}
			
		} finally {
			close(rset);
			close(stmt);
		}
		
		return bList;
	}

	public List<VBoard> otherBoardList(Connection conn, int type) throws Exception{
		
		List<VBoard> bList = null;
		
		String query = prop.getProperty("otherBoardList");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, type);
			
			rset = pstmt.executeQuery();
			
			bList = new ArrayList<VBoard>();
			
			while(rset.next()) {
				VBoard board = new VBoard();
				board.setBoardNo(rset.getInt("BOARD_NO"));
				board.setBoardTitle(rset.getString("BOARD_TITLE"));
				board.setMemberId(rset.getString("MEMBER_ID"));
				board.setReplyCount(rset.getInt("REPLY_COUNT"));
				board.setLikeCount(rset.getInt("LIKE_COUNT"));
				board.setReadCount(rset.getInt("READ_COUNT"));
				board.setBoardCreateDate(rset.getTimestamp("BOARD_CREATE_DT"));
				
				bList.add(board);
			}
			
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return bList;
	}

	
	
	
}
