package com.kh.thinko.member.model.service;

import com.kh.thinko.member.model.vo.PageInfo;
import com.kh.thinko.member.model.vo.PageInfo2;
import com.kh.thinko.board.model.vo.VBoard;
import com.kh.thinko.reply.model.vo.Reply;
import com.kh.thinko.member.model.dao.MemberDAO;
import com.kh.thinko.board.model.vo.Board;
import com.kh.thinko.member.model.vo.Member;
import static com.kh.thinko.common.JDBCTemplate.*;
import java.sql.Connection;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

/**
 * @author jeonga
 *
 */
public class MemberService {

	private MemberDAO dao = new MemberDAO();


	/**
	 * 회원가입 Service
	 * 
	 * @param member
	 * @return result
	 * @throws Exception
	 */
	public int joinMember(Member member) throws Exception {

		Connection conn = getConnection();

		int result = dao.joinMember(conn, member);

		if (result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);

		return result;
	}

	/**
	 * 아이디 중복 검사 Service
	 * 
	 * @param inputId
	 * @return result
	 * @throws Exception
	 */
	public int idDupCheck(String inputId) throws Exception {

		Connection conn = getConnection();

		int result = dao.idDupCheck(conn, inputId);

		close(conn);

		return result;
	}

	/**
	 * 정보 수정 Service
	 * 
	 * @param member
	 * @return result
	 * @throws Exception
	 */
	public int updateMember(Member member) throws Exception {

		Connection conn = getConnection();

		int result = dao.checkCurrentPwd(conn, member);

		if (result > 0) {

			result = dao.updateMember(conn, member);

			if (result > 0)
				commit(conn);
			else
				rollback(conn);

		} else {
			result = -1;
		}
		close(conn);

		return result;
	}

	/**
	 * 비밀번호 변경 Service
	 * 
	 * @param loginMember
	 * @param newPwd
	 * @return result
	 * @throws Exception
	 */
	public int updatePwd(Member loginMember, String newPwd) throws Exception {
		Connection conn = getConnection();

		int result = dao.checkCurrentPwd(conn, loginMember);

		if (result > 0) {
			loginMember.setMemberPwd(newPwd);
			result = dao.updatePwd(conn, loginMember);

			if (result > 0)
				commit(conn);
			else
				rollback(conn);
		} else {
			result = -1;
		}
		close(conn);

		return result;
	}

	/**
	 * 회원 탈퇴 Service
	 * 
	 * @param loginMember
	 * @return result
	 * @throws Exception
	 */
	public int updateStatus(Member loginMember) throws Exception {

		Connection conn = getConnection();
		int result = dao.checkCurrentPwd(conn, loginMember);

		if (result > 0) {

			result = dao.updateStatus(conn, loginMember.getMemberNo());
			if (result > 0)
				commit(conn);
			else
				rollback(conn);

		} else {
			result = -1;
		}

		close(conn);

		return result;
	}

	/**
	 * 페이징 처리를 위한 값 계산 Service
	 * 
	 * @param boardCp
	 * @return pInfo
	 * @throws Exception
	 */
	public PageInfo getBoardPageInfo(String boardCp, int memberNo) throws Exception {
		Connection conn = getConnection();

		int currentPage = boardCp == null ? 1 : Integer.parseInt(boardCp);

		int listCount = dao.getBoardListCount(conn, memberNo);

		close(conn);

		return new PageInfo(currentPage, listCount);
	}

	/**
	 * 페이징 처리를 위한 값 계산 Service
	 * 
	 * @param replyCp
	 * @return pInfo
	 * @throws Exception
	 */
	public PageInfo getReplyPageInfo(String replyCp, int memberNo) throws Exception {
		Connection conn = getConnection();

		int currentPage = replyCp == null ? 1 : Integer.parseInt(replyCp);

		int listCount = dao.getReplyListCount(conn, memberNo);

		close(conn);

		return new PageInfo(currentPage, listCount);
	}

	/**
	 * 내 게시글 목록 조회
	 * 
	 * @param pInfo
	 * @return bList
	 * @throws Exception
	 */
	public List<Board> selectBoardList(PageInfo pInfo, int memberNo) throws Exception {
		Connection conn = getConnection();

		List<Board> bList = dao.selectBoardList(conn, pInfo, memberNo);

		close(conn);

		return bList;

	}

	/**
	 * 게시글 삭제 Service
	 * 
	 * @param boardNo
	 * @return result
	 * @throws Exception
	 */
	public int updateBoardStatus(String[] boardNo) throws Exception {
		Connection conn = getConnection();

		int result = 0;

		for (int i = 0; i < boardNo.length; i++) {
			result += dao.updateBoardStatus(conn, boardNo[i]);
		}

		if (result >= boardNo.length) {
			commit(conn);
		} else {
			rollback(conn);
		}

		close(conn);

		return result;
	}

	/**
	 * 내 댓글 목록 조회
	 * 
	 * @param pInfo
	 * @return rList
	 * @throws Exception
	 */
	public List<Reply> selectReplyList(PageInfo pInfo, int memberNo) throws Exception {
		Connection conn = getConnection();

		List<Reply> rList = dao.selectReplyList(conn, pInfo, memberNo);

		close(conn);

		return rList;
	}

	/**
	 * 댓글 삭제 Service
	 * 
	 * @param replyArr
	 * @return result
	 * @throws Exception
	 */
	public int updateReplyStatus(String[] replyArr) throws Exception {
		Connection conn = getConnection();

		int result = 0;

		for (int i = 0; i < replyArr.length; i++) {
			result += dao.updateReplyStatus(conn, replyArr[i]);
		}

		if (result >= replyArr.length) {
			commit(conn);
		} else {
			rollback(conn);
		}

		close(conn);

		return result;
	}

	/**
	 * 페이징 처리를 위한 값 계산 Service
	 * 
	 * @param boardCp
	 * @return pInfo
	 * @throws Exception
	 */
	public PageInfo2 getFavoritePageInfo(String boardCp, int memberNo) throws Exception {
		Connection conn = getConnection();

		int currentPage = boardCp == null ? 1 : Integer.parseInt(boardCp);

		int listCount = dao.getFavoriteListCount(conn, memberNo);

		close(conn);

		return new PageInfo2(currentPage, listCount);
	}

	/**
	 * 좋아하는 글 목록 조회
	 * 
	 * @param pInfo
	 * @return bList
	 * @throws Exception
	 */
	public List<VBoard> selectFavoriteList(PageInfo2 pInfo, int memberNo) throws Exception {
		Connection conn = getConnection();

		List<VBoard> bList = dao.selectFavoriteList(conn, pInfo, memberNo);

		close(conn);

		return bList;
	}

	
	
	
	/** 이메일 발송 
	 * @param email
	 * @return
	 */
	public String mailSend(String email) {
		
		String user = "projectthinko@gmail.com"; // 네이버일 경우 네이버 계정, gmail경우 gmail 계정
		String password = "wqaybxyleezgjwbt"; // 패스워드

		Properties prop = new Properties(); 
		prop.put("mail.smtp.host", "smtp.gmail.com"); 
		prop.put("mail.smtp.port", 465); 
		prop.put("mail.smtp.auth", "true"); 
		prop.put("mail.smtp.ssl.enable", "true"); 
		prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		
        java.util.Random generator = new java.util.Random();
        generator.setSeed(System.currentTimeMillis());
        String number = Integer.toString(generator.nextInt(1000000) % 1000000);


		Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
			
			// 메일 제목
			message.setSubject("[Thinko] 회원가입 인증번호를 보내드립니다.");
			
			// 메일 내용
			message.setText(
					"안녕하세요. Thinko 홈페이지를 방문해주셔서 감사합니다. \r\n" +
					"아래의 인증코드를 인증번호란에 입력하고 확인 버튼을 클릭 하시면 본인확인이 완료됩니다.\r\n\r\n" +
					"인증번호 : " + number);
			

			// send the message
			Transport.send(message);

		} catch (MessagingException e) {
			e.printStackTrace();

		}
		return number;
	}

	/**
	 * 좋아요 취소 Service
	 * 
	 * @param id
	 * @return result
	 * @throws Exception
	 */
	public int updateLikeFl(int memberNo, String id) throws Exception {
		Connection conn = getConnection();

		int result = 0;
		result = dao.updateLikeFl(conn, memberNo, id);

		if (result > 0) commit(conn);
		else 			rollback(conn);

		close(conn);

		return result;
	}

}
