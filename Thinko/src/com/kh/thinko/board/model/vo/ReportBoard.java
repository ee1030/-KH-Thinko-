package com.kh.thinko.board.model.vo;

import java.sql.Timestamp;

public class ReportBoard {
	private int boardNo;
	private String boardTitle;
	private String memberId;
	private int readCount;
	private Timestamp boardCreateDate;
	private String boardStatus;
	private int reportCount;
	private String boardTypeName;

	public ReportBoard() {}
	
	public ReportBoard(int boardNo, String boardTitle, String memberId, int readCount, Timestamp boardCreateDate,
			String boardStatus, int reportCount, String boardTypeName) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.memberId = memberId;
		this.readCount = readCount;
		this.boardCreateDate = boardCreateDate;
		this.boardStatus = boardStatus;
		this.reportCount = reportCount;
		this.boardTypeName = boardTypeName;
	}

	public String getBoardTypeName() {
		return boardTypeName;
	}

	public void setBoardTypeName(String boardTypeName) {
		this.boardTypeName = boardTypeName;
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

	public String getBoardStatus() {
		return boardStatus;
	}

	public void setBoardStatus(String boardStatus) {
		this.boardStatus = boardStatus;
	}

	public int getReportCount() {
		return reportCount;
	}

	public void setReportCount(int reportCount) {
		this.reportCount = reportCount;
	}

	@Override
	public String toString() {
		return "ReportBoard [boardNo=" + boardNo + ", boardTitle=" + boardTitle + ", memberId=" + memberId
				+ ", readCount=" + readCount + ", boardCreateDate=" + boardCreateDate + ", boardStatus=" + boardStatus
				+ ", reportCount=" + reportCount + "]";
	}
	
	
}