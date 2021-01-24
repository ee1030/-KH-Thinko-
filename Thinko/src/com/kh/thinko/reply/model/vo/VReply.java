package com.kh.thinko.reply.model.vo;

import java.sql.Timestamp;

public class VReply {
	private int replyNo;
	private String replyContent;
	private Timestamp replyCreateDate;
	private String replyStatus;
	private String memberId;
	private int parentBoardNo;
	private String boardTitle;
	private int memberNo;
	
	public VReply() {}

	public VReply(int replyNo, String replyContent, Timestamp replyCreateDate, String replyStatus, String memberId,
			int parentBoardNo, String boardTitle, int memberNo) {
		super();
		this.replyNo = replyNo;
		this.replyContent = replyContent;
		this.replyCreateDate = replyCreateDate;
		this.replyStatus = replyStatus;
		this.memberId = memberId;
		this.parentBoardNo = parentBoardNo;
		this.boardTitle = boardTitle;
		this.memberNo = memberNo;
	}

	public int getReplyNo() {
		return replyNo;
	}

	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public Timestamp getReplyCreateDate() {
		return replyCreateDate;
	}

	public void setReplyCreateDate(Timestamp replyCreateDate) {
		this.replyCreateDate = replyCreateDate;
	}

	public String getReplyStatus() {
		return replyStatus;
	}

	public void setReplyStatus(String replyStatus) {
		this.replyStatus = replyStatus;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public int getParentBoardNo() {
		return parentBoardNo;
	}

	public void setParentBoardNo(int parentBoardNo) {
		this.parentBoardNo = parentBoardNo;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	@Override
	public String toString() {
		return "VReply [replyNo=" + replyNo + ", replyContent=" + replyContent + ", replyCreateDate=" + replyCreateDate
				+ ", replyStatus=" + replyStatus + ", memberId=" + memberId + ", parentBoardNo=" + parentBoardNo
				+ ", boardTitle=" + boardTitle + ", memberNo=" + memberNo + "]";
	}


}
