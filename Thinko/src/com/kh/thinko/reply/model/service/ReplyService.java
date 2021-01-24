package com.kh.thinko.reply.model.service;

import static com.kh.thinko.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import com.kh.thinko.reply.model.dao.ReplyDAO;
import com.kh.thinko.reply.model.vo.Reply;
import com.kh.thinko.reply.model.vo.VReply;

public class ReplyService {

	private ReplyDAO dao = new ReplyDAO();
	
	/** 작성자 mang
	 * 댓글 목록 조회 Service
	 * @param parentBoardNo
	 * @return rList
	 * @throws Exception
	 */
	public List<VReply> selectList(int parentBoardNo) throws Exception{
		
		Connection conn = getConnection();
		
		List<VReply> rList = dao.selectList(conn, parentBoardNo);
		
		int type = dao.findType(conn,parentBoardNo);
		
		if(type == 10) {
			for(VReply vReply : rList) {
				vReply.setMemberId("익명");
			}
		}
		
		close(conn);
		
		return rList;
	}


	/** 작성자 mang
	 * 댓글 삽입 Service
	 * @param reply
	 * @return result
	 * @throws Exception
	 */
	public int insertReply(Reply reply) throws Exception{
		
		Connection conn = getConnection();
		String replyContent = reply.getReplyContent();
		
		// 크로스 사이트 스크립팅 방지 처리
		replyContent = replaceParameter(replyContent);
		
		// 개행 문자 변환처리
		// ajax 통신 시 textarea의 개행문자가 \n 하나만 넘어옴 . 
		// \n -> <br>
		replyContent = replyContent.replace("\n", "<br>");
		
		// 변경된 replyContent를 다시 reply에 세팅
		reply.setReplyContent(replyContent);
		
		int result = dao.insertReply(conn, reply);
		
		if(result > 0)	commit(conn);
		else			rollback(conn);
		
		close(conn);
		
		return result;
	}


	/** 작성자 mang
	 * 댓글 수정 Service
	 * @param reply
	 * @return result
	 * @throws Exception
	 */
	public int updateReply(Reply reply) throws Exception{
		
		Connection conn = getConnection();
		
		String replyContent = reply.getReplyContent();
		replyContent = replaceParameter(replyContent);
		replyContent = replyContent.replace("\n", "<br>");
		reply.setReplyContent(replyContent);
		
		int result = dao.updateReply(conn, reply);
		
		if(result > 0)	commit(conn);
		else			rollback(conn);
		
		close(conn);
		
		return result;
	}


	/** 작성자 mang
	 * 댓글 삭제 Service
	 * @param replyNo
	 * @return result
	 * @throws Exception
	 */
	public int updateReplyStatus(int replyNo) throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.updateReplyStatus(conn, replyNo);
		
		if(result > 0)	commit(conn);
		else			rollback(conn);
		
		close(conn);
		
		
		return result;
	}

	
	/** 작성자 mang
	 * 크로스 사이트 스크립트 방지 메소드
	 * @param param
	 * @return result
	 */
	private String replaceParameter(String param) {
		String result = param;
		if(param != null) {
			result = result.replaceAll("&", "&amp;");
			result = result.replaceAll("<", "&lt;");
			result = result.replaceAll(">", "&gt;");
			result = result.replaceAll("\"", "&quot;");
		}
		
		return result;
	}

	
	
	
	/** 작성자 mang
	 * @param replyNo
	 * @param memberNo
	 * @return findResult
	 * @throws Exception
	 */
	public int findReport(int replyNo, int memberNo) throws Exception{
		
		Connection conn = getConnection();
		
		int findResult = dao.findReport(conn, replyNo, memberNo);
		
		close(conn);
		
		
		return findResult;
	}

	
	
	
	/**	작성자 mang
	 * 댓글 신고 Service
	 * @param replyNo
	 * @param memberNo
	 * @param reportCategoryNo 
	 * @param boardNo 
	 * @return result
	 * @throws Exception
	 */
	public int reportReply(int replyNo, int memberNo, int reportCategoryNo) throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.reportReply(conn, replyNo, memberNo, reportCategoryNo);
		
		if(result > 0)	commit(conn);
		else			rollback(conn);
		
		close(conn);
		
		return result;
	}


	
	
}
