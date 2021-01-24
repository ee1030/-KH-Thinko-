package com.kh.thinko.board.model.vo;

import java.sql.Timestamp;

/**
 * @author jeonga
 *
 */

public class Board {
   
   private int boardNo;
   private String boardTitle;
   private String boardContent;
   private Timestamp boardCreateDate;
   private Timestamp boardModifyDate;
   private String boardStatus;
   private String boardFix;
   private int readCount;
   private String memberId;
   private String boardTypeName;
   private String boardCategoryName;

   public Board() {}
   
   
   





	public Board(int boardNo, String boardTitle, Timestamp boardCreateDate, int readCount, String memberId) {
	super();
	this.boardNo = boardNo;
	this.boardTitle = boardTitle;
	this.boardCreateDate = boardCreateDate;
	this.readCount = readCount;
	this.memberId = memberId;
}








	public Board(int boardNo, String boardTitle, String boardContent, Timestamp boardCreateDate, Timestamp boardModifyDate,
			String boardStatus, String boardFix, int readCount, String memberId, String boardTypeName,
			String boardCategoryName) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardCreateDate = boardCreateDate;
		this.boardModifyDate = boardModifyDate;
		this.boardStatus = boardStatus;
		this.boardFix = boardFix;
		this.readCount = readCount;
		this.memberId = memberId;
		this.boardTypeName = boardTypeName;
		this.boardCategoryName = boardCategoryName;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public Timestamp getBoardCreateDate() {
		return boardCreateDate;
	}

	public void setBoardCreateDate(Timestamp boardCreateDate) {
		this.boardCreateDate = boardCreateDate;
	}

	public Timestamp getBoardModifyDate() {
		return boardModifyDate;
	}

	public void setBoardModifyDate(Timestamp boardModifyDate) {
		this.boardModifyDate = boardModifyDate;
	}

	public String getBoardStatus() {
		return boardStatus;
	}

	public void setBoardStatus(String boardStatus) {
		this.boardStatus = boardStatus;
	}

	public String getBoardFix() {
		return boardFix;
	}

	public void setBoardFix(String boardFix) {
		this.boardFix = boardFix;
	}

	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getBoardTypeName() {
		return boardTypeName;
	}

	public void setBoardTypeName(String boardTypeName) {
		this.boardTypeName = boardTypeName;
	}

	public String getBoardCategoryName() {
		return boardCategoryName;
	}

	public void setBoardCategoryName(String boardCategoryName) {
		this.boardCategoryName = boardCategoryName;
	}

	@Override
	public String toString() {
		return "Board [boardNo=" + boardNo + ", boardTitle=" + boardTitle + ", boardContent=" + boardContent
				+ ", boardCreateDate=" + boardCreateDate + ", boardModifyDate=" + boardModifyDate + ", boardStatus="
				+ boardStatus + ", boardFix=" + boardFix + ", readCount=" + readCount + ", memberId=" + memberId
				+ ", boardTypeName=" + boardTypeName + ", boardCategoryName=" + boardCategoryName + "]";
	
	}
}
