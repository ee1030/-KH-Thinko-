package com.kh.thinko.board.model.service;

import static com.kh.thinko.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.Map;

import com.kh.thinko.board.model.dao.BoardDAO1;
import com.kh.thinko.board.model.vo.VBoard;

public class BoardService1 {
	
	private BoardDAO1 dao = new BoardDAO1();

	/** 게시글 상세조회 Service
	 * @param boardNo
	 * @return board
	 * @throws Exception
	 */
	public VBoard selectBoard(int boardNo, String memberId) throws Exception {
		Connection conn = getConnection();
		
		VBoard board = dao.selectBoard(conn, boardNo);
		
		if(board != null && !board.getMemberId().equals(memberId)) {
			int result = dao.increaseReadCount(conn, boardNo);
			
			if(result > 0) {
				commit(conn);
				
				board.setReadCount(board.getReadCount()+1);
			} else {
				rollback(conn);
			}
			
		}
		
		close(conn);
		
		return board;
	}

	/** 게시글 신고 기능 Service
	 * @param boardNo
	 * @return result
	 * @throws Exception
	 */
	public int reportBoard(int boardNo, int memberNo, int reportCategoryNo) throws Exception {
		Connection conn = getConnection();
		
		int result = dao.reportBoard(conn, boardNo, memberNo, reportCategoryNo);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		return result;
	}

	/** 중복 신고 검사 Service
	 * @param boardNo
	 * @param memberNo
	 * @return findReport
	 * @throws Exception
	 */
	public int findReport(int boardNo, int memberNo) throws Exception {
		Connection conn = getConnection();
		
		int findReport = dao.findReport(conn, boardNo, memberNo);
		
		close(conn);
		
		return findReport;
	}
	
	/** 좋아요 중복 검사 Service
	 * @param boardNo
	 * @param memberNo
	 * @return checkLike
	 * @throws Exception
	 */
	public int checkLike(int boardNo, int memberNo) throws Exception {
		Connection conn = getConnection();
		
		int checkLike = dao.checkLike(conn, boardNo, memberNo);
		
		close(conn);
		
		return checkLike;
	}

	/** 좋아요 상태 업데이트 Service
	 * @param boardNo
	 * @param memberNo
	 * @return
	 * @throws Exception
	 */
	public String updateLike(int boardNo, int memberNo) throws Exception {
		Connection conn = getConnection();
		
		String fl = dao.getLikeFl(conn, boardNo, memberNo);
		
		if(fl.equals("Y")) {
			int result = dao.updateLikeToN(conn, boardNo, memberNo);
			
			if(result > 0) {
				commit(conn);
				
				fl = "N";
			} else {
				rollback(conn);
			}
		} else {
			int result = dao.updateLikeToY(conn, boardNo, memberNo);
			
			if(result > 0) {
				commit(conn);
				
				fl = "Y";
			} else {
				rollback(conn);
			}
		}
		
		close(conn);
		
		return fl;
	}

	/** 좋아요 삽입 Service
	 * @param boardNo
	 * @param memberNo
	 * @return insertLike
	 * @throws Exception
	 */
	public int insertLike(int boardNo, int memberNo) throws Exception {
		Connection conn = getConnection();
		
		int insertLike = dao.insertLike(conn, boardNo, memberNo);
		
		if(insertLike > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return insertLike;
	}

	/** 위치정보 가져오기 Service
	 * @param boardNo
	 * @return map
	 * @throws Exception
	 */
	public Map<String, Object> selectLocation(int boardNo) throws Exception {
		Connection conn = getConnection();
				
		Map<String, Object> map = dao.selectLocation(conn, boardNo);
		
		close(conn);
				
		return map;
	}


	
	
	

}
