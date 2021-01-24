package com.kh.thinko.member.model.service;

import java.sql.Connection;
import java.sql.SQLIntegrityConstraintViolationException;

import static com.kh.thinko.common.JDBCTemplate.*;

import com.kh.thinko.common.SHA256;
import com.kh.thinko.member.model.dao.MemberDAO2;
import com.kh.thinko.member.model.vo.Member;

/**
 * @author 연창
 *
 */
public class MemberService2 {

	private MemberDAO2 dao = new MemberDAO2();
	
	/** 로그인 Service
	 * @param member
	 * @return loginMember
	 * @throws Exception
	 */
	public Member loginMember(Member member) throws Exception {

		 // 1) Connection 얻어오기
	      Connection conn = getConnection();
	      
	      Member loginMember = null;
	      
	      // 마지막 로그인 날짜 업데이트
	      int result = dao.updateLastLogin(conn, member);
	      
	      
	      // 아이디와 비밀번호가 입력되어 로그인 날짜가 업데이트 되었으면
	      if(result > 0) {
	    	  commit(conn);
	      } else {
	    	  rollback(conn);
	      }
	      
	      // 2) DAO 메소드를 수행하여 결과 반환받기
    	  loginMember = dao.loginMember(conn, member);
	      
	      // 3) Connection 반환하기
	      close(conn);
	      
	      // 4) DAO 수행 결과를 Controller 반환
	      return loginMember;
	}

	/** 아이디 찾기 Service
	 * @param memberNm
	 * @param memberEmail
	 * @return result
	 * @throws Exception
	 */
	public String searchId(String memberNm, String memberEmail) throws Exception{
		Connection conn = getConnection();
		
		String result = dao.searchId(conn, memberNm, memberEmail);
		
		close(conn);
		
		return result;

	}

	/** 비밀번호 찾기 Service
	 * @param memberId
	 * @param memberEmail
	 * @return result
	 * @throws Exception
	 */
	public int searchPw(String memberId, String memberEmail, String encryptEmail) throws Exception{

		Connection conn = getConnection();
		
		// 아이디, 이메일이 일치하는 회원 조회
		int result = dao.searchPw(conn, memberId, memberEmail);
		
		if(result > 0) {
			
			try {
				result = dao.insertEncryptEmail(conn, memberId, encryptEmail);
			}catch(SQLIntegrityConstraintViolationException e) {
				// 이미 해당 회원이 비밀번호 변경을 시도한적이 있어
				// ENCRYPT_EMAIL 테이블에  삽입된 회원번호가 있을 경우
				
				result = 1;
			}
			if(result > 0)	commit(conn);
			else			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	
	
	/** 암호화된 이메일과 일치하는 회원 번호 조회 Service
	 * @param code
	 * @return result
	 * @throws Exception
	 */
	public int selectEncryptEmail(String code) throws Exception {
		Connection conn = getConnection();
		
		// 아이디, 이메일이 일치하는 회원 조회
		int result = dao.selectEncryptEmail(conn, code);
		
		// 한번 일치한 암호화된 이메일(code)를 삭제
		if(result > 0) {
			int result2 = dao.deleteEncryptEmail(conn, code);
			
			if(result2 > 0) commit(conn);
			else	rollback(conn);
			
		}
		
		close(conn);
		
		return result;
	}

	
	/** 새 비밀번호로 변경 Service 
	 * @param memberNo
	 * @param newPw
	 * @return reuslt
	 * @throws Exception
	 */
	public int updatePw(int memberNo, String newPw) throws Exception{
		Connection conn = getConnection();
		
		int result = dao.updatePw(conn, memberNo, newPw);
			
		if(result > 0)	commit(conn);
		else			rollback(conn);
		
		close(conn);
		
		return result;
	}

}
