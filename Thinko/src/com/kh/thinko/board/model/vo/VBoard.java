package com.kh.thinko.board.model.vo;

import java.sql.Timestamp;

public class VBoard {
	private int boardNo;
	private String boardTitle;
	private String boardContent;
	private String memberId;
	private int readCount;
	private Timestamp boardCreateDate;
	private Timestamp boardModifyDate;
	private int boardTypeNo;
	private int boardCategoryNo;
	private String boardStatus;
	private String boardTypeName;
	private String boardCategoryName;
	private int likeCount;
	private int replyCount;

	public VBoard() {}

	
	
	
	public VBoard(int boardNo, String boardTitle, String boardContent, String memberId, int readCount,
			Timestamp boardCreateDate, String boardCategoryName, int likeCount) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.memberId = memberId;
		this.readCount = readCount;
		this.boardCreateDate = boardCreateDate;
		this.boardCategoryName = boardCategoryName;
		this.likeCount = likeCount;
	}




	public VBoard(int boardNo, String boardTitle, String boardContent, String memberId, int readCount,
			Timestamp boardCreateDate, Timestamp boardModifyDate, int boardTypeNo, int boardCategoryNo,
			String boardStatus, String boardTypeName, String boardCategoryName, int likeCount, int replyCount) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.memberId = memberId;
		this.readCount = readCount;
		this.boardCreateDate = boardCreateDate;
		this.boardModifyDate = boardModifyDate;
		this.boardTypeNo = boardTypeNo;
		this.boardCategoryNo = boardCategoryNo;
		this.boardStatus = boardStatus;
		this.boardTypeName = boardTypeName;
		this.boardCategoryName = boardCategoryName;
		this.likeCount = likeCount;
		this.replyCount = replyCount;
	}
	
	
	

	public VBoard(int boardNo, String boardTitle, String boardContent, String memberId, int readCount,
			Timestamp boardCreateDate, int likeCount, int replyCount) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.memberId = memberId;
		this.readCount = readCount;
		this.boardCreateDate = boardCreateDate;
		this.likeCount = likeCount;
		this.replyCount = replyCount;
	}
	
	

	public VBoard(int boardNo, String boardTitle, String boardContent, String memberId, int readCount,
			Timestamp boardCreateDate, String boardCategoryName, int likeCount, int replyCount) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.memberId = memberId;
		this.readCount = readCount;
		this.boardCreateDate = boardCreateDate;
		this.boardCategoryName = boardCategoryName;
		this.likeCount = likeCount;
		this.replyCount = replyCount;
	}

	public VBoard(int boardNo, String boardTitle, String boardContent, String memberId, int readCount,
			Timestamp boardCreateDate, int likeCount) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.memberId = memberId;
		this.readCount = readCount;
		this.boardCreateDate = boardCreateDate;
		this.likeCount = likeCount;
	}

	
	public VBoard(int boardNo, String boardTitle, String boardContent, String memberId, int readCount,
			Timestamp boardCreateDate, Timestamp boardModifyDate, String boardStatus, String boardTypeName,
			String boardCategoryName, int likeCount, int replyCount) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.memberId = memberId;
		this.readCount = readCount;
		this.boardCreateDate = boardCreateDate;
		this.boardModifyDate = boardModifyDate;
		this.boardStatus = boardStatus;
		this.boardTypeName = boardTypeName;
		this.boardCategoryName = boardCategoryName;
		this.likeCount = likeCount;
		this.replyCount = replyCount;
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

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
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

	public int getBoardTypeNo() {
		return boardTypeNo;
	}

	public void setBoardTypeNo(int boardTypeNo) {
		this.boardTypeNo = boardTypeNo;
	}

	public int getBoardCategoryNo() {
		return boardCategoryNo;
	}

	public void setBoardCategoryNo(int boardCategoryNo) {
		this.boardCategoryNo = boardCategoryNo;
	}

	public String getBoardStatus() {
		return boardStatus;
	}

	public void setBoardStatus(String boardStatus) {
		this.boardStatus = boardStatus;
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

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public int getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}

	@Override
	public String toString() {
		return "VBoard [boardNo=" + boardNo + ", boardTitle=" + boardTitle + ", boardContent=" + boardContent
				+ ", memberId=" + memberId + ", readCount=" + readCount + ", boardCreateDate=" + boardCreateDate
				+ ", boardModifyDate=" + boardModifyDate + ", boardTypeNo=" + boardTypeNo + ", boardCategoryNo="
				+ boardCategoryNo + ", boardStatus=" + boardStatus + ", boardTypeName=" + boardTypeName
				+ ", boardCategoryName=" + boardCategoryName + ", likeCount=" + likeCount + ", replyCount=" + replyCount
				+ "]";
	}

}
