package com.kh.thinko.admin.model.service;

import static com.kh.thinko.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import com.kh.thinko.admin.model.dao.AdminDAO;
import com.kh.thinko.board.model.vo.PageInfo;
import com.kh.thinko.board.model.vo.ReportBoard;
import com.kh.thinko.board.model.vo.VBoard;
import com.kh.thinko.member.model.vo.Member;
import com.kh.thinko.reply.model.vo.Reply;
import com.kh.thinko.reply.model.vo.VReportReply;

/**
 * @author sksgu
 *
 */
public class AdminService {
	
	private AdminDAO dao = new AdminDAO();

	/** 당일 방문자 수 조회 Service
	 * @return result
	 * @throws Exception
	 */
	public int getVisitorCount() throws Exception{
		Connection conn = getConnection();
		
		int result = dao.getVisitorCount(conn);
		
		close(conn);
		
		return result;
	}

	/** 총 게시글 수 조회 Service
	 * @return result
	 * @throws Exception
	 */
	public int getBoardCount() throws Exception {
		Connection conn = getConnection();
		
		int result = dao.getBoardCount(conn);
		
		close(conn);
		
		return result;
	}

	/** 최근 게시글 조회 Service
	 * @return bList
	 * @throws Exception
	 */
	public List<VBoard> selectBoardList() throws Exception {
		Connection conn = getConnection();
		
		List<VBoard> bList = dao.selectBoardList(conn);
		
		close(conn);
		
		return bList;
	}

	
	/** 최근 댓글 목록 조회 Service
	 * @return rList
	 * @throws Exception
	 */
	public List<Reply> selectReplyList() throws Exception {
		Connection conn = getConnection();
		
		List<Reply> rList = dao.selectReplyList(conn);
		
		close(conn);
		
		return rList;
	}

	/** 최근 신고된 게시글 목록 조회 Service
	 * @return rbList
	 * @throws Exception
	 */
	public List<ReportBoard> selectReportBoard() throws Exception {
		Connection conn = getConnection();
		
		List<ReportBoard> rbList = dao.selectReportBoard(conn);
		
		close(conn);
		
		return rbList;
	}

	/** 최근 신고된 댓글 목록 조회 Service
	 * @return rrList
	 * @throws Exception
	 */
	public List<VReportReply> selectReportReply() throws Exception {
		Connection conn = getConnection();
		
		List<VReportReply> rrList = dao.selectReportReply(conn);
		
		close(conn);
		
		return rrList;
	}
	
	/** 회원 관리 페이징 처리를 위한 값 계산 Service
	 * @param cp
	 * @return pInfo
	 * @throws Exception
	 */
	public PageInfo getMemberPageInfo(String cp) throws Exception {
		Connection conn = getConnection();
		
		// cp가 null일 경우
		int currentPage = cp == null ? 1 : Integer.parseInt(cp);
		
		// DB에서 전체 게시글 수를 조회하여 반환 받기
		int listCount = dao.getMemberListCount(conn);
		
		close(conn); 
		
		// 얻어온 현재 페이지와, DB에서 조회한 전체 게시글 수를 이용하여
		// PageInfo 객체를 생성함
		return new PageInfo(currentPage, listCount);
	}

	/** 회원 목록 조회 Service
	 * @return mList
	 * @throws Exception
	 */
	public List<Member> selectMemberList(PageInfo pInfo) throws Exception {
		Connection conn = getConnection();
		
		List<Member> mList = dao.selectMemberList(conn, pInfo);
		
		close(conn);
		
		return mList;
	}

	/** 회원 상태 업데이트 Service
	 * @param memberNo
	 * @return result
	 * @throws Exception
	 */
	public int updateMemberStatus(int memberNo, String memberStatus) throws Exception {
		Connection conn = getConnection();
		int result = 0;
		
		if(memberStatus.equals("활동")) {
			result = dao.updateMemberStatusN(conn, memberNo);
		} else {
			result = dao.updateMemberStatusY(conn, memberNo);
		}
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		return result;
	}

	/** 페이징 처리를 위한 값 계산 Service
	 * @param cp
	 * @return pInfo
	 * @throws Exception
	 */
	public PageInfo getPageInfo(String cp) throws Exception {
		Connection conn = getConnection();
		
		// cp가 null일 경우
		int currentPage = cp == null ? 1 : Integer.parseInt(cp);
		
		// DB에서 전체 게시글 수를 조회하여 반환 받기
		int listCount = dao.getListCount(conn);
		
		close(conn); 
		
		// 얻어온 현재 페이지와, DB에서 조회한 전체 게시글 수를 이용하여
		// PageInfo 객체를 생성함
		return new PageInfo(currentPage, listCount);
	}

	/** 전체 게시글 목록 조회 Service
	 * @param pInfo
	 * @return bList
	 * @throws Exception
	 */
	public List<VBoard> selectBoardList(PageInfo pInfo) throws Exception {
		Connection conn = getConnection();
		
		List<VBoard> bList = dao.selectBoardList(conn, pInfo);
		
		close(conn);
		
		return bList;
	}

	/** 전체 댓글 목록 조회 Service
	 * @param pInfo
	 * @return rList
	 * @throws Exception
	 */
	public List<Reply> selectReplyList(PageInfo pInfo) throws Exception {
		Connection conn = getConnection();
		
		List<Reply> rList = dao.selectReplyList(conn, pInfo);
		
		close(conn);
		
		return rList;
	}
	
	/** 모든 댓글 조회 페이징 처리를 위한 값 계산 Service
	 * @param cp
	 * @return pInfo
	 * @throws Exception
	 */
	public PageInfo getReplyPageInfo(String cp) throws Exception {
		Connection conn = getConnection();
		
		// cp가 null일 경우
		int currentPage = cp == null ? 1 : Integer.parseInt(cp);
		
		// DB에서 전체 게시글 수를 조회하여 반환 받기
		int listCount = dao.getReplyListCount(conn);
		
		close(conn); 
		
		// 얻어온 현재 페이지와, DB에서 조회한 전체 게시글 수를 이용하여
		// PageInfo 객체를 생성함
		return new PageInfo(currentPage, listCount);
	}
 
	/**  전체 댓글 수 반환 Service
	 * @return listCount
	 * @throws Exception
	 */
	public int getReplyCount() throws Exception {
		Connection conn = getConnection();
		
		int listCount = dao.getReplyListCount(conn);
		
		close(conn);
		
		return listCount;
	}

	/** 모든 신고된 게시글 목록 조회 Service
	 * @return
	 * @throws Exception
	 */
	public List<ReportBoard> selectReportedBoardList(PageInfo pInfo) throws Exception {
		Connection conn = getConnection();
		
		List<ReportBoard> rbList = dao.selectReportedBoardList(conn, pInfo);
		
		close(conn);
		
		return rbList;
	}

	/**  모든 신고된 게시글 페이징을 위한 값 계산 Service
	 * @return listCount
	 * @throws Exception
	 */
	public PageInfo getReportBoardPageInfo(String cp) throws Exception {
		Connection conn = getConnection();
		
		// cp가 null일 경우
		int currentPage = cp == null ? 1 : Integer.parseInt(cp);
		
		// DB에서 전체 게시글 수를 조회하여 반환 받기
		int listCount = dao.getReportBoardPageInfo(conn);
		
		close(conn); 
		
		// 얻어온 현재 페이지와, DB에서 조회한 전체 게시글 수를 이용하여
		// PageInfo 객체를 생성함
		return new PageInfo(currentPage, listCount);
	}

	/** 모든 신고된 댓글 페이징을 위한 값 계산 Service
	 * @param cp
	 * @return
	 * @throws Exception
	 */
	public PageInfo getReportReplyPageInfo(String cp) throws Exception {
		Connection conn = getConnection();
		
		// cp가 null일 경우
		int currentPage = cp == null ? 1 : Integer.parseInt(cp);
		
		// DB에서 전체 게시글 수를 조회하여 반환 받기
		int listCount = dao.getReportReplyPageInfo(conn);
		
		close(conn); 
		
		// 얻어온 현재 페이지와, DB에서 조회한 전체 게시글 수를 이용하여
		// PageInfo 객체를 생성함
		return new PageInfo(currentPage, listCount);
	}

	/** 모든 신고된 댓글 목록 조회 Service
	 * @param pInfo
	 * @return rrList
	 * @throws Exception
	 */
	public List<VReportReply> selectReportedReplyList(PageInfo pInfo) throws Exception {
		Connection conn = getConnection();
		
		List<VReportReply> rrList = dao.selectReportedReplyList(conn, pInfo);
		
		close(conn);
		
		return rrList;
	}

	/** DB에 있는 파일 이름 가져오기 Service
	 * @return dbFileList
	 * @throws Exception
	 */
	public List<String> getDbFileName() throws Exception {
		Connection conn = getConnection();
		
		List<String> noticeFileList = dao.getNoticeFileList(conn);
		List<String> dbFileList = dao.getDbFileName(conn);
		
		for(String s : noticeFileList) {
			dbFileList.add(s);
		}
		
		close(conn);
		
		return dbFileList;
	}

}
