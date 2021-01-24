package com.kh.thinko.reply.model.vo;

import java.sql.Timestamp;

public class VReportReply {
	
	private int replyNo;
	private String replyContent;
	private Timestamp replyCreateDate;
	private String replyStatus;
	private String memberId;
	private int parentBoardNo;
	private int reportCount;
	
	public VReportReply() {}

	public VReportReply(int replyNo, String replyContent, Timestamp replyCreateDate, String replyStatus,
			String memberId, int parentBoardNo, int reportCount) {
		super();
		this.replyNo = replyNo;
		this.replyContent = replyContent;
		this.replyCreateDate = replyCreateDate;
		this.replyStatus = replyStatus;
		this.memberId = memberId;
		this.parentBoardNo = parentBoardNo;
		this.reportCount = reportCount;
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

	public int getReportCount() {
		return reportCount;
	}

	public void setReportCount(int reportCount) {
		this.reportCount = reportCount;
	}

	@Override
	public String toString() {
		return "VReportReply [replyNo=" + replyNo + ", replyContent=" + replyContent + ", replyCreateDate="
				+ replyCreateDate + ", replyStatus=" + replyStatus + ", memberId=" + memberId + ", parentBoardNo="
				+ parentBoardNo + ", reportCount=" + reportCount + ", getReplyNo()=" + getReplyNo()
				+ ", getReplyContent()=" + getReplyContent() + ", getReplyCreateDate()=" + getReplyCreateDate()
				+ ", getReplyStatus()=" + getReplyStatus() + ", getMemberId()=" + getMemberId()
				+ ", getParentBoardNo()=" + getParentBoardNo() + ", getReportCount()=" + getReportCount()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
}
