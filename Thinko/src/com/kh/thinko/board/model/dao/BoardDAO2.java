package com.kh.thinko.board.model.dao;

import static com.kh.thinko.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.thinko.board.model.vo.Attachment;
import com.kh.thinko.board.model.vo.Board;
import com.kh.thinko.board.model.vo.PageInfo;
import com.kh.thinko.board.model.vo.VBoard;

public class BoardDAO2 {

	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rset = null;

	private Properties prop = null;

	public BoardDAO2() {
		String fileName = BoardDAO2.class.getResource("/com/kh/thinko/sql/board/board-query2.xml").getPath();
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream(fileName));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 전체 게시글 수 반환 DAO
	 * 
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public int getListCount(Connection conn, int type) throws Exception {

		int listCount = 0;

		String query = prop.getProperty("getListCount");

		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, type);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				listCount = rset.getInt(1);
			}

		} finally {
			close(rset);
			close(stmt);

		}

		return listCount;

	}

	public List<VBoard> selectBoardList(Connection conn, PageInfo pInfo, int type) throws Exception {

		List<VBoard> bList= null;
		
		String query = prop.getProperty("selectBoardList");
		
		try {
			
			int startRow = (pInfo.getCurrentPage() -1) * pInfo.getLimit() + 1; 
			int endRow = startRow + pInfo.getLimit() -1 ;
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, type);
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
						rset.getInt("LIKE_COUNT"),
						rset.getInt("REPLY_COUNT"));
						
				vBoard.setBoardCategoryName(rset.getString("BOARD_CATEGORY_NM"));
				bList.add(vBoard);
			}
			
			
			
		} finally {

			close(rset);
			close(pstmt);
		
		}
		
		
		return bList;
	}

	/** 인기글 게시판 목록 조회
	 * @param conn
	 * @return pList
	 * @throws ExceptionJ
	 */
	public List<VBoard> selectPopularPosts(Connection conn) throws Exception{
		
		List<VBoard> pList = null;
		
		String query = prop.getProperty("selectPopularPosts");
		
		try {
			stmt = conn.createStatement();
			
			rset = stmt.executeQuery(query);
			
			pList = new ArrayList<VBoard>();
			
			while(rset.next()) {
				VBoard vBoard = new VBoard(rset.getInt("BOARD_NO"),
						rset.getString("BOARD_TITLE"),
						rset.getString("BOARD_CONTENT"),
						rset.getString("MEMBER_ID"),
						rset.getInt("READ_COUNT"), 
						rset.getTimestamp("BOARD_CREATE_DT"),
						rset.getInt("LIKE_COUNT"),
						rset.getInt("REPLY_COUNT"));
						vBoard.setBoardCategoryName(rset.getString("BOARD_CATEGORY_NM"));
				pList.add(vBoard);
			}
			
		}finally {
			close(rset);
			close(stmt);
		}
		
		return pList;
	}

	public List<Attachment> selectThumbnailList(Connection conn, PageInfo pInfo) throws Exception {

		List<Attachment> fList = null;
		
		String query = prop.getProperty("selectThumbnailList");
		
		try {
			// 위치 홀더에 들어갈 시작행, 끝 행 번호 계산
			int startRow = (pInfo.getCurrentPage()-1) * pInfo.getLimit() + 1;
			int endRow = startRow + pInfo.getLimit() -1;
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
					
			rset = pstmt.executeQuery();
			
			// 조회 결과를 저장할 List 생성
			fList = new ArrayList<Attachment>();
			
			while(rset.next()) {
				
				Attachment at = new Attachment();
				at.setFileName(rset.getString("ATTACHMENT_NM"));
				at.setParentBoardNo(rset.getInt("BOARD_NO"));
				
				fList.add(at);
			}
			
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return fList;
	}
}
