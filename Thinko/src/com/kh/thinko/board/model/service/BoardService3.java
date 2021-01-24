package com.kh.thinko.board.model.service;

import static com.kh.thinko.common.JDBCTemplate.*;

import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.kh.thinko.board.model.dao.BoardDAO1;
import com.kh.thinko.board.model.dao.BoardDAO3;
import com.kh.thinko.board.model.exception.FileInsertFailedException;
import com.kh.thinko.board.model.vo.Attachment;
import com.kh.thinko.board.model.vo.VBoard;

/**게시글 등록, 수정, 삭제 Service
 * @author jeonga
 *
 */
public class BoardService3 {

	private BoardDAO3 dao = new BoardDAO3();
	private BoardDAO1 dao2 = new BoardDAO1();

	/**
	 * 게시글 등록 Service
	 * 
	 * @param map
	 * @return result
	 * @throws Exception
	 */
	public int insertBoard(Map<String, Object> map) throws Exception {
		Connection conn = getConnection();
		int result = 0;

		int boardNo = dao.selectNextNo(conn);

		if (boardNo > 0) {
			map.put("boardNo", boardNo);

			Pattern pattern = Pattern.compile("<img[^>]*src=[\"']?([^>\"']+)[\"']?[^>]*>"); // img 태그 src 추출 정규표현식
			Matcher matcher = pattern.matcher((String) map.get("boardContent"));

			List<String> imageList = new ArrayList<String>();
			List<Attachment> fList = new ArrayList<Attachment>();

			// 정규표현식으로 게시글 내용에 포함된 img 태그 src 경로 중 파일명만 imageList에 저장
			while (matcher.find()) {
				String src = matcher.group(1).toString();

				src = src.substring(src.lastIndexOf("/") + 1);

				imageList.add(src);
			}

			try {
				result = dao.insertBoard(conn, map);

				if (result > 0 && !imageList.isEmpty()) {

					result = 0;

					for (int i = 0; i < imageList.size(); i++) {
						Attachment at = new Attachment();
						at.setFilePath((String)map.get("filePath"));
						at.setFileName(imageList.get(i).toString());
						at.setFileLevel(i);
						at.setParentBoardNo(boardNo);

						fList.add(at);
					}

					for (Attachment file : fList) {
						result = dao.insertAttachment(conn, file);

						if (result == 0) {
							throw new FileInsertFailedException("파일 정보 삽입 실패");
						}
					}
				}
			} catch (Exception e) {

				if (!fList.isEmpty()) {
					for (Attachment file : fList) {
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
				result = boardNo;
			} else {
				rollback(conn);
			}
		}
		
		if(map.containsKey("lat")) {
			int insertLocation = dao.insertLocation(conn, map, boardNo);
		
			if(insertLocation > 0) {
				commit(conn);
			} else {
				rollback(conn);
			}
		}
		
		close(conn);

		return result;
	}

	/**
	 * 게시글 수정 화면 출력용 Service
	 * 
	 * @param boardNo
	 * @return board
	 * @throws Exception
	 */
	public VBoard updateView(int boardNo) throws Exception {
		Connection conn = getConnection();

		VBoard board = dao2.selectBoard(conn, boardNo);

		close(conn);

		return board;
	}

	/**
	 * 게시글 수정 Service
	 * 
	 * @param map
	 * @return result
	 * @throws Exception
	 */
	public int updateBoard(Map<String, Object> map) throws Exception {
		Connection conn = getConnection();

		int result = 0;
		int boardNo = (int) map.get("boardNo");

		Pattern pattern = Pattern.compile("<img[^>]*src=[\"']?([^>\"']+)[\"']?[^>]*>"); // img 태그 src 추출 정규표현식
		Matcher matcher = pattern.matcher((String) map.get("boardContent"));

		List<String> imageList = new ArrayList<String>();
		List<Attachment> newFiles = new ArrayList<Attachment>();
		

		while (matcher.find()) {
			String src = matcher.group(1).toString();

			src = src.substring(src.lastIndexOf("/") + 1);

			imageList.add(src);
		}

		try {
			// 이미지 없는 수정
			result = dao.updateBoard(conn, map);

			List<String> oldFiles = dao.selectBoardFiles(conn, boardNo);

			// 신규 파일 o / 기존 파일 x
			if (result > 0 && !imageList.isEmpty() && oldFiles.isEmpty()) {

				result = 0;
				Attachment at = new Attachment();

				for (int i = 0; i < imageList.size(); i++) {
					at.setFilePath((String)map.get("filePath"));
					at.setFileName(imageList.get(i).toString());
					at.setFileLevel(i);
					at.setParentBoardNo(boardNo);

					result = dao.insertAttachment(conn, at);

					if (result == 0) {
						throw new FileInsertFailedException("파일 정보 삽입 실패");
					}
				}
			}
			// 신규 파일 x / 기존 파일 o
			else if (result > 0 && imageList.isEmpty() && !oldFiles.isEmpty()) {

				result = dao.deleteAttachment(conn, boardNo);

			}
			// 신규 파일 o / 기존 파일 o
			else if (result > 0 && !imageList.isEmpty() && !oldFiles.isEmpty()) {
				
				result = 0;

				result = dao.deleteAttachment(conn, boardNo);

				if(result>0) {
					
					for (int i = 0; i < imageList.size(); i++) {
						Attachment at = new Attachment();
						at.setFilePath((String)map.get("filePath"));
						at.setFileName(imageList.get(i).toString());
						at.setFileLevel(i);
						at.setParentBoardNo(boardNo);

						newFiles.add(at);
					}
					
					for (Attachment newfile : newFiles) {
						result = dao.insertAttachment(conn, newfile);

						if (result == 0) {
							throw new FileInsertFailedException("파일 정보 삽입 실패");
						}
					}
					
				}else {
					throw new FileInsertFailedException("파일 정보 삭제 실패");
				}
				 
			}

		} catch (Exception e) {
			if (!newFiles.isEmpty()) {
				for (Attachment file : newFiles) {
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
			result = boardNo;
		} else {
			rollback(conn);
		}
		close(conn);

		return result;
	}

	/**
	 * 게시글 삭제 Service
	 * 
	 * @param boardNo
	 * @return result
	 * @throws Exception
	 */
	
	public int updateBoardStatus(int boardNo) throws Exception {

		Connection conn = getConnection();

		int result = dao.updateBoardStatus(conn, boardNo);

		List<String> oldFiles = dao.selectBoardFiles(conn, boardNo);

		if (!oldFiles.isEmpty()) {
			result = 0;
			result = dao.deleteAttachment(conn, boardNo);
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
