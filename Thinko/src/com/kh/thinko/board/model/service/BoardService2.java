package com.kh.thinko.board.model.service;

import static com.kh.thinko.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import com.kh.thinko.board.model.dao.BoardDAO2;
import com.kh.thinko.board.model.vo.Attachment;
import com.kh.thinko.board.model.vo.Board;
import com.kh.thinko.board.model.vo.PageInfo;
import com.kh.thinko.board.model.vo.VBoard;

public class BoardService2 {
	
	private BoardDAO2 dao = new BoardDAO2();


	public PageInfo getPageInfo(String cp, int type) throws Exception{

		Connection conn = getConnection();
		
		int currentPage = cp == null ? 1 : Integer.parseInt(cp);
		
		  int listCount = dao.getListCount(conn, type);
		  
		  close(conn);
		
		  return new PageInfo(currentPage, listCount);
	}


	/** 게시글 목록 조회
	 * @param pInfo
	 * @param type
	 * @return bList
	 * @throws Exception
	 */
	public List<VBoard> selectBoardList(PageInfo pInfo, int type) throws Exception {
		Connection conn = getConnection();
		
		List<VBoard> bList = dao.selectBoardList(conn, pInfo, type);
		
		close(conn);
		
		return bList;
		
		
	}


	/** 인기글 게시판 목록 조회 Service
	 * @return pList
	 * @throws Exception
	 */
	public List<VBoard> selectPopularPosts() throws Exception {
		Connection conn = getConnection();
		List<VBoard> pList = dao.selectPopularPosts(conn);
		conn.close();
		return pList;
	}


	public List<Attachment> selectThumbnailList(PageInfo pInfo) throws Exception {

		Connection conn = getConnection();
		
		List<Attachment> fList = dao.selectThumbnailList(conn, pInfo);
		
		close(conn);
		
		return fList;
		
	
	}


	
	
	
	

}
