package com.kh.thinko.notice.model.service;

import static com.kh.thinko.common.JDBCTemplate.*;

import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.kh.thinko.board.model.exception.FileInsertFailedException;
import com.kh.thinko.board.model.vo.Attachment;
import com.kh.thinko.board.model.vo.NAttachment;
import com.kh.thinko.board.model.vo.PageInfo;
import com.kh.thinko.board.model.vo.VBoard;
import com.kh.thinko.notice.model.dao.NoticeDAO;
import com.kh.thinko.notice.model.vo.Notice;

public class NoticeService {
	
	private NoticeDAO dao = new NoticeDAO();

	/** 공지사항 목록 페이징을 위한 값 계산 Service
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

	/** 공지사항 목록 조회 Service
	 * @param pInfo
	 * @return nList
	 * @throws Exception
	 */
	public List<Notice> selectNoticeList(PageInfo pInfo) throws Exception {
		Connection conn = getConnection();
		
		List<Notice> nList = dao.selectNoticeList(conn, pInfo);
		
		close(conn);
		
		return nList;
	}

	/** 공지사항 상세조회 Service
	 * @param noticeNo
	 * @return notice
	 * @throws Exception
	 */
	public Notice selectNotice(int noticeNo) throws Exception {
		Connection conn = getConnection();
		
		Notice notice = dao.selectNotice(conn, noticeNo);
		
		if(notice != null) {
			int result = dao.increaseReadCount(conn, noticeNo);
			
			if(result > 0) {
				commit(conn);
				
				notice.setReadCount(notice.getReadCount()+1);
			} else {
				rollback(conn);
			}
		}
		
		close(conn);
		
		return notice;
	}

	/** 공지사항 작성 Service
	 * @param map
	 * @return result
	 * @throws Exception
	 */
	public int insertNotice(Map<String, Object> map) throws Exception {
		Connection conn = getConnection();
		int result = 0;
		
		int noticeNo = dao.selectNextNo(conn);
		
		if(noticeNo > 0) {
			map.put("noticeNo", noticeNo);
			
	        Pattern pattern = Pattern.compile("<img[^>]*src=[\"']?([^>\"']+)[\"']?[^>]*>"); //img 태그 src 추출 정규표현식
	        Matcher matcher = pattern.matcher((String)map.get("noticeContent"));
	         
	        List<String> imageList = new ArrayList<String>();
	        List<NAttachment> fList = new ArrayList<NAttachment>();
	        
	        // 정규표현식으로 게시글 내용에 포함된 img 태그 src 경로 중 파일명만 imageList에 저장
	        while(matcher.find()){
	        	String src = matcher.group(1).toString();
	        	
	        	src = src.substring(src.lastIndexOf("/")+1);
	        	
	        	imageList.add(src);
	        }
	        
	        
	        
			try {
				result = dao.insertNotice(conn, map);
				
				if(result > 0 && !imageList.isEmpty()) {
					
					result = 0; // result 재활용을 위해 0으로 초기화 
					NAttachment at = new NAttachment();
					
					for(int i=0 ; i<imageList.size() ; i++) {
						at.setFilePath((String)map.get("filePath"));
						at.setFileName(imageList.get(i).toString());
						at.setFileLevel(i);
						at.setParentNoticeNo(noticeNo);
						
						fList.add(at);					
					}
					
					for(NAttachment file : fList) {
						result = dao.insertNAttachment(conn, file);
						
						if(result == 0) { // 파일 정보 삽입 실패
							// 강제로 예외 발생
							throw new FileInsertFailedException("파일 정보 삽입 실패");
						}
					}	
				}
				
			} catch (Exception e) {
				if (!fList.isEmpty()) {
					for (NAttachment file : fList) {
						String filePath = file.getFilePath();
						String fileName = file.getFileName();
						File deleteFile = new File(filePath + fileName);

						if (deleteFile.exists()) {
							deleteFile.delete();
						}
					}
				}
				throw e;
			}
			
			if(result > 0) 	{
				commit(conn);
				result = noticeNo;
			}
			else {
				rollback(conn);
			}
		}	
		
		close(conn);
		
		return result;
	}

	/** 공지사항 수정 환면 전환 Service
	 * @param noticeNo
	 * @return notice
	 * @throws Exception
	 */
	public Notice updateView(int noticeNo) throws Exception {
		Connection conn = getConnection();
		
		Notice notice = dao.selectNotice(conn, noticeNo);

		close(conn);
		
		return notice;
	}

	/** 공지사항 수정 Service
	 * @param map
	 * @return result
	 * @throws Exception
	 */
	public int updateNotice(Map<String, Object> map) throws Exception {
		Connection conn = getConnection();
		
		int result = 0; 
		int noticeNo = (int)map.get("noticeNo");
		
        Pattern pattern = Pattern.compile("<img[^>]*src=[\"']?([^>\"']+)[\"']?[^>]*>"); //img 태그 src 추출 정규표현식
        Matcher matcher = pattern.matcher((String)map.get("noticeContent"));
         
        List<String> imageList = new ArrayList<String>();
        List<NAttachment> newFiles = new ArrayList<NAttachment>();

		while (matcher.find()) {
			String src = matcher.group(1).toString();

			src = src.substring(src.lastIndexOf("/") + 1);

			imageList.add(src);
		}

		try {
			// 이미지 없는 수정
			result = dao.updateNotice(conn, map);

			List<NAttachment> oldFiles = dao.selectNoticeFiles(conn, noticeNo);

			// 신규 파일 o / 기존 파일 x
			if (result > 0 && !imageList.isEmpty() && oldFiles.isEmpty()) {

				result = 0;
				NAttachment at = new NAttachment();

				for (int i = 0; i < imageList.size(); i++) {
					at.setFilePath((String)map.get("filePath"));
					at.setFileName(imageList.get(i).toString());
					at.setFileLevel(i);
					at.setParentNoticeNo(noticeNo);

					result = dao.insertNAttachment(conn, at);

					if (result == 0) {
						throw new FileInsertFailedException("파일 정보 삽입 실패");
					}
				}
			}
			// 신규 파일 x / 기존 파일 o
			else if (result > 0 && imageList.isEmpty() && !oldFiles.isEmpty()) {

				result = dao.deleteAttachment(conn, noticeNo);

			}
			// 신규 파일 o / 기존 파일 o
			else if (result > 0 && !imageList.isEmpty() && !oldFiles.isEmpty()) {

				result = 0;

				for (NAttachment oldFile : oldFiles) {
					newFiles.add(oldFile);
				}

				for (int i = 0; i < imageList.size(); i++) {
					NAttachment at = new NAttachment();

					at.setFilePath((String)map.get("filePath"));
					at.setFileName(imageList.get(i).toString());
					at.setFileLevel(i + oldFiles.size());
					at.setParentNoticeNo(noticeNo);

					newFiles.add(at);
				}

				for (NAttachment newFile : newFiles) {

					result = dao.insertNAttachment(conn, newFile);
					if (result == 0) {
						throw new FileInsertFailedException("파일 정보 삽입 실패");
					}
				}
			}

		} catch (Exception e) {
			if (!newFiles.isEmpty()) {
				for (NAttachment file : newFiles) {
					String filePath = file.getFilePath();
					String fileName = file.getFileName();
					File deleteFile = new File(filePath + fileName);

					if (deleteFile.exists()) {
						deleteFile.delete();
					}
				}
			}
			throw e;

		}

		if (result > 0) {
			commit(conn);
			result = noticeNo;
		} else {
			rollback(conn);
		}
		close(conn);

		return result;
	}

	/** 공지사항 검색 목록 페이지 정보 조회 Service
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public PageInfo getSearchPageInfo(Map<String, Object> map) throws Exception {
		Connection conn = getConnection();

		map.put("currentPage",
				(map.get("currentPage") == null) ? 1 : Integer.parseInt((String) map.get("currentPage")));

		String condition = createCondition(map);

		int listCount = 0;

		listCount = dao.getSearchListCount(conn, condition);

		close(conn);

		return new PageInfo((int) map.get("currentPage"), listCount);
	}
	
	/** 공지사항 검색 조건문 생성 Service
	 * @param map
	 * @return condition
	 */
	private String createCondition(Map<String, Object> map) {

		String condition = null;

		String searchKey = (String) map.get("searchKey");
		String searchValue = (String) map.get("searchValue");

		switch (searchKey) {

		case "title":
			condition = " NOTICE_TITLE LIKE '%' || '" + searchValue + "' || '%' ";
			break; // "BOARD_TITLE LIKE '%' || '49' || '%' "

		case "content":
			condition = " NOTICE_CONTENT LIKE '%' || '" + searchValue + "' || '%' ";
			break;

		case "titcont":
			condition = " (NOTICE_TITLE LIKE '%' || '" + searchValue + "' || '%' " + "OR NOTICE_CONTENT LIKE '%' || '"
					+ searchValue + "' || '%') ";
			break;

		case "writer":
			condition = " MEMBER_ID LIKE '%' || '" + searchValue + "' || '%' ";
			break;
		}
		return condition;
	}

	/** 공지사항 검색 목록 조회 Service
	 * @param map
	 * @param pInfo
	 * @return nList
	 * @throws Exception
	 */
	public List<Notice> searchNoticeList(Map<String, Object> map, PageInfo pInfo) throws Exception {
		Connection conn = getConnection();

		String condition = createCondition(map);

		List<Notice> nList = dao.searchNoticeList(conn, pInfo, condition, map);

		close(conn);

		return nList;
	}

	/** 공지사항 삭제 Service
	 * @param noticeNo
	 * @return result
	 * @throws Exception
	 */
	public int updateNoticeStatus(int noticeNo) throws Exception {

		Connection conn = getConnection();

		int result = dao.updateNoticeStatus(conn, noticeNo);

		List<NAttachment> oldFiles = dao.selectNoticeFiles(conn, noticeNo);

		if (!oldFiles.isEmpty()) {
			result = 0;
			result = dao.deleteAttachment(conn, noticeNo);
		}

		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}

		close(conn);

		return result;
	}

}
