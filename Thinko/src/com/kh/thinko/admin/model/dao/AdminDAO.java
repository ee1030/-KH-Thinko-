package com.kh.thinko.admin.model.dao;

import static com.kh.thinko.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.thinko.board.model.vo.PageInfo;
import com.kh.thinko.board.model.vo.ReportBoard;
import com.kh.thinko.board.model.vo.VBoard;
import com.kh.thinko.member.model.dao.MemberDAO;
import com.kh.thinko.member.model.vo.Member;
import com.kh.thinko.reply.model.vo.Reply;
import com.kh.thinko.reply.model.vo.VReportReply;

public class AdminDAO {
	
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rset = null;
	private Properties prop = null;
	
	public AdminDAO() {
		try {
			String filePath 
				= MemberDAO.class.getResource("/com/kh/thinko/sql/admin/admin-query.xml").getPath();

			prop = new Properties();
			prop.loadFromXML(new FileInputStream(filePath));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 총 방문자 수 조회 DAO
	 * @param conn
	 * @return result
	 * @throws Exception
	 */
	public int getVisitorCount(Connection conn) throws Exception {
		int result = 0;
		
		String query = prop.getProperty("getVisitorCount");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {
				result = rset.getInt(1);
			}
			
		} finally {
			close(rset);
			close(stmt);
		}
		
		return result;
	}

	/** 총 게시글 수 조회 DAO
	 * @param conn
	 * @return result
	 * @throws Exception
	 */
	public int getBoardCount(Connection conn) throws Exception {
		
		int result = 0;
		
		String query = prop.getProperty("getBoardCount");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {
				result = rset.getInt(1);
			}
			
		} finally {
			close(rset);
			close(stmt);
		}
		
		return result;
	}

	/** 최근 게시글 조회 DAO
	 * @param conn
	 * @return bList
	 * @throws Exception
	 */
	public List<VBoard> selectBoardList(Connection conn) throws Exception {
		List<VBoard> bList = null;
		
		String query = prop.getProperty("selectBoardList");
		
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

	/** 최근 댓글 목록 조회 DAO
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public List<Reply> selectReplyList(Connection conn) throws Exception {
		List<Reply> rList = null;
		
		String query = prop.getProperty("selectReplyList");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			rList = new ArrayList<Reply>();
			
			while(rset.next()) {
				Reply reply = new Reply();
				
				reply.setReplyNo(rset.getInt("REPLY_NO"));
				reply.setReplyContent(rset.getString("REPLY_CONTENT"));
				reply.setMemberId(rset.getString("MEMBER_ID"));
				reply.setReplyCreateDate(rset.getTimestamp("REPLY_CREATE_DT"));
				
				rList.add(reply);
			}
			
		} finally {
			close(rset);
			close(stmt);
		}
		
		return rList;
	}

	/** 최근 신고된 게시글 목록 조회 DAO
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public List<ReportBoard> selectReportBoard(Connection conn) throws Exception {
		List<ReportBoard> rbList = null;
		
		String query = prop.getProperty("selectReportBoard");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			rbList = new ArrayList<ReportBoard>();
			
			while(rset.next()) {
				ReportBoard board = new ReportBoard();
				
				board.setBoardNo(rset.getInt(1));
				board.setBoardTitle(rset.getString(2));
				board.setMemberId(rset.getString(3));
				board.setBoardCreateDate(rset.getTimestamp(4));
				board.setReadCount(rset.getInt(5));
				board.setReportCount(rset.getInt(6));
				
				rbList.add(board);
			}
			
		} finally {
			close(rset);
			close(stmt);
		}
		
		return rbList;
	}

	/** 최근 신고된 댓글 목록 조회 DAO
	 * @param conn
	 * @return rrList
	 * @throws Exception
	 */
	public List<VReportReply> selectReportReply(Connection conn) throws Exception {
		List<VReportReply> rrList = null;
		
		String query = prop.getProperty("selectReportReply");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			rrList = new ArrayList<VReportReply>();
			
			while(rset.next()) {
				VReportReply reply = new VReportReply();
				
				reply.setReplyNo(rset.getInt(1));
				reply.setReplyContent(rset.getString(2));
				reply.setReplyCreateDate(rset.getTimestamp(3));
				reply.setMemberId(rset.getString(5));
				reply.setParentBoardNo(rset.getInt(6));
				reply.setReportCount(rset.getInt(7));
				
				rrList.add(reply);
			}
			
		} finally {
			close(rset);
			close(stmt);
		}
		
		return rrList;
	}
	
	/** 회원 관리 페이지 페이징 처리를 위한 값 계산 DAO
	 * @param conn
	 * @return listCount
	 * @throws Exception
	 */
	public int getMemberListCount(Connection conn) throws Exception {
		int listCount = 0;
		
		String query = prop.getProperty("getMemberListCount");
		
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

	/** 회원 목록 조회 DAO
	 * @param conn
	 * @return mList
	 * @throws Exception
	 */
	public List<Member> selectMemberList(Connection conn, PageInfo pInfo) throws Exception {
		List<Member> mList = null;
		
		String query = prop.getProperty("selectMemberList");
		
		try {
			int startRow = (pInfo.getCurrentPage() - 1) * pInfo.getLimit() + 1;
			int endRow = startRow + pInfo.getLimit() - 1;
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);

			rset = pstmt.executeQuery();
			
			mList = new ArrayList<Member>();
			
			while(rset.next()) {
				Member member = new Member();
				
				member.setMemberNo(rset.getInt("MEMBER_NO"));
				member.setMemberId(rset.getString("MEMBER_ID"));
				member.setMemberNm(rset.getString("MEMBER_NM"));
				member.setMemberEmail(rset.getString("MEMBER_EMAIL"));
				member.setMemberPhone(rset.getString("MEMBER_PHONE"));
				member.setMemberEnrollDate(rset.getDate("MEMBER_ENROLL_DATE"));
				member.setMemberStatus(rset.getString("MEMBER_STATUS"));
				member.setMemberGrade(rset.getString("MEMBER_GRADE"));
				
				mList.add(member);
			}
					
		} finally {
			close(rset);
			close(stmt);
		}
		
		return mList;
	}

	/** 회원 상태 N으로 업데이트 DAO
	 * @param conn
	 * @param memberNo
	 * @return result
	 * @throws Exception
	 */
	public int updateMemberStatusN(Connection conn, int memberNo) throws Exception {
		int result = 0;
		
		String query = prop.getProperty("updateMemberStatusN");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	/** 회원 상태 Y로 업데이트 DAO
	 * @param conn
	 * @param memberNo
	 * @return result
	 * @throws Exception
	 */
	public int updateMemberStatusY(Connection conn, int memberNo) throws Exception {
		int result = 0;
		
		String query = prop.getProperty("updateMemberStatusY");
		
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
	 * @return listCount
	 * @throws Exception
	 */
	public int getListCount(Connection conn) throws Exception {
		int listCount = 0;
		
		String query = prop.getProperty("getListCount");
		
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
	
	/** 전체 게시글 목록 조회 DAO
	 * @param conn
	 * @param pInfo
	 * @return bList
	 * @throws Exception
	 */
	public List<VBoard> selectBoardList(Connection conn, PageInfo pInfo) throws Exception {
		
		List<VBoard> bList = null;
		
		String query = prop.getProperty("selectAllBoardList");
		
		try {
			// SQL 구문 조건절에 대입할 변수 생성
			int startRow = (pInfo.getCurrentPage() - 1) * pInfo.getLimit() + 1;
			int endRow = startRow + pInfo.getLimit() - 1;
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			bList = new ArrayList<VBoard>();
			
			while(rset.next()) {
				VBoard board = new VBoard();
				board.setBoardNo(rset.getInt("BOARD_NO"));
				board.setBoardTitle(rset.getString("BOARD_TITLE"));
				board.setMemberId(rset.getString("MEMBER_ID"));
				board.setBoardTypeName(rset.getString("BOARD_TYPE_NM"));
				board.setBoardTypeNo(rset.getInt("BOARD_TYPE_NO"));
				board.setBoardStatus(rset.getString("BOARD_STATUS"));
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

	/** 전체 댓글 목록 조회 DAO
	 * @param conn
	 * @param pInfo
	 * @return
	 * @throws Exception
	 */
	public List<Reply> selectReplyList(Connection conn, PageInfo pInfo) throws Exception {
		List<Reply> rList = null;
		
		String query = prop.getProperty("selectAllReplyList");
		
		try {
			// SQL 구문 조건절에 대입할 변수 생성
			int startRow = (pInfo.getCurrentPage() - 1) * pInfo.getLimit() + 1;
			int endRow = startRow + pInfo.getLimit() - 1;
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			rList = new ArrayList<Reply>();
			
			while(rset.next()) {
				Reply reply = new Reply();
				reply.setReplyNo(rset.getInt("REPLY_NO"));
				reply.setParentBoardNo(rset.getInt("PARENT_BOARD_NO"));
				reply.setReplyContent(rset.getString("REPLY_CONTENT"));
				reply.setMemberId(rset.getString("MEMBER_ID"));
				reply.setReplyCreateDate(rset.getTimestamp("REPLY_CREATE_DT"));
				reply.setReplyStatus(rset.getString("REPLY_STATUS"));

				rList.add(reply);
			}
			
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return rList;
	}

	/** 전체 댓글 수 반환 DAO
	 * @param conn
	 * @return listCount
	 * @throws Exception
	 */
	public int getReplyListCount(Connection conn) throws Exception {
		int listCount = 0;
		
		String query = prop.getProperty("getReplyListCount");
		
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

	/** 모든 신고된 게시글 조회 DAO
	 * @param conn
	 * @return rbList
	 * @throws Exception
	 */
	public List<ReportBoard> selectReportedBoardList(Connection conn, PageInfo pInfo) throws Exception {
		List<ReportBoard> rbList = null;
		
		String query = prop.getProperty("selectAllReportBoard");
		
		try {
			int startRow = (pInfo.getCurrentPage() - 1) * pInfo.getLimit() + 1;
			int endRow = startRow + pInfo.getLimit() - 1;
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			rbList = new ArrayList<ReportBoard>();
			
			while(rset.next()) {
				ReportBoard board = new ReportBoard();
				
				board.setBoardNo(rset.getInt("BOARD_NO"));
				board.setBoardTitle(rset.getString("BOARD_TITLE"));
				board.setMemberId(rset.getString("MEMBER_ID"));
				board.setBoardStatus(rset.getString("BOARD_STATUS"));
				board.setReadCount(rset.getInt("READ_COUNT"));
				board.setBoardCreateDate(rset.getTimestamp("BOARD_CREATE_DT"));
				board.setReportCount(rset.getInt("REPORT_COUNT"));
				board.setBoardTypeName(rset.getString("BOARD_TYPE_NM"));
				
				rbList.add(board);
			}
			
		} finally {
			close(rset);
			close(stmt);
		}
		
		return rbList;
	}

	/** 모든 신고된 게시글 페이징을 위한 값 계산 DAO
	 * @param conn
	 * @return listCount
	 * @throws Exception
	 */
	public int getReportBoardPageInfo(Connection conn) throws Exception {
		int listCount = 0;
		
		String query = prop.getProperty("getReportBoardListCount");
		
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

	/** 모든 신고된 댓글 페이징을 위한 값 계산 DAO
	 * @param conn
	 * @return listCount
	 * @throws Exception
	 */
	public int getReportReplyPageInfo(Connection conn) throws Exception {
		int listCount = 0;
		
		String query = prop.getProperty("getReportReplyListCount");
		
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

	/** 모든 신고된 댓글 조회 DAO
	 * @param conn
	 * @param pInfo
	 * @return rrList
	 * @throws Exception
	 */
	public List<VReportReply> selectReportedReplyList(Connection conn, PageInfo pInfo) throws Exception {
		List<VReportReply> rrList = null;
		
		String query = prop.getProperty("selectReportedReplyList");
		
		try {
			int startRow = (pInfo.getCurrentPage() - 1) * pInfo.getLimit() + 1;
			int endRow = startRow + pInfo.getLimit() - 1;
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			rrList = new ArrayList<VReportReply>();
			
			while(rset.next()) {
				VReportReply reply = new VReportReply();
				reply.setReplyNo(rset.getInt("REPLY_NO"));
				reply.setParentBoardNo(rset.getInt("PARENT_BOARD_NO"));
				reply.setReplyContent(rset.getString("REPLY_CONTENT"));
				reply.setMemberId(rset.getString("MEMBER_ID"));
				reply.setReplyStatus(rset.getString("REPLY_STATUS"));
				reply.setReplyCreateDate(rset.getTimestamp("REPLY_CREATE_DT"));
				reply.setReportCount(rset.getInt("REPORT_COUNT"));
				
				rrList.add(reply);
			}
			
		} finally {
			close(rset);
			close(stmt);
		}
		
		return rrList;
	}

	/** DB에 있는 파일 이름 가져오기 DAO
	 * @param conn
	 * @return dbFileList
	 * @throws Exception
	 */
	public List<String> getDbFileName(Connection conn) throws Exception {
		
		List<String> dbFileList = null; 
		
		String query = prop.getProperty("getDbFileName");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			dbFileList = new ArrayList<String>();
			
			while(rset.next()) {
				String fileName = rset.getString(1);
				
				dbFileList.add(fileName);
			}
			
		} finally {
			close(rset);
			close(stmt);
		}
		
		return dbFileList;
	}

	/** 공지사항 파일 목록 조회 DAO
	 * @param conn
	 * @return noticeFileList
	 * @throws Exception
	 */
	public List<String> getNoticeFileList(Connection conn) throws Exception {
		List<String> noticeFileList = null;
		
		String query = prop.getProperty("getNoticeFileList");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			noticeFileList = new ArrayList<String>();
			
			while(rset.next()) {
				String fileName = rset.getString(1);
				
				noticeFileList.add(fileName);
			}
			
		} finally {
			close(rset);
			close(stmt);
		}
		
		return noticeFileList;
	}


}
