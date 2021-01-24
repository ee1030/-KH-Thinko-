package com.kh.thinko.admin.model.dao;

import static com.kh.thinko.common.JDBCTemplate.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.kh.thinko.board.model.vo.PageInfo;
import com.kh.thinko.board.model.vo.PageInfo2;
import com.kh.thinko.board.model.vo.ReportBoard;
import com.kh.thinko.board.model.vo.VBoard;
import com.kh.thinko.member.model.vo.Member;
import com.kh.thinko.reply.model.vo.Reply;
import com.kh.thinko.reply.model.vo.VReportReply;

public class AdminSearchDAO {

	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rset = null;

	/** 전체 게시글 검색 페이지 정보 생성 DAO
	 * @param conn
	 * @param condition
	 * @return listCount
	 * @throws Exception
	 */
	public int getListCount(Connection conn, String condition) throws Exception {

		int listCount = 0;

		String query = "SELECT COUNT(*) FROM V_BOARD WHERE" + condition;

		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);

			if (rset.next()) {
				listCount = rset.getInt(1);
			}

		} finally {
			close(rset);
			close(stmt);

		}

		return listCount;
	}

	/** 전체 게시글 검색 목록 조회 DAO
	 * @param conn
	 * @param pInfo
	 * @param condition
	 * @param map
	 * @return bList
	 * @throws Exception
	 */
	public List<VBoard> searchBoardList(Connection conn, PageInfo pInfo, String condition, Map<String, Object> map)
			throws Exception {

		List<VBoard> bList = null; // 최종 조회 결과를 담을 변수

		String query = "SELECT * FROM" + "    (SELECT ROWNUM RNUM , V.*" + "    FROM"
				+ "        (SELECT * FROM V_BOARD " + "        WHERE " + condition
				+ "         ORDER BY BOARD_NO DESC) V )" + "WHERE RNUM BETWEEN ? AND ?";

		try {
			// SQL 구문에 사용될 위치 홀더값 생성
			int startRow = (pInfo.getCurrentPage() - 1) * pInfo.getLimit() + 1;
			int endRow = startRow + pInfo.getLimit() - 1;

			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);

			rset = pstmt.executeQuery();

			bList = new ArrayList<VBoard>();

			while (rset.next()) {
				VBoard vBoard = new VBoard();
				
				vBoard.setBoardNo(rset.getInt("BOARD_NO"));
				vBoard.setBoardTitle(rset.getString("BOARD_TITLE"));
				vBoard.setMemberId(rset.getString("MEMBER_ID"));
				vBoard.setBoardTypeName(rset.getString("BOARD_TYPE_NM"));
				vBoard.setBoardStatus(rset.getString("BOARD_STATUS"));
				vBoard.setReplyCount(rset.getInt("REPLY_COUNT"));
				vBoard.setLikeCount(rset.getInt("LIKE_COUNT"));
				vBoard.setReadCount(rset.getInt("READ_COUNT"));
				vBoard.setBoardCreateDate(rset.getTimestamp("BOARD_CREATE_DT"));

				bList.add(vBoard);
			}

		} finally {
			close(rset);
			close(pstmt);
		}

		return bList;
	}

	/** 전체 댓글 검색 페이지 정보 조회 DAO
	 * @param conn
	 * @param condition
	 * @return listCount
	 * @throws Exception
	 */
	public int getReplyListCount(Connection conn, String condition) throws Exception {
		int listCount = 0;

		String query = "SELECT COUNT(*) FROM V_REPLY WHERE" + condition;

		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);

			if (rset.next()) {
				listCount = rset.getInt(1);
			}

		} finally {
			close(rset);
			close(stmt);

		}

		return listCount;
	}

	/** 전체 댓글 검색 목록 조회 DAO
	 * @param conn
	 * @param pInfo
	 * @param condition
	 * @param map
	 * @return rList
	 * @throws Exception
	 */
	public List<Reply> searchReplyList(Connection conn, PageInfo pInfo, String condition, Map<String, Object> map) throws Exception {
		List<Reply> rList = null; // 최종 조회 결과를 담을 변수

		String query = "SELECT * FROM" + "    (SELECT ROWNUM RNUM , V.*" + "    FROM"
				+ "        (SELECT * FROM V_REPLY " + "        WHERE " + condition
				+ "         ORDER BY REPLY_NO DESC) V )" + "WHERE RNUM BETWEEN ? AND ?";

		try {
			// SQL 구문에 사용될 위치 홀더값 생성
			int startRow = (pInfo.getCurrentPage() - 1) * pInfo.getLimit() + 1;
			int endRow = startRow + pInfo.getLimit() - 1;

			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);

			rset = pstmt.executeQuery();

			rList = new ArrayList<Reply>();

			while (rset.next()) {
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

	/** 신고 게시글 목록 페이지 정보 조회 DAO
	 * @param conn
	 * @param condition
	 * @return listCount
	 * @throws Exception
	 */
	public int getReportBoardListCount(Connection conn, String condition) throws Exception {
		int listCount = 0;

		String query = "SELECT COUNT(*) FROM V_R_BOARD WHERE REPORT_COUNT > 0 AND" + condition;

		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);

			if (rset.next()) {
				listCount = rset.getInt(1);
			}

		} finally {
			close(rset);
			close(stmt);

		}

		return listCount;
	}

	/** 신고 게시글 검색 목록 조회 DAO
	 * @param conn
	 * @param pInfo
	 * @param condition
	 * @param map
	 * @return rbList
	 * @throws Exception
	 */
	public List<ReportBoard> searchReportBoardList(Connection conn, PageInfo pInfo, String condition,
			Map<String, Object> map) throws Exception {
		
		List<ReportBoard> rbList = null; // 최종 조회 결과를 담을 변수

		String query = "SELECT * FROM" + "    (SELECT ROWNUM RNUM , V.*" + "    FROM"
				+ "        (SELECT * FROM V_R_BOARD " + "        WHERE REPORT_COUNT > 0 AND" + condition
				+ "         ORDER BY BOARD_NO DESC) V )" + "WHERE RNUM BETWEEN ? AND ?";

		try {
			// SQL 구문에 사용될 위치 홀더값 생성
			int startRow = (pInfo.getCurrentPage() - 1) * pInfo.getLimit() + 1;
			int endRow = startRow + pInfo.getLimit() - 1;

			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);

			rset = pstmt.executeQuery();

			rbList = new ArrayList<ReportBoard>();

			while (rset.next()) {
				ReportBoard board = new ReportBoard();
				
				board.setBoardNo(rset.getInt("BOARD_NO"));
				board.setBoardTitle(rset.getString("BOARD_TITLE"));
				board.setMemberId(rset.getString("MEMBER_ID"));
				board.setBoardStatus(rset.getString("BOARD_STATUS"));
				board.setReadCount(rset.getInt("READ_COUNT"));
				board.setBoardCreateDate(rset.getTimestamp("BOARD_CREATE_DT"));
				board.setReportCount(rset.getInt("REPORT_COUNT"));
				
				rbList.add(board);
			}

		} finally {
			close(rset);
			close(pstmt);
		}

		return rbList;
	}

	/** 신고 댓글 검색 목록 페이지 정보 조회 DAO
	 * @param conn
	 * @param condition
	 * @return listCount
	 * @throws Exception
	 */
	public int getReportReplyListCount(Connection conn, String condition) throws Exception {
		int listCount = 0;

		String query = "SELECT COUNT(*) FROM V_R_REPLY WHERE REPORT_COUNT > 0 AND" + condition;

		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);

			if (rset.next()) {
				listCount = rset.getInt(1);
			}

		} finally {
			close(rset);
			close(stmt);

		}

		return listCount;
	}

	/** 신고 댓글 검색 목록 조회 DAO
	 * @param conn
	 * @param pInfo
	 * @param condition
	 * @param map
	 * @return rrList
	 * @throws Exception
	 */
	public List<VReportReply> searchReportReplyList(Connection conn, PageInfo pInfo, String condition,
			Map<String, Object> map) throws Exception {
		List<VReportReply> rrList = null; // 최종 조회 결과를 담을 변수

		String query = "SELECT * FROM" + "    (SELECT ROWNUM RNUM , V.*" + "    FROM"
				+ "        (SELECT * FROM V_R_REPLY " + "        WHERE REPORT_COUNT > 0 AND" + condition
				+ "         ORDER BY REPLY_NO DESC) V )" + "WHERE RNUM BETWEEN ? AND ?";

		try {
			// SQL 구문에 사용될 위치 홀더값 생성
			int startRow = (pInfo.getCurrentPage() - 1) * pInfo.getLimit() + 1;
			int endRow = startRow + pInfo.getLimit() - 1;

			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);

			rset = pstmt.executeQuery();

			rrList = new ArrayList<VReportReply>();

			while (rset.next()) {
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
			close(pstmt);
		}

		return rrList;
	}

	/** 회원관리 검색 페이징 조회 DAO
	 * @param conn
	 * @param condition
	 * @return listCount
	 * @throws Exception
	 */
	public int getMemberListCount(Connection conn, String condition) throws Exception {
		int listCount = 0;

		String query = "SELECT COUNT(*) FROM TK_MEMBER WHERE" + condition;

		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);

			if (rset.next()) {
				listCount = rset.getInt(1);
			}

		} finally {
			close(rset);
			close(stmt);

		}

		return listCount;
	}

	/** 회원관리 검색 목록 조회 DAO
	 * @param conn
	 * @param pInfo
	 * @param condition
	 * @param map
	 * @return mList
	 * @throws Exception
	 */
	public List<Member> searchMemberList(Connection conn, PageInfo pInfo, String condition, Map<String, Object> map) throws Exception {
		
		List<Member> mList = null; // 최종 조회 결과를 담을 변수

		String query = "SELECT * FROM" + "    (SELECT ROWNUM RNUM , V.*" + "    FROM"
				+ "        (SELECT * FROM TK_MEMBER " + "        WHERE" + condition
				+ "         ORDER BY MEMBER_NO DESC) V )" + "WHERE RNUM BETWEEN ? AND ?";

		try {
			// SQL 구문에 사용될 위치 홀더값 생성
			int startRow = (pInfo.getCurrentPage() - 1) * pInfo.getLimit() + 1;
			int endRow = startRow + pInfo.getLimit() - 1;

			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);

			rset = pstmt.executeQuery();

			mList = new ArrayList<Member>();

			while (rset.next()) {
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
			close(pstmt);
		}

		return mList;
	}

}
