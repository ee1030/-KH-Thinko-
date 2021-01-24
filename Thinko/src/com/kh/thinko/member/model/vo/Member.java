package com.kh.thinko.member.model.vo;

import java.sql.Date;

/**
 * @author jeonga
 *
 */
public class Member {
	private int memberNo;
	private String memberId;
	private String memberPwd;
	private String memberNm;
	private String memberPhone;
	private String memberEmail;
	private String membershipType;
	private String memberInterest;
	private Date memberEnrollDate;
	private String memberStatus;
	private String memberGrade;
	private Date lastLoginDate;
	
	public Member() {}
	
	
	
	
	// 회원가입
	public Member(String memberId, String memberPwd, String memberNm, String memberPhone, String memberEmail,
			String membershipType, String memberInterest) {
		super();
		this.memberId = memberId;
		this.memberPwd = memberPwd;
		this.memberNm = memberNm;
		this.memberPhone = memberPhone;
		this.memberEmail = memberEmail;
		this.membershipType = membershipType;
		this.memberInterest = memberInterest;
	}
	
	// 세션
	public Member(int memberNo, String memberId, String memberNm, String memberPhone, String memberEmail,
			String membershipType, String memberInterest) {
		super();
		this.memberNo = memberNo;
		this.memberId = memberId;
		this.memberNm = memberNm;
		this.memberPhone = memberPhone;
		this.memberEmail = memberEmail;
		this.memberInterest = memberInterest;
		this.membershipType = membershipType;
	}
	
	





	// 로그인
	public Member(int memberNo, String memberId, String memberNm, String memberPhone, String memberEmail,
			String membershipType, String memberInterest, Date memberEnrollDate, String memberStatus,
			String memberGrade, Date lastLoginDate) {
		super();
		this.memberNo = memberNo;
		this.memberId = memberId;
		this.memberNm = memberNm;
		this.memberPhone = memberPhone;
		this.memberEmail = memberEmail;
		this.membershipType = membershipType;
		this.memberInterest = memberInterest;
		this.memberEnrollDate = memberEnrollDate;
		this.memberStatus = memberStatus;
		this.memberGrade = memberGrade;
		this.lastLoginDate = lastLoginDate;
	}




	public Member(int memberNo, String memberId, String memberPwd, String memberNm, String memberPhone,
			String memberEmail, String membershipType, String memberInterest, Date memberEnrollDate, String memberStatus) {
		super();
		this.memberNo = memberNo;
		this.memberId = memberId;
		this.memberPwd = memberPwd;
		this.memberNm = memberNm;
		this.memberPhone = memberPhone;
		this.memberEmail = memberEmail;
		this.membershipType = membershipType;
		this.memberInterest = memberInterest;
		this.memberEnrollDate = memberEnrollDate;
		this.memberStatus = memberStatus;
	}


	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberPwd() {
		return memberPwd;
	}

	public void setMemberPwd(String memberPwd) {
		this.memberPwd = memberPwd;
	}

	public String getMemberNm() {
		return memberNm;
	}

	public void setMemberNm(String memberNm) {
		this.memberNm = memberNm;
	}

	public String getMemberPhone() {
		return memberPhone;
	}

	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}

	public String getMemberEmail() {
		return memberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	public String getMembershipType() {
		return membershipType;
	}
	
	public void setMembershipType(String membershipType) {
		this.membershipType = membershipType;
	}

	public String getMemberInterest() {
		return memberInterest;
	}

	public void setMemberInterest(String memberInterest) {
		this.memberInterest = memberInterest;
	}

	public Date getMemberEnrollDate() {
		return memberEnrollDate;
	}

	public void setMemberEnrollDate(Date memberEnrollDate) {
		this.memberEnrollDate = memberEnrollDate;
	}

	public String getMemberStatus() {
		return memberStatus;
	}

	public void setMemberStatus(String memberStatus) {
		this.memberStatus = memberStatus;
	}

	
	public String getMemberGrade() {
		return memberGrade;
	}

	public void setMemberGrade(String memberGrade) {
		this.memberGrade = memberGrade;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}




	@Override
	public String toString() {
		return "Member [memberNo=" + memberNo + ", memberId=" + memberId + ", memberPwd=" + memberPwd + ", memberNm="
				+ memberNm + ", memberPhone=" + memberPhone + ", memberEmail=" + memberEmail + ", membershipType="
				+ membershipType + ", memberInterest=" + memberInterest + ", memberEnrollDate=" + memberEnrollDate
				+ ", memberStatus=" + memberStatus + "]";
	}


	
	
	

}