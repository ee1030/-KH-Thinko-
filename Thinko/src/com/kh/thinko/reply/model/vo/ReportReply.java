package com.kh.thinko.reply.model.vo;

import java.sql.Date;

public class ReportReply {

	private int rpReportNo;
	private Date rpReportCrateDt;
	private String rpReportStatus;
	private int rpReportCategoryNo;
	private int memberNo;
	private int replyNo;
	
	public ReportReply() {
		// TODO Auto-generated constructor stub
	}

	public ReportReply(int rpReportNo, Date rpReportCrateDt, String rpReportStatus, int rpReportCategoryNo,
			int memberNo, int replyNo) {
		super();
		this.rpReportNo = rpReportNo;
		this.rpReportCrateDt = rpReportCrateDt;
		this.rpReportStatus = rpReportStatus;
		this.rpReportCategoryNo = rpReportCategoryNo;
		this.memberNo = memberNo;
		this.replyNo = replyNo;
	}

	public int getRpReportNo() {
		return rpReportNo;
	}

	public void setRpReportNo(int rpReportNo) {
		this.rpReportNo = rpReportNo;
	}

	public Date getRpReportCrateDt() {
		return rpReportCrateDt;
	}

	public void setRpReportCrateDt(Date rpReportCrateDt) {
		this.rpReportCrateDt = rpReportCrateDt;
	}

	public String getRpReportStatus() {
		return rpReportStatus;
	}

	public void setRpReportStatus(String rpReportStatus) {
		this.rpReportStatus = rpReportStatus;
	}

	public int getRpReportCategoryNo() {
		return rpReportCategoryNo;
	}

	public void setRpReportCategoryNo(int rpReportCategoryNo) {
		this.rpReportCategoryNo = rpReportCategoryNo;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public int getReplyNo() {
		return replyNo;
	}

	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
	}

	@Override
	public String toString() {
		return "ReportReply [rpReportNo=" + rpReportNo + ", rpReportCrateDt=" + rpReportCrateDt + ", rpReportStatus="
				+ rpReportStatus + ", rpReportCategoryNo=" + rpReportCategoryNo + ", memberNo=" + memberNo
				+ ", replyNo=" + replyNo + "]";
	}
	
	 
	
}
