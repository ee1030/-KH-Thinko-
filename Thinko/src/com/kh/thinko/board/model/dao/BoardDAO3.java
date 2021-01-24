package com.kh.thinko.board.model.dao;

import static com.kh.thinko.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.kh.thinko.board.model.vo.Attachment;


/**게시글 등록, 수정, 삭제 DAO
 * @author jeonga
 *
 */
public class BoardDAO3 {
	
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rset = null;
	
	private Properties prop = null;
	
	   public BoardDAO3(){
		      String fileName = BoardDAO3.class.getResource("/com/kh/thinko/sql/board/board-query3.xml").getPath();
		      try {
		         prop = new Properties();
		         prop.loadFromXML(new FileInputStream(fileName)); 
		      } catch (Exception e) {
		         e.printStackTrace();
		      }
		   }

		/** 다음 게시글 번호 조회 DAO
		 * @param conn
		 * @return boardNo
		 * @throws Exception
		 */
		public int selectNextNo(Connection conn) throws Exception {
			int boardNo = 0;
			
			String query = prop.getProperty("selectNextNo");
			
			try {
				stmt = conn.createStatement();
				rset = stmt.executeQuery(query);
				
				if(rset.next()) {
					boardNo = rset.getInt(1);
				}
				
			} finally {
				close(rset);
				close(stmt);
			}
			
			return boardNo;
		}

		/** 게시글 삽입 DAO
		 * @param conn
		 * @param map
		 * @return result
		 */
		public int insertBoard(Connection conn, Map<String, Object> map) throws Exception{
			int result = 0;
			
			String query = prop.getProperty("insertBoard");
			
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, (int)map.get("boardNo"));
				pstmt.setString(2, (String)map.get("boardTitle"));
				pstmt.setString(3, (String)map.get("boardContent"));
				pstmt.setInt(4, (int)map.get("boardWriter"));
				pstmt.setInt(5, (int)map.get("boardType"));
				pstmt.setInt(6, (int)map.get("categoryCode"));
				
				result = pstmt.executeUpdate();
				
				if(result > 0) {
					commit(conn);
				}else {
					rollback(conn);
				}
				
			} finally {
				close(pstmt);
			}
			
			return result;
		}

		/** 파일 정보 삽입 DAO
		 * @param conn
		 * @param at
		 * @return result
		 * @throws Exception
		 */
		public int insertAttachment(Connection conn, Attachment at) throws Exception{
			int result = 0;
			
			String query = prop.getProperty("insertAttachment");
			
			try {
				pstmt = conn.prepareStatement(query);
				
				pstmt.setString(1, at.getFilePath());
				pstmt.setString(2, at.getFileName());
				pstmt.setInt(3, at.getFileLevel());
				pstmt.setInt(4, at.getParentBoardNo());
				
				result = pstmt.executeUpdate();
				
			} finally {
				close(pstmt);
			}
			
			return result;
		}

		/** 게시글 수정 DAO
		 * @param conn
		 * @param map
		 * @return result
		 * @throws Exception
		 */
		public int updateBoard(Connection conn, Map<String, Object> map) throws Exception {
		
			int result = 0;
			
			String query = prop.getProperty("updateBoard");
			
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, (String)map.get("boardTitle") );
				pstmt.setString(2, (String)map.get("boardContent") );
				pstmt.setInt(3, (int)map.get("categoryCode"));
				pstmt.setInt(4, (int)map.get("boardNo"));
				
				result = pstmt.executeUpdate();
			} finally {
				close(pstmt);
			}
			
			return result;
		}


		/** 파일 정보 불러오기 DAO
		 * @param conn
		 * @param boardNo
		 * @return fList
		 * @throws Exception
		 */
		public List<String> selectBoardFiles(Connection conn, int boardNo) throws Exception{
			List<String> fList = null;
			
			String query = prop.getProperty("selectBoardFiles");
			
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, boardNo);
				rset = pstmt.executeQuery();
				fList = new ArrayList<String>();
				
				while(rset.next()) {
				
					String at = rset.getString("ATTACHMENT_NM");
					
					fList.add(at);
				}
			} finally {
				close(rset);
				close(pstmt);
			}
			
			return fList;

		}

		/** 파일 정보 수정 DAO
		 * @param conn
		 * @param boardNo
		 * @return result
		 * @throws Exception
		 */
		public int updateAttachment(Connection conn, Attachment at) throws Exception{
			int result = 0;
			
			String query = prop.getProperty("updateAttachment");
			
			try {
				pstmt = conn.prepareStatement(query);
				
				pstmt.setString(1, at.getFilePath());
				pstmt.setString(2, at.getFileName());
				pstmt.setInt(3, at.getFileLevel());
				pstmt.setInt(4, at.getParentBoardNo());
				
				result = pstmt.executeUpdate();
				
			} finally {
				close(pstmt);
			}
			
			return result;
		}
		/** 파일 정보 삭제 DAO
		 * @param conn
		 * @param boardNo
		 * @return result
		 * @throws Exception
		 */
		public int deleteAttachment(Connection conn, int boardNo) throws Exception {
			int result = 0;
			String query = prop.getProperty("deleteAttachment");
			
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, boardNo);
				result = pstmt.executeUpdate();
				
			} finally {
				close(pstmt);
			}
			
			return result;
		}
		
		/** 게시글 삭제 DAO
		 * @param conn
		 * @param boardNo
		 * @return result
		 * @throws Exception
		 */
		public int updateBoardStatus(Connection conn, int boardNo) throws Exception{
			
			int result = 0;
			String query = prop.getProperty("updateBoardStatus");
			
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, boardNo);
				result = pstmt.executeUpdate();
				
			} finally {
				close(pstmt);
			}
			
			return result;
		}

		/** 위치 좌표 삽입 DAO
		 * @param conn
		 * @param map
		 * @param boardNo
		 * @return insertLocation
		 * @throws Exception
		 */
		public int insertLocation(Connection conn, Map<String, Object> map, int boardNo) throws Exception {
			int insertLocation = 0;
			
			String query = prop.getProperty("insertLocation");
			
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, (String)map.get("lat"));
				pstmt.setString(2, (String)map.get("lng"));
				pstmt.setInt(3, boardNo);
				
				insertLocation = pstmt.executeUpdate();
				
			} finally {
				close(pstmt);
			}
				
			return insertLocation;
		}

		

}
