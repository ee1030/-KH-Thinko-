package com.kh.thinko.member.model.dao;

import static com.kh.thinko.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import com.kh.thinko.member.model.vo.Member;

public class MemberDAO2 {

	   private Statement stmt = null;
	   private PreparedStatement pstmt = null;
	   private ResultSet rset = null;
	   private Properties prop = null;
	   
	   public MemberDAO2() {
		      try {
		          String filePath 
		          = MemberDAO2.class.
		          getResource("/com/kh/thinko/sql/member/member-query2.xml").getPath();
		          
		          
		          prop = new Properties();
		          prop.loadFromXML(new FileInputStream(filePath));
		          
		          
		       }catch (Exception e) {
		          e.printStackTrace();
		       }
	   }

	/** 회원 로그인 DAO
	 * @param conn
	 * @param member
	 * @return result
	 * @throws Exception
	 */
	public Member loginMember(Connection conn, Member member) throws Exception {

		
		Member loginMember = null;
	      
	      String query = prop.getProperty("loginMember");
	      
	      try {
	       
	         pstmt = conn.prepareStatement(query);
	         
	         
	         pstmt.setString(1, member.getMemberId());
	         pstmt.setString(2,  member.getMemberPwd());
	         
	         rset = pstmt.executeQuery();
	         
				
			if(rset.next()) {
				loginMember = new Member(rset.getInt("MEMBER_NO"),
											rset.getString("MEMBER_ID"),
											rset.getString("MEMBER_NM"),
											rset.getString("MEMBER_PHONE"),
											rset.getString("MEMBER_EMAIL"),
											rset.getString("MEMBERSHIP_TYPE"),
											rset.getString("MEMBER_INTEREST"),
											rset.getDate("MEMBER_ENROLL_DATE"),
											rset.getString("MEMBER_STATUS"),
											rset.getString("MEMBER_GRADE"),
											rset.getDate("LAST_LOGIN_DATE"));

			}
				 
	      } finally {
	       
	         close(rset);
	         close(pstmt);
	      }
	      return loginMember;
	   }

	/** 아이디 찾기 DAO
	 * @param conn
	 * @param memberEmail
	 * @param memberNm
	 * @return result
	 * @throws Exception
	 */
	public String searchId(Connection conn, String memberNm, String memberEmail) throws Exception {

		String result= null;
		
		String query = prop.getProperty("searchId");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, memberNm);
			pstmt.setString(2, memberEmail);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getString(1);
			}
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return result;
	}

	/** 비밀번호 찾기 DAO
	 * @param conn
	 * @param memberId
	 * @param memberEmail
	 * @return result
	 * @throws Exception
	 */
	public int searchPw(Connection conn, String memberId, String memberEmail) throws Exception {

		int result= 0;
		
		String query = prop.getProperty("searchPw");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberEmail);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt(1);
			}
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return result;
		

	}

	
	/** 암호화된 이메일 삽입 DAO
	 * @param conn
	 * @param memberId
	 * @param code
	 * @return result
	 * @throws Exception
	 */
	public int insertEncryptEmail(Connection conn, String memberId, String code) throws Exception {
		
		int result = 0;
		
		String query = prop.getProperty("insertEncryptEmail");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, memberId);
			pstmt.setString(2, code);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(stmt);
		}
		
		return result;
	}

	
	
	/** 암호화된 이메일과 일치하는 회원 번호 조회 DAO
	 * @param conn
	 * @param code
	 * @return result
	 * @throws Exception
	 */
	public int selectEncryptEmail(Connection conn, String code) throws Exception {
		int result= 0;
		
		String query = prop.getProperty("selectEncryptEmail");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, code);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt(1);
				System.out.println("result : " + result); 
			}
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return result;
	}

	
	
	/** 새 비밀번호로 변경 DAO
	 * @param conn
	 * @param memberNo
	 * @param newPw
	 * @return result
	 * @throws Exception
	 */
	public int updatePw(Connection conn, int memberNo, String newPw) throws Exception {
		
		int result = 0;
		
		String query = prop.getProperty("updatePw");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, newPw);
			pstmt.setInt(2, memberNo);
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	/** 마지막 로그인 날짜 업데이트 DAO
	 * @param conn
	 * @param member
	 * @return result
	 * @throws Exception
	 */
	public int updateLastLogin(Connection conn, Member member) throws Exception {
		int result = 0;
		
		String query = prop.getProperty("updateLastLogin");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPwd());
			
			result = pstmt.executeUpdate();
			System.out.println(result);
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	
	/** 한번 일치한 암호화된 이메일(code)를 삭제하는 DAO
	 * @param conn
	 * @param code
	 * @return result
	 * @throws Exception
	 */
	public int deleteEncryptEmail(Connection conn, String code) throws Exception {
		int result = 0;
				
		String query = prop.getProperty("deleteEncryptEmail");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, code);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}

}
