package com.kh.thinko.reply.model.dao;

import static com.kh.thinko.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.thinko.board.model.dao.BoardDAO2;
import com.kh.thinko.reply.model.vo.Reply;
import com.kh.thinko.reply.model.vo.VReply;

public class ReplyDAO {

	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rset = null;

	private Properties prop;

	public ReplyDAO() {
		String fileName = BoardDAO2.class.getResource("/com/kh/thinko/sql/reply/reply-query.xml").getPath();
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream(fileName));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 작성자 mang 댓글 목록 조회 DAO
	 * 
	 * @param conn
	 * @param parentBoardNo
	 * @return rList
	 * @throws Exception
	 */
	public List<VReply> selectList(Connection conn, int parentBoardNo) throws Exception {

		List<VReply> rList = null;

		String query = prop.getProperty("selectList");

		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, parentBoardNo);

			rset = pstmt.executeQuery();

			rList = new ArrayList<VReply>();

			while (rset.next()) {
				VReply reply = new VReply();

				reply.setReplyNo(rset.getInt("REPLY_NO"));
				reply.setReplyContent(rset.getString("REPLY_CONTENT"));
				reply.setReplyCreateDate(rset.getTimestamp("REPLY_CREATE_DT"));
				reply.setMemberId(rset.getString("MEMBER_ID"));
				reply.setMemberNo(rset.getInt("MEMBER_NO"));
				
				rList.add(reply);
			}

		} finally {
			close(rset);
			close(pstmt);

		}

		return rList;
	}

	/**
	 * 작성자 mang 댓글 삽입 DAO
	 * 
	 * @param conn
	 * @param reply
	 * @return result
	 * @throws Exception
	 */
	public int insertReply(Connection conn, Reply reply) throws Exception {

		int result = 0;

		String query = prop.getProperty("insertReply");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, reply.getReplyContent());
			pstmt.setString(2, reply.getMemberId());
			pstmt.setInt(3, reply.getParentBoardNo());

			result = pstmt.executeUpdate();

		} finally {
			close(pstmt);
		}

		return result;
	}

	/**
	 * 작성자 mang 댓글 수정 DAO
	 * 
	 * @param conn
	 * @param reply
	 * @return result
	 * @throws Exception
	 */
	public int updateReply(Connection conn, Reply reply) throws Exception {

		int result = 0;
		String query = prop.getProperty("updateReply");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, reply.getReplyContent());
			pstmt.setInt(2, reply.getReplyNo());

			result = pstmt.executeUpdate();

		} finally {
			close(pstmt);
		}

		return result;
	}

	/**
	 * 작성자 mang 댓글 삭제 DAO
	 * 
	 * @param conn
	 * @param replyNo
	 * @return result
	 * @throws Exception
	 */
	public int updateReplyStatus(Connection conn, int replyNo) throws Exception {

		int result = 0;

		String query = prop.getProperty("updateReplyStatus");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, replyNo);

			result = pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}

		return result;
	}

	/** 작성자 김만희
	 * 댓글 신고 DAO
	 * @param conn
	 * @param replyNo
	 * @param memberId
	 * @return result 
	 * @throws Exception
	 */
	public int reportReply(Connection conn, int replyNo, String memberId) throws Exception{
		
		int result = 0;
		
		String query = prop.getProperty("reportReply");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, replyNo);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}

		return result;
	}

	
	/** 작성자 mang
	 * 중복 신고 검사 DAO
	 * @param conn
	 * @param replyNo
	 * @param memberNo
	 * @return 
	 * @throws Exception
	 */
	public int findReport(Connection conn, int replyNo, int memberNo)  throws Exception{
		
		int findResult = 0;
		
		String query = prop.getProperty("findReport");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			pstmt.setInt(2, replyNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				findResult = rset.getInt(1);
			}
			
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return findResult;
	}
	
	
	/** 작성자 mang 
	 *  댓글 신고 DAO
	 * @param conn
	 * @param replyNo
	 * @param memberNo
	 * @param reportCategoryNo
	 * @return result
	 * @throws Exception
	 */
	public int reportReply(Connection conn, int replyNo, int memberNo, int reportCategoryNo) throws Exception{
		
		int result = 0;
		
		String query = prop.getProperty("reportReply");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, reportCategoryNo);
			pstmt.setInt(2, memberNo);
			pstmt.setInt(3, replyNo);
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	/** 익명 게시판을 위한 type 확인
	 * @param conn
	 * @param parentBoardNo
	 * @return result
	 * @throws Exception
	 */
	public int findType(Connection conn, int parentBoardNo) throws Exception{
		
		int type = 0;
		
		String query = prop.getProperty("findType");
		
		System.out.println(query);
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, parentBoardNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				type = rset.getInt(1);
			}
			
		}finally {
			close(rset);
			close(pstmt);
		}
		
		
		return type;
	}

}
