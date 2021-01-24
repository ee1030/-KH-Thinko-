package com.kh.thinko.search.model.dao;

import static com.kh.thinko.common.JDBCTemplate.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.kh.thinko.board.model.vo.PageInfo2;
import com.kh.thinko.board.model.vo.VBoard;

public class SearchDAO {
	
	   private Statement stmt = null;
	   private PreparedStatement pstmt = null;
	   private ResultSet rset = null;
	   

	public int getListCount(Connection conn, String condition) throws Exception {

		int listCount = 0;
		
		String query = "SELECT COUNT(*) FROM V_BOARD WHERE BOARD_STATUS = 'Y' AND" + condition;
		System.out.println(query);		
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {
				listCount = rset.getInt(1);
			}
			
			
		} finally {
			close(rset);
			close(stmt);
		
		}
		
		return listCount;
	}
	
	

	public int getListTypeCount(Connection conn, String condition, int type) throws Exception {
		
		int listCount = 0;
		
		String query = "SELECT COUNT(*) FROM V_BOARD WHERE BOARD_STATUS = 'Y' AND BOARD_TYPE_NO = ? AND (" + condition + ")";
	
		System.out.println(query);
		System.out.println(type);
		
		try {
			pstmt = conn.prepareStatement(query);
	         
	         pstmt.setInt(1, type);
	         
	         rset = pstmt.executeQuery();
	         
			if(rset.next()) {
				listCount = rset.getInt(1);
			}
			
			
		} finally {
			close(rset);
			close(stmt);
			
		}
		
		return listCount;
		
	}

	public List<VBoard> searchBoardList(Connection conn, PageInfo2 pInfo, String condition, Map<String, Object> map) throws Exception {

		  List<VBoard> bList = null; // 최종 조회 결과를 담을 변수
	      
	      String query = 
	            "SELECT * FROM" + 
	            "    (SELECT ROWNUM RNUM , V.*" + 
	            "    FROM" + 
	            "        (SELECT * FROM V_BOARD " + 
	            "        WHERE " + condition +
	            "        AND BOARD_STATUS = 'Y' ORDER BY BOARD_NO DESC) V )" + 
	            "WHERE RNUM BETWEEN ? AND ?";
	      
	      
	      try {
	         // SQL 구문에 사용될 위치 홀더값 생성
	         int startRow = (pInfo.getCurrentPage() -1) * pInfo.getLimit() +1;
	         int endRow = startRow + pInfo.getLimit()-1;
	         
	         pstmt = conn.prepareStatement(query);
	         
	         pstmt.setInt(1, startRow);
	         pstmt.setInt(2, endRow);
	         
	         rset = pstmt.executeQuery();
	         
	         bList = new ArrayList<VBoard>();
	         
	     	while(rset.next()) {
				VBoard vBoard = new VBoard(rset.getInt("BOARD_NO"),
						rset.getString("BOARD_TITLE"),
						rset.getString("BOARD_CONTENT"),
						rset.getString("MEMBER_ID"),
						rset.getInt("READ_COUNT"),
						rset.getTimestamp("BOARD_CREATE_DT"),
						rset.getInt("LIKE_COUNT"),
						rset.getInt("REPLY_COUNT"));
						
				
				bList.add(vBoard);
	         }
	         
	      } finally {
	         close(rset);
	         close(pstmt);
	      }
	      
	      return bList;
	   }


	public List<VBoard> searchTypeBoardList(Connection conn, PageInfo2 pInfo, String condition, Map<String, Object> map) throws Exception {
		
		List<VBoard> bList = null; // 최종 조회 결과를 담을 변수
	      
	      String query = 
	            "SELECT * FROM" + 
	            "    (SELECT ROWNUM RNUM , V.*" + 
	            "    FROM" + 
	            "        (SELECT * FROM V_BOARD " + 
	            "        WHERE (" + condition + ") " +
	            "		AND BOARD_TYPE_NO = ?" +
	            "        AND BOARD_STATUS = 'Y' ORDER BY BOARD_NO DESC) V )" + 
	            "WHERE RNUM BETWEEN ? AND ?";
	      
	      try {
	         // SQL 구문에 사용될 위치 홀더값 생성
	         int startRow = (pInfo.getCurrentPage() -1) * pInfo.getLimit() +1;
	         int endRow = startRow + pInfo.getLimit()-1;
	         
	         pstmt = conn.prepareStatement(query);
	         
	         pstmt.setInt(1, (int)map.get("type"));
	         pstmt.setInt(2, startRow);
	         pstmt.setInt(3, endRow);
	         
	         rset = pstmt.executeQuery();
	         
	         bList = new ArrayList<VBoard>();
	         
	     	while(rset.next()) {
				VBoard vBoard = new VBoard(rset.getInt("BOARD_NO"),
						rset.getString("BOARD_TITLE"),
						rset.getString("BOARD_CONTENT"),
						rset.getString("MEMBER_ID"),
						rset.getInt("READ_COUNT"), 
						rset.getTimestamp("BOARD_CREATE_DT"),
						rset.getInt("LIKE_COUNT"));
		
				
				bList.add(vBoard);
	         }
	         
	      } finally {
	         close(rset);
	         close(pstmt);
	      }
	      
	      return bList;
		
	}



	}


