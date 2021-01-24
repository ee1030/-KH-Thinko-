package com.kh.thinko.admin.model.service;

import java.sql.Connection;
import java.util.List;
import java.util.Map;
import static com.kh.thinko.common.JDBCTemplate.*;

import com.kh.thinko.admin.model.dao.AdminSearchDAO;
import com.kh.thinko.board.model.vo.PageInfo;
import com.kh.thinko.board.model.vo.ReportBoard;
import com.kh.thinko.board.model.vo.VBoard;
import com.kh.thinko.member.model.vo.Member;
import com.kh.thinko.reply.model.vo.Reply;
import com.kh.thinko.reply.model.vo.VReportReply;

public class AdminSearchService {
	private AdminSearchDAO dao = new AdminSearchDAO();

	/** 전체 게시글 검색 페이지 정보 반환 Service
	 * @param map
	 * @return pInfo
	 * @throws Exception
	 */
	public PageInfo getPageInfo(Map<String, Object> map) throws Exception {
		Connection conn = getConnection();

		map.put("currentPage",
				(map.get("currentPage") == null) ? 1 : Integer.parseInt((String) map.get("currentPage")));

		String condition = createCondition(map);

		int listCount = 0;

		listCount = dao.getListCount(conn, condition);

		close(conn);

		return new PageInfo((int) map.get("currentPage"), listCount);
	}
	
	/** 전체 댓글 검색 페이지 정보 반환 Service
	 * @param map
	 * @return pInfo
	 * @throws Exception
	 */
	public PageInfo getReplyPageInfo(Map<String, Object> map) throws Exception {
		Connection conn = getConnection();
		
		map.put("currentPage",
				(map.get("currentPage") == null) ? 1 : Integer.parseInt((String) map.get("currentPage")));
		
		String condition = createReplyCondition(map);
		
		int listCount = 0;
		
		listCount = dao.getReplyListCount(conn, condition);
		
		close(conn);
		
		return new PageInfo((int) map.get("currentPage"), listCount);
	}

	/** 전체 게시글 검색 조건문 생성 Service
	 * @param map
	 * @return condition
	 */
	private String createCondition(Map<String, Object> map) {

		String condition = null;

		String searchKey = (String) map.get("searchKey");
		String searchValue = (String) map.get("searchValue");

		switch (searchKey) {

		case "title":
			condition = " BOARD_TITLE LIKE '%' || '" + searchValue + "' || '%' ";
			break; // "BOARD_TITLE LIKE '%' || '49' || '%' "

		case "content":
			condition = " BOARD_CONTENT LIKE '%' || '" + searchValue + "' || '%' ";
			break;

		case "titcont":
			condition = " (BOARD_TITLE LIKE '%' || '" + searchValue + "' || '%' " + "OR BOARD_CONTENT LIKE '%' || '"
					+ searchValue + "' || '%') ";
			break;

		case "writer":
			condition = " MEMBER_ID LIKE '%' || '" + searchValue + "' || '%' ";
			break;
		}
		return condition;
	}
	
	/** 전체 댓글 검색 조건문 생성 Service
	 * @param map
	 * @return condition
	 */
	private String createReplyCondition(Map<String, Object> map) {
		
		String condition = null;
		
		String searchKey = (String) map.get("searchKey");
		String searchValue = (String) map.get("searchValue");
		
		switch (searchKey) {
		
		case "content":
			condition = " REPLY_CONTENT LIKE '%' || '" + searchValue + "' || '%' ";
			break;

		case "writer":
			condition = " MEMBER_ID LIKE '%' || '" + searchValue + "' || '%' ";
			break;		
		}
		return condition;
	}
	
	/** 회원관리 페이지 검색 조건문 생성 Service
	 * @param map
	 * @return condition
	 */
	private String createMemberCondition(Map<String, Object> map) {
		
		String condition = null;
		
		String searchKey = (String) map.get("searchKey");
		String searchValue = (String) map.get("searchValue");
		
		switch (searchKey) {
		
		case "id":
			condition = " MEMBER_ID LIKE '%' || '" + searchValue + "' || '%' ";
			break;
			
		case "name":
			condition = " MEMBER_NM LIKE '%' || '" + searchValue + "' || '%' ";
			break;	
			
		case "email":
			condition = " MEMBER_EMAIL LIKE '%' || '" + searchValue + "' || '%' ";
			break;		
		}
		return condition;
	}

	/** 전체 게시글 검색 목록 조회 Service
	 * @param map
	 * @param pInfo
	 * @return bList
	 * @throws Exception
	 */
	public List<VBoard> searchBoardList(Map<String, Object> map, PageInfo pInfo) throws Exception {
		Connection conn = getConnection();

		String condition = createCondition(map);

		List<VBoard> bList = dao.searchBoardList(conn, pInfo, condition, map);

		close(conn);

		return bList;
	}
	
	/** 전체 댓글 검색 목록 조회 Service
	 * @param map
	 * @param pInfo
	 * @return bList
	 * @throws Exception
	 */
	public List<Reply> searchReplyList(Map<String, Object> map, PageInfo pInfo) throws Exception {
		Connection conn = getConnection();
		
		String condition = createReplyCondition(map);
		
		List<Reply> rList = dao.searchReplyList(conn, pInfo, condition, map);
		
		close(conn);
		
		return rList;
	}

	/** 신고된 게시글 페이지 정보 조회 Service
	 * @param map
	 * @return pInfo
	 * @throws Exception
	 */
	public PageInfo getReportBoardPageInfo(Map<String, Object> map) throws Exception {
		Connection conn = getConnection();

		map.put("currentPage",
				(map.get("currentPage") == null) ? 1 : Integer.parseInt((String) map.get("currentPage")));

		String condition = createCondition(map);

		int listCount = 0;

		listCount = dao.getReportBoardListCount(conn, condition);

		close(conn);

		return new PageInfo((int) map.get("currentPage"), listCount);
	}

	/** 신고된 게시글 검색 목록 조회 Service
	 * @param map
	 * @param pInfo
	 * @return rbList
	 * @throws Exception
	 */
	public List<ReportBoard> searchReportBoardList(Map<String, Object> map, PageInfo pInfo) throws Exception {
		Connection conn = getConnection();

		String condition = createCondition(map);

		List<ReportBoard> rbList = dao.searchReportBoardList(conn, pInfo, condition, map);

		close(conn);

		return rbList;
	}

	/** 신고된 댓글 검색 페이지 정보 조회 Service
	 * @param map
	 * @return pInfo
	 * @throws Exception
	 */
	public PageInfo getReportReplyPageInfo(Map<String, Object> map) throws Exception {
		Connection conn = getConnection();
		
		map.put("currentPage",
				(map.get("currentPage") == null) ? 1 : Integer.parseInt((String) map.get("currentPage")));
		
		String condition = createReplyCondition(map);
		
		int listCount = 0;
		
		listCount = dao.getReportReplyListCount(conn, condition);
		
		close(conn);
		
		return new PageInfo((int) map.get("currentPage"), listCount);
	}

	/** 신고된 댓글 검색 목록 조회 Service
	 * @param map
	 * @param pInfo
	 * @return rrList
	 * @throws Exception
	 */
	public List<VReportReply> searchReportReplyList(Map<String, Object> map, PageInfo pInfo) throws Exception {
		Connection conn = getConnection();
		
		String condition = createReplyCondition(map);
		
		List<VReportReply> rrList = dao.searchReportReplyList(conn, pInfo, condition, map);
		
		close(conn);
		
		return rrList;
	}

	/** 회원관리 검색 목록 페이징 조회 Service
	 * @param map
	 * @return pInfo
	 * @throws Exception
	 */
	public PageInfo getMemberPageInfo(Map<String, Object> map) throws Exception {
		Connection conn = getConnection();
		
		map.put("currentPage",
				(map.get("currentPage") == null) ? 1 : Integer.parseInt((String) map.get("currentPage")));
		
		String condition = createMemberCondition(map);
		
		int listCount = 0;
		
		listCount = dao.getMemberListCount(conn, condition);
		
		close(conn);
		
		return new PageInfo((int) map.get("currentPage"), listCount);
	}

	/** 회원관리 검색 목록 조회 Service
	 * @param map
	 * @param pInfo
	 * @return mList
	 * @throws Exception
	 */
	public List<Member> searchMemberList(Map<String, Object> map, PageInfo pInfo) throws Exception {
		Connection conn = getConnection();
		
		String condition = createMemberCondition(map);
		
		List<Member> mList = dao.searchMemberList(conn, pInfo, condition, map);
		
		close(conn);
		
		return mList;
	}

}
