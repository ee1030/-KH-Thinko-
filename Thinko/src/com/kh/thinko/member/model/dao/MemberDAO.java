package com.kh.thinko.member.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.thinko.member.model.vo.PageInfo;
import com.kh.thinko.reply.model.vo.Reply;
import com.kh.thinko.board.model.vo.Board;
import com.kh.thinko.member.model.vo.PageInfo2;
import com.kh.thinko.board.model.vo.VBoard;
import com.kh.thinko.member.model.vo.Member;
import static com.kh.thinko.common.JDBCTemplate.*;

/**
 * @author jeonga
 *
 */
public class MemberDAO {
	
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rset = null;
	private Properties prop = null;
	
	public MemberDAO() {
		try {
			String filePath 
				= MemberDAO.class.getResource("/com/kh/thinko/sql/member/member-query.xml").getPath();

			prop = new Properties();
			prop.loadFromXML(new FileInputStream(filePath));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public int joinMember(Connection conn, Member member) throws Exception {
		
		int result = 0;
		
		String query = prop.getProperty("joinMember");
		
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPwd());
			pstmt.setString(3, member.getMemberNm());
			pstmt.setString(4, member.getMemberEmail());
			pstmt.setString(5, member.getMemberPhone());
			pstmt.setString(6, member.getMembershipType());
			pstmt.setString(7, member.getMemberInterest());
			
			result = pstmt.executeUpdate();
			
			
		} finally {
			close(pstmt);
		}
		
		
		return result;
	}

	public int idDupCheck(Connection conn, String inputId) throws Exception{

		int result = 0;
		String query = prop.getProperty("idDupCheck");
		
		try {
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, inputId);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt(1);
			}
			
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return result;
	}

	/** 정보 수정 DAO
	 * @param conn
	 * @param member
	 * @return result
	 */
	public int updateMember(Connection conn, Member member) throws Exception{

		int result = 0;
		String query = prop.getProperty("updateMember");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, member.getMemberEmail());
			pstmt.setString(2, member.getMemberPhone());
			pstmt.setString(3, member.getMemberInterest());
			pstmt.setInt(4, member.getMemberNo());
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	/** 현재 비밀번호 검사 DAO
	 * @param conn
	 * @param loginMember
	 * @return result
	 * @throws Exception
	 */
	public int checkCurrentPwd(Connection conn, Member loginMember) throws Exception{
		int result = 0;
		String query = prop.getProperty("checkCurrentPwd");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, loginMember.getMemberNo());
			pstmt.setString(2, loginMember.getMemberPwd());
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt(1);
			}
			
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return result;
	}

	/** 비밀번호 변경 DAO
	 * @param conn
	 * @param loginMember
	 * @return result
	 * @throws Exception
	 */
	public int updatePwd(Connection conn, Member loginMember) throws Exception{
		int result = 0;
		String query = prop.getProperty("updatePwd");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, loginMember.getMemberPwd());
			pstmt.setInt(2, loginMember.getMemberNo());
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	/** 회원 탈퇴 DAO
	 * @param conn
	 * @param memberNo
	 * @return result
	 * @throws Exception
	 */
	public int updateStatus(Connection conn, int memberNo) throws Exception{
		
		int result = 0;
		String query = prop.getProperty("updateStatus");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	/** 전체 게시글 수 반환 DAO
	 * @param conn
	 * @param memberNo
	 * @return listCount
	 * @throws Exception
	 */
	public int getBoardListCount(Connection conn, int memberNo) throws Exception{
		int listCount = 0;
		
		String query = prop.getProperty("getBoardListCount");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, memberNo);
			
			rset = pstmt.executeQuery();
		
			if(rset.next()) {
				listCount = rset.getInt(1);
			}
			
		} finally {
			close(stmt);
			close(rset);
		}
		
		return listCount;
	}
	
	/** 전체 댓글 수 반환 DAO
	 * @param conn
	 * @param memberNo
	 * @return listCount
	 * @throws Exception
	 */
	public int getReplyListCount(Connection conn, int memberNo) throws Exception{
		int listCount = 0;
		
		String query = prop.getProperty("getReplyListCount");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, memberNo);
			
			rset = pstmt.executeQuery();
		
			if(rset.next()) {
				listCount = rset.getInt(1);
			}
			
		} finally {
			close(stmt);
			close(rset);
		}
		
		return listCount;
	}

	/** 내 게시글 목록 조회 DAO
	 * @param conn
	 * @param pInfo
	 * @param memberNo
	 * @return bList
	 * @throws Exception
	 */
	public List<Board> selectBoardList(Connection conn, PageInfo pInfo, int memberNo) throws Exception{
		List<Board> bList = null;
		String query = prop.getProperty("selectBoardList");
		
		try {
			// SQL 구문 조건절에 대입할 변수 생성
			int startRow = (pInfo.getCurrentPage() - 1) * pInfo.getLimit() + 1 ;
			int endRow = startRow + pInfo.getLimit() - 1;
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			
			bList = new ArrayList<Board>();
			
			while(rset.next()) {
				Board board = new Board();
				
					board.setBoardCreateDate(rset.getTimestamp("BOARD_CREATE_DT"));
					board.setBoardNo(rset.getInt("BOARD_NO"));
					board.setBoardTitle(rset.getString("BOARD_TITLE"));
					board.setBoardTypeName(rset.getString("BOARD_TYPE_NM"));
					board.setReadCount(rset.getInt("READ_COUNT"));
			
				bList.add(board);
			
			}
			
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return bList;
	}

	/** 게시글 삭제 DAO
	 * @param conn
	 * @param boardNo
	 * @return result
	 * @throws Exception
	 */
	public int updateBoardStatus(Connection conn, String boardNo) throws Exception{
		int result = 0;
		String query = prop.getProperty("updateBoardStatus");
		
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(boardNo));
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	/** 내 댓글 목록 조회 DAO
	 * @param conn
	 * @param pInfo
	 * @param memberNo
	 * @return rList
	 * @throws Exception
	 */
	public List<Reply> selectReplyList(Connection conn, PageInfo pInfo, int memberNo) throws Exception{
		List<Reply> rList = null;
		String query = prop.getProperty("selectReplyList");
		
		try {
			int startRow = (pInfo.getCurrentPage() - 1) * pInfo.getLimit() + 1 ;
			int endRow = startRow + pInfo.getLimit() - 1;
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			
			rList = new ArrayList<Reply>();
			
			while(rset.next()) {
				Reply reply = new Reply();
				
					reply.setReplyNo(rset.getInt("REPLY_NO"));
					reply.setReplyContent(rset.getString("REPLY_CONTENT"));
					reply.setReplyCreateDate(rset.getTimestamp("REPLY_CREATE_DT"));
					reply.setParentBoardNo(rset.getInt("PARENT_BOARD_NO"));
					reply.setBoardTitle(rset.getString("BOARD_TITLE"));
					
				rList.add(reply);
			
			}
			
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return rList;
	}

	/** 댓글 삭제 DAO
	 * @param conn
	 * @param replyNo
	 * @return result
	 * @throws Exception
	 */
	public int updateReplyStatus(Connection conn, String replyNo) throws Exception{
		int result = 0;
		String query = prop.getProperty("updateReplyStatus");
		
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(replyNo));
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	/** 좋아요 누른 게시글 수 반환 DAO
	 * @param conn
	 * @param memberNo
	 * @return listCount
	 * @throws Exception
	 */
	public int getFavoriteListCount(Connection conn, int memberNo) throws Exception{
		int listCount = 0;
		
		String query = prop.getProperty("getFavoriteListCount");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, memberNo);
			
			rset = pstmt.executeQuery();
		
			if(rset.next()) {
				listCount = rset.getInt(1);
			}
			
		} finally {
			close(stmt);
			close(rset);
		}
		
		return listCount;
	}

	/** 좋아요 누른 글 목록 조회 DAO
	 * @param conn
	 * @param pInfo
	 * @param memberNo
	 * @return bList
	 * @throws Exception
	 */
	public List<VBoard> selectFavoriteList(Connection conn, PageInfo2 pInfo, int memberNo) throws Exception{
		List<VBoard> bList = null;
		String query = prop.getProperty("selectFavoriteList");
		
		try {
			// SQL 구문 조건절에 대입할 변수 생성
			int startRow = (pInfo.getCurrentPage() - 1) * pInfo.getLimit() + 1 ;
			int endRow = startRow + pInfo.getLimit() - 1;
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			
			bList = new ArrayList<VBoard>();
			
			while(rset.next()) {
				VBoard board = new VBoard();
				
				board.setBoardNo(rset.getInt("BOARD_NO"));
				board.setBoardTypeName(rset.getString("BOARD_TYPE_NM"));
				board.setBoardTitle(rset.getString("BOARD_TITLE"));
				board.setReplyCount(rset.getInt("REPLY_COUNT"));
				board.setReadCount(rset.getInt("READ_COUNT"));
				board.setLikeCount(rset.getInt("LIKE_COUNT"));
				board.setBoardCreateDate(rset.getTimestamp("BOARD_CREATE_DT"));
			
				bList.add(board);
			
			}
			
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return bList;
	}

	/** 좋아요 취소 DAO
	 * @param conn
	 * @param id
	 * @return
	 */
	public int updateLikeFl(Connection conn, int memberNo, String id) throws Exception{
		int result = 0;
		String query = prop.getProperty("updateLikeFl");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			pstmt.setInt(2, Integer.parseInt(id));
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}

}
