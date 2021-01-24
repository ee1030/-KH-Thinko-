package com.kh.thinko.notice.model.dao;

import static com.kh.thinko.common.JDBCTemplate.close;
import static com.kh.thinko.common.JDBCTemplate.commit;
import static com.kh.thinko.common.JDBCTemplate.rollback;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.kh.thinko.board.model.vo.Attachment;
import com.kh.thinko.board.model.vo.NAttachment;
import com.kh.thinko.board.model.vo.PageInfo;
import com.kh.thinko.board.model.vo.VBoard;
import com.kh.thinko.member.model.dao.MemberDAO;
import com.kh.thinko.notice.model.vo.Notice;

public class NoticeDAO {
	
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rset = null;
	private Properties prop = null;
	
	public NoticeDAO() {
		try {
			String filePath 
				= MemberDAO.class.getResource("/com/kh/thinko/sql/notice/notice-query.xml").getPath();

			prop = new Properties();
			prop.loadFromXML(new FileInputStream(filePath));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 공지사항 페이징 처리를 위합 값 계산 DAO
	 * @param conn
	 * @return result
	 * @throws Exception
	 */
	public int getListCount(Connection conn) throws Exception {
		int result = 0;
		
		String query = prop.getProperty("getListCount");
		
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

	/** 공지사항 목록 조회 DAO
	 * @param conn
	 * @param pInfo
	 * @return nList
	 * @throws Exception
	 */
	public List<Notice> selectNoticeList(Connection conn, PageInfo pInfo) throws Exception {
		List<Notice> nList = null;
		
		String query = prop.getProperty("selectNoticeList");
		
		try {
			// SQL 구문 조건절에 대입할 변수 생성
			int startRow = (pInfo.getCurrentPage() - 1) * pInfo.getLimit() + 1;
			int endRow = startRow + pInfo.getLimit() - 1;
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			nList = new ArrayList<Notice>();
			
			while(rset.next()) {
				Notice notice = new Notice();
				notice.setNoticeNo(rset.getInt("NOTICE_NO"));
				notice.setNoticeTitle(rset.getString("NOTICE_TITLE"));
				notice.setNoticeContent(rset.getString("NOTICE_CONTENT"));
				notice.setNoticeCreateDate(rset.getTimestamp("NOTICE_CREATE_DT"));
				notice.setNoticeStatus(rset.getString("NOTICE_STATUS"));
				notice.setMemberId(rset.getString("MEMBER_ID"));
				notice.setReadCount(rset.getInt("READ_COUNT"));
				
				nList.add(notice);
			}
			
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return nList;
	}

	/** 공지사항 상세조회 DAO
	 * @param conn
	 * @param noticeNo
	 * @return notice
	 * @throws Exception
	 */
	public Notice selectNotice(Connection conn, int noticeNo) throws Exception {
		Notice notice = null;

		String query = prop.getProperty("selectNotice");

		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, noticeNo);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				notice = new Notice();
				notice.setNoticeNo(rset.getInt("NOTICE_NO"));
				notice.setNoticeTitle(rset.getString("NOTICE_TITLE"));
				notice.setNoticeContent(rset.getString("NOTICE_CONTENT"));
				notice.setNoticeCreateDate(rset.getTimestamp("NOTICE_CREATE_DT"));
				notice.setNoticeStatus(rset.getString("NOTICE_STATUS"));
				notice.setMemberId(rset.getString("MEMBER_ID"));
				notice.setReadCount(rset.getInt("READ_COUNT"));
			}

		} finally {
			close(rset);
			close(pstmt);
		}

		return notice;
	}
	
	/**  조회수 증가 DAO
	 * @param conn
	 * @param boardNo
	 * @return result
	 * @throws Exception
	 */
	public int increaseReadCount(Connection conn, int boardNo) throws Exception {
		int result = 0;
		
		String query = prop.getProperty("increaseReadCount");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	/** 다음 공지사항 번호 조회 DAO
	 * @param conn
	 * @return noticeNo
	 * @throws Exception
	 */
	public int selectNextNo(Connection conn) throws Exception {
		int noticeNo = 0;
		
		String query = prop.getProperty("selectNextNo");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {
				noticeNo = rset.getInt(1);
			}
			
		} finally {
			close(rset);
			close(stmt);
		}
		
		return noticeNo;
	}

	/** 공지사항 작성 DAO
	 * @param conn
	 * @param map
	 * @return result
	 * @throws Exception
	 */
	public int insertNotice(Connection conn, Map<String, Object> map) throws Exception {
		int result = 0;
		
		String query = prop.getProperty("insertNotice");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, (int)map.get("noticeNo"));
			pstmt.setString(2, (String)map.get("noticeTitle"));
			pstmt.setString(3, (String)map.get("noticeContent"));
			pstmt.setInt(4, (int)map.get("noticeWriter"));
			
			result = pstmt.executeUpdate();
			
			if(result > 0) {
				commit(conn);
			}else {
				rollback(conn);
			}
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	/** 파일 정보 삽입 DAO
	 * @param conn
	 * @param at
	 * @return result
	 * @throws Exception
	 */
	public int insertNAttachment(Connection conn, NAttachment at) throws Exception {
		int result = 0;
		
		String query = prop.getProperty("insertNAttachment");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, at.getFilePath());
			pstmt.setString(2, at.getFileName());
			pstmt.setInt(3, at.getFileLevel());
			pstmt.setInt(4, at.getParentNoticeNo());
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	/** 공지사항 수정 DAO
	 * @param conn
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateNotice(Connection conn, Map<String, Object> map) throws Exception {
		
		int result = 0;
		
		String query = prop.getProperty("updateNotice");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, (String)map.get("noticeTitle") );
			pstmt.setString(2, (String)map.get("noticeContent") );
			pstmt.setInt(3, (int)map.get("noticeNo"));
			
			result = pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	/** 공지사항 검색 결과 공지사항 수 조회 DAO
	 * @param conn
	 * @param condition
	 * @return listCount
	 * @throws Exception
	 */
	public int getSearchListCount(Connection conn, String condition) throws Exception {
		int listCount = 0;

		String query = "SELECT COUNT(*) FROM V_NOTICE WHERE" + condition;

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

	/** 공지사항 검색 목록 조회 DAO
	 * @param conn
	 * @param pInfo
	 * @param condition
	 * @param map
	 * @return nList
	 * @throws Exception
	 */
	public List<Notice> searchNoticeList(Connection conn, PageInfo pInfo, String condition, Map<String, Object> map) throws Exception {
		List<Notice> nList = null; // 최종 조회 결과를 담을 변수

		String query = "SELECT * FROM" + "    (SELECT ROWNUM RNUM , V.*" + "    FROM"
				+ "        (SELECT * FROM V_NOTICE " + "        WHERE " + condition
				+ "         ORDER BY NOTICE_NO DESC) V )" + "WHERE RNUM BETWEEN ? AND ?";

		try {
			// SQL 구문에 사용될 위치 홀더값 생성
			int startRow = (pInfo.getCurrentPage() - 1) * pInfo.getLimit() + 1;
			int endRow = startRow + pInfo.getLimit() - 1;

			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);

			rset = pstmt.executeQuery();

			nList = new ArrayList<Notice>();

			while (rset.next()) {
				Notice notice = new Notice();
				
				notice.setNoticeNo(rset.getInt("NOTICE_NO"));
				notice.setNoticeTitle(rset.getString("NOTICE_TITLE"));
				notice.setNoticeContent(rset.getString("NOTICE_CONTENT"));
				notice.setNoticeCreateDate(rset.getTimestamp("NOTICE_CREATE_DT"));
				notice.setNoticeStatus(rset.getString("NOTICE_STATUS"));
				notice.setMemberId(rset.getString("MEMBER_ID"));
				notice.setReadCount(rset.getInt("READ_COUNT"));

				nList.add(notice);
			}

		} finally {
			close(rset);
			close(pstmt);
		}

		return nList;
	}
	
	/** 파일 정보 불러오기 DAO
	 * @param conn
	 * @param boardNo
	 * @return fList
	 * @throws Exception
	 */
	public List<NAttachment> selectNoticeFiles(Connection conn, int noticeNo) throws Exception{
		List<NAttachment> fList = null;
		
		String query = prop.getProperty("selectNoticeFiles");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noticeNo);
			rset = pstmt.executeQuery();
			fList = new ArrayList<NAttachment>();
			
			while(rset.next()) {
			
				NAttachment at = new NAttachment(		
						rset.getInt("NOTICE_FILE_NO"), 
						rset.getString("NOTICE_FILE_PATH"), 
						rset.getString("NOTICE_FILE_NAME"),
						rset.getInt("NOTICE_FILE_LEVEL"),
						rset.getInt("NOTICE_NO")
						);
				
				
				fList.add(at);
			}
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return fList;
	}

	/** 파일 정보 삭제 DAO
	 * @param conn
	 * @param boardNo
	 * @return result
	 * @throws Exception
	 */
	public int deleteAttachment(Connection conn, int noticeNo) throws Exception {
		int result = 0;
		String query = prop.getProperty("deleteAttachment");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noticeNo);
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	/** 공지사항 삭제 DAO
	 * @param conn
	 * @param noticeNo
	 * @return result
	 * @throws Exception
	 */
	public int updateNoticeStatus(Connection conn, int noticeNo) throws Exception {
		int result = 0;
		String query = prop.getProperty("updateNoticeStatus");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noticeNo);
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}

}
