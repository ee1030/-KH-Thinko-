package com.kh.thinko.member.controller;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.thinko.common.SHA256;
import com.kh.thinko.member.model.service.MemberService2;
import com.kh.thinko.member.model.vo.Member;

@WebServlet("/member2/*")
public class MemberController2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = uri.substring((contextPath + "/member2").length());

		String path = null; // forward 또는 redirect 경로를 저장할 변수
		RequestDispatcher view = null; // 요청 위임 객체

		String swalIcon = null; // sweet alert로 메세지 전달하는 용도
		String swalTitle = null;
		String swalText = null;

		String errorMsg = null; // 에러메시지 전달용 변수

		try {

			MemberService2 service = new MemberService2();

			// 로그인 Controller
			if (command.equals("/memberLogin.do")) {
				errorMsg = "로그인 중 에러 발생";
				HttpSession session = request.getSession();

				String memberId = request.getParameter("userId");
				String memberPwd = request.getParameter("userPwd");
				String save = request.getParameter("save");

				Member member = new Member();
				member.setMemberId(memberId);
				member.setMemberPwd(memberPwd);

				// 체크시 on, 아니면 null

				Member loginMember = new MemberService2().loginMember(member);

				if (loginMember != null) {
					session.setMaxInactiveInterval(60 * 30);
					session.setAttribute("loginMember", loginMember);

					Cookie cookie = new Cookie("saveId", memberId);

					if (save != null) {
						cookie.setMaxAge(60 * 60 * 24 * 7);
					} else {
						cookie.setMaxAge(0);
					}

					cookie.setPath(request.getContextPath());
					response.addCookie(cookie);

					response.sendRedirect("/Thinko");

				} else {
					session.setAttribute("swalIcon", "error");

					session.setAttribute("swalTitle", "로그인 실패");

					session.setAttribute("swalText", "아이디 또는 비밀번호를 확인해주세요.");

					path = "/WEB-INF/views/member/memberLogin.jsp";
					view = request.getRequestDispatcher(path);
					view.forward(request, response);
				}

			}

			// 로그인 페이지 이동 Controller
			else if (command.equals("/memberLoginForm.do")) {
				errorMsg = "로그인페이지로 이동중 오류 발생";

				path = "/WEB-INF/views/member/memberLogin.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
			}

			else if (command.equals("/logout.do")) {
				errorMsg = "로그아웃 중 오류 발생";

				path = "${contextPath}/member2/memberLoginForm.do";

				request.getSession().invalidate();
				response.sendRedirect(request.getContextPath());

			}

			// 아이디 찾기 화면 전환 Controller
			else if (command.equals("/searchIdForm.do")) {
				path = "/WEB-INF/views/member/searchId.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
			}

			// 아이디 찾기 Controller
			else if (command.equals("/searchId.do")) {
				errorMsg = "아이디 찾기 페이지로 이동 중 에러 발생";

				String memberNm = request.getParameter("memberNm");
				String memberEmail = request.getParameter("memberEmail");

				String result = service.searchId(memberNm, memberEmail);

				System.out.println(memberNm + " / " + memberEmail);

				if (result != null) {
					path = "/WEB-INF/views/member/successId.jsp";
					request.setAttribute("result", result);

					view = request.getRequestDispatcher(path);
					view.forward(request, response);

				} else {
					request.getSession().setAttribute("swalIcon", "error");
					request.getSession().setAttribute("swalTitle", "아이디찾기 실패");
					request.getSession().setAttribute("swalText", "존재하지 않은 아이디이거나 잘못 입력하셨습니다.");

					response.sendRedirect("searchIdForm.do");
				}

			}

			// 비밀번호 찾기 화면 전환 Controller
			else if (command.equals("/searchPwForm.do")) {
				path = "/WEB-INF/views/member/searchPw.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
			}

			// 비밀번호 찾기 Controller
			else if (command.equals("/searchPw.do")) {
				errorMsg = "비밀번호 찾기 페이지로 이동 중 에러 발생";

				String memberId = request.getParameter("memberId");
				String memberEmail = request.getParameter("memberEmail");
				String encryptEmail = SHA256.getSHA256(memberEmail + System.currentTimeMillis());
				
				int result = service.searchPw(memberId, memberEmail, encryptEmail);

				if (result > 0) {
					path = "sendEmail.do";
					request.getSession().setAttribute("memberEmail", memberEmail);
					request.getSession().setAttribute("encryptEmail", encryptEmail);
				} else {

					path = "searchPwForm.do";
					request.getSession().setAttribute("swalIcon", "error");
					request.getSession().setAttribute("swalTitle", "비밀번호 찾기 실패");
					request.getSession().setAttribute("swalText", "존재하지 않은 아이디이거나 잘못 입력하셨습니다.");

				}

				response.sendRedirect(path);
			}


			// 이메일 전송 Controller
			else if (command.equals("/sendEmail.do")) {

				HttpSession session = request.getSession();
				String email = (String) session.getAttribute("memberEmail");
				String encryptEmail = (String) session.getAttribute("encryptEmail");

				if (email == null) {
					swalIcon = "error";
					swalTitle = "오류";

				} else {
					String host = "http://localhost:8080/Thinko/";
					String from = "projectthinko@gmail.com";
					String to = email;
					System.out.println("to : " + to);

					
					String subject = "비밀번호 재설정을 위한 인증 메일입니다.";
					String content = "다음 링크에 접속하여 비밀번호를 변경하세요.<br><br>" + "<a href='" + host + "member2/checkCode.do?code="
							+ encryptEmail 
							+ "'>여기</a>" + "를 클릭하면 비밀번호 변경화면으로 이동합니다.";

					Properties prop = new Properties();
					prop.setProperty("mail.transport.protocol", "smtp");
					prop.setProperty("mail.host", "smtp.gmail.com");
					prop.setProperty("mail.smtp.port", "465");
					prop.setProperty("mail.smtp.auth", "true");
					prop.setProperty("mail.smtp.socketFactory.port", "465");
					prop.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
					prop.setProperty("mail.smtp.socketFactory.fallback", "false");
					prop.setProperty("mail.smtp.ssl.enable", "true");
					prop.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");
					prop.put("mail.store.protocol", "pop3");

					// 메일 서버 인증 계정 설정
					Authenticator auth = new Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication("projectthinko@gmail.com", "wqaybxyleezgjwbt");
						}
					};

					Session sess = Session.getDefaultInstance(prop, auth);
					MimeMessage msg = new MimeMessage(sess);
					Address fromAddr = new InternetAddress(from);
					Address toAddr = new InternetAddress(to);
					msg.setFrom(fromAddr);
					msg.addRecipient(Message.RecipientType.TO, toAddr);
					msg.setSubject(subject);
					msg.setContent(content, "text/html;charset=UTF-8");
					Transport.send(msg);

					swalIcon = "success";
					swalTitle = "이메일을 통해 비밀번호를 재설정해주세요.";
				}

				session.setAttribute("swalIcon", swalIcon);
				session.setAttribute("swalTitle", swalTitle);
				response.sendRedirect(request.getContextPath());

			}
			
			
			// 신규 비밀번호 작성 화면 전환 Controller 
			else if(command.equals("/checkCode.do")) {
				
				// 이메일 변경 시 주소에 첨부된 암호화된 이메일이 DB에 존재하는지 확인
				String code = (String)request.getParameter("code");
				
				// result == 회원번호
				int result = service.selectEncryptEmail(code);
				
				if(result > 0) {
					request.getSession().setAttribute("memberNo", result);;
					path = "changePwForm.do";
				}else {
					path = request.getContextPath();
				}
				response.sendRedirect(path);
			}
			
			
			else if(command.equals("/changePwForm.do")) {
				path = "/WEB-INF/views/member/changePw.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
			}
			
			
			// 비밀번호 변경 Controller
			else if(command.equals("/searchPwdChange.do")) {
				int memberNo = (int)request.getSession().getAttribute("memberNo");
				
				String newPw = request.getParameter("newPw");
						
				int result =  service.updatePw(memberNo, newPw);
				
				if(result > 0) {
					path = request.getContextPath();
					swalIcon = "success";
					swalTitle = "비밀번호 변경 성공";
				}else {
					path = request.getHeader("referer");
					swalIcon = "erorr";
					swalTitle = "비밀번호 변경 실패";
				}
				
				request.getSession().setAttribute("swalIcon", swalIcon);
				request.getSession().setAttribute("swalTitle", swalTitle);
				
				response.sendRedirect(path);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			path = "/WEB-INF/views/common/errorPage.jsp";
			request.setAttribute("errorMsg", errorMsg);
			view = request.getRequestDispatcher(path);
			view.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
