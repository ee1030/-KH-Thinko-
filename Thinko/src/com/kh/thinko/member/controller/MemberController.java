package com.kh.thinko.member.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kh.thinko.member.model.service.MemberService;
import com.kh.thinko.board.model.vo.Board;
import com.kh.thinko.member.model.vo.PageInfo2;
import com.kh.thinko.board.model.vo.VBoard;
import com.kh.thinko.member.model.vo.Member;
import com.kh.thinko.member.model.vo.PageInfo;
import com.kh.thinko.reply.model.vo.Reply;

@WebServlet("/member/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = uri.substring((contextPath + "/member").length());

		String path = null;
		RequestDispatcher view = null;

		String swalIcon = null;
		String swalTitle = null;
		String swalText = null;

		String errorMsg = null;

		try {
			MemberService service = new MemberService();

			// 회원가입 폼 Controller *******************************************
			if (command.equals("/joinForm.do")) {
				path = "/WEB-INF/views/member/joinForm.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request, response);

			}

			// 이메일 검사 폼 Controller *******************************************
			if (command.equals("/email.do")) {
				HttpSession session = request.getSession();
				String email = request.getParameter("userEmail");

				String number = new MemberService().mailSend(email);

				response.getWriter().print(number);
			}

			// 회원가입 Controller *******************************************
			else if (command.equals("/join.do")) {

				errorMsg = "회원 가입 과정에서 오류가 발생했습니다.";

				String memberId = request.getParameter("id");
				String memberPwd = request.getParameter("pwd");
				String memberNm = request.getParameter("name");
				String memberPhone = request.getParameter("phone");
				String memberEmail = request.getParameter("email");
				String membershipType = request.getParameter("type");

				String[] interest = request.getParameterValues("memberInterest");
				String memberInterest = null;
				if (interest != null)
					memberInterest = String.join(",", interest);

				Member member = new Member(memberId, memberPwd, memberNm, memberPhone, memberEmail, membershipType,
						memberInterest);

				int result = new MemberService().joinMember(member);

				HttpSession session = request.getSession();

				if (result > 0) {

					session.setAttribute("swalIcon", "success");
					session.setAttribute("swalTitle", "회원 가입이 완료되었습니다.");
					session.setAttribute("swalText", "로그인 페이지로 이동합니다.");
					session.setAttribute("swalButton", "확인");

					response.sendRedirect("../member2/memberLoginForm.do");

				} else {
					session.setAttribute("swalIcon", "error");
					session.setAttribute("swalTitle", "회원 가입에 실패했습니다.");
					session.setAttribute("swalText", "확인 버튼을 누르면 이전 페이지로 이동합니다.");
					session.setAttribute("swalButton", "확인");

					response.sendRedirect(request.getHeader("referer"));
				}

			}

			// 아이디 중복검사 Controller ***************************************
			else if (command.equals("/idDupCheck.do")) {
				String inputId = request.getParameter("inputId");

				int result = new MemberService().idDupCheck(inputId);

				// PrintWriter out = response.getWriter();
				// out.append(result+"");

				// 변수 저장 필요 없을 시
				response.getWriter().print(result);

			}

			// 마이페이지 form Controller *******************************************
			else if (command.equals("/myPage.do")) {
				path = "/WEB-INF/views/member/myPage.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
			}

			// 회원 정보 변경 Controller *******************************************
			else if (command.equals("/updateMember.do")) {
				errorMsg = "회원 정보 변경 과정에서 오류가 발생했습니다.";

				String memberPwd = request.getParameter("currentPwd");
				String memberEmail = request.getParameter("email");
				String memberPhone = request.getParameter("phone");
				String[] interest = request.getParameterValues("memberInterest");
				String memberInterest = null;
				if (interest != null)
					memberInterest = String.join(",", interest);

				HttpSession session = request.getSession();
				Member loginMember = (Member) session.getAttribute("loginMember");

				Member member = new Member();
				member.setMemberNo(loginMember.getMemberNo());
				member.setMemberPwd(memberPwd);
				member.setMemberEmail(memberEmail);
				member.setMemberPhone(memberPhone);
				member.setMemberInterest(memberInterest);

				int result = service.updateMember(member);

				if (result > 0) {
					swalIcon = "success";
					swalTitle = "회원 정보 변경이 완료되었습니다.";

					member.setMemberId(loginMember.getMemberId());
					member.setMemberNm(loginMember.getMemberNm());
					member.setMembershipType(loginMember.getMembershipType());
					session.setAttribute("loginMember", member);
				} else if (result == 0) {
					swalIcon = "error";
					swalTitle = "회원 정보 변경에 실패했습니다.";
				} else {
					swalIcon = "warning";
					swalTitle = "현재 비밀번호가 일치하지 않습니다.";
				}

				session.setAttribute("swalIcon", swalIcon);
				session.setAttribute("swalTitle", swalTitle);

				response.sendRedirect("myPage.do");

			}

			// 비밀번호 변경 form Controller ***********************************
			else if (command.equals("/changePwdForm.do")) {
				path = "/WEB-INF/views/member/changePwd.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
			}

			// 비밀번호 변경 Controller ****************************************
			else if (command.equals("/updatePwd.do")) {
				errorMsg = "비밀번호 변경 과정에서 오류가 발생했습니다.";

				String currentPwd = request.getParameter("currentPwd");
				String newPwd = request.getParameter("newPwd");

				HttpSession session = request.getSession();
				Member loginMember = (Member) session.getAttribute("loginMember");

				loginMember.setMemberPwd(currentPwd);
				int result = new MemberService().updatePwd(loginMember, newPwd);

				if (result > 0) {
					swalIcon = "success";
					swalTitle = "비밀번호가 변경되었습니다.";

				} else if (result == 0) { // 비밀번호 변경 실패
					swalIcon = "error";
					swalTitle = "비밀번호 변경에 실패했습니다.";

				} else { // 현재 비밀번호 불일치
					swalIcon = "warning";
					swalTitle = "현재 비밀번호가 일치하지 않습니다.";
				}

				session.setAttribute("swalIcon", swalIcon);
				session.setAttribute("swalTitle", swalTitle);

				response.sendRedirect(request.getHeader("referer"));

			}

			// 내 게시물 화면 전환 Controller ******************************************
			else if (command.equals("/myList.do")) {

				Member loginMember = (Member) (request.getSession()).getAttribute("loginMember");

				PageInfo boardPInfo = service.getBoardPageInfo(null, loginMember.getMemberNo());
				PageInfo replyPInfo = service.getReplyPageInfo(null, loginMember.getMemberNo());

				request.setAttribute("boardPInfo", boardPInfo);
				request.setAttribute("replyPInfo", replyPInfo);

				path = "/WEB-INF/views/member/myBoardList.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request, response);

			}

			// 내 게시물 조회 Controller ******************************************
			else if (command.equals("/myBoardList.do")) {

				errorMsg = "내 게시물 조회 과정에서 오류가 발생했습니다.";

				HttpSession session = request.getSession();
				Member loginMember = (Member) session.getAttribute("loginMember");
				String boardCp = request.getParameter("boardCp");

				PageInfo boardPInfo = service.getBoardPageInfo(boardCp, loginMember.getMemberNo());
				List<Board> bList = service.selectBoardList(boardPInfo, loginMember.getMemberNo());

				response.setContentType("text/html; charset=UTF-8");

				Map<String, Object> boardMap = new HashMap<>();
				boardMap.put("bList", bList);
				boardMap.put("boardPInfo", boardPInfo);

				Gson gson = new GsonBuilder().setDateFormat("yy-MM-dd").create();
				gson.toJson(boardMap, response.getWriter());

			}

			// 내 댓글 Controller ******************************************
			else if (command.equals("/myReplyList.do")) {

				errorMsg = "내 댓글 조회 과정에서 오류가 발생했습니다.";

				HttpSession session = request.getSession();
				Member loginMember = (Member) session.getAttribute("loginMember");

				String replyCp = request.getParameter("replyCp");

				PageInfo replyPInfo = service.getReplyPageInfo(replyCp, loginMember.getMemberNo());
				List<Reply> rList = service.selectReplyList(replyPInfo, loginMember.getMemberNo());

				response.setContentType("text/html; charset=UTF-8");

				Map<String, Object> replyMap = new HashMap<>();
				replyMap.put("rList", rList);
				replyMap.put("replyPInfo", replyPInfo);

				Gson gson = new GsonBuilder().setDateFormat("yy-MM-dd").create();
				gson.toJson(replyMap, response.getWriter());

			}

			// 내 게시글 삭제 Controller ******************************************
			else if (command.equals("/deleteMyBoard.do")) {
				errorMsg = "게시글 삭제 과정에서 오류가 발생했습니다.";
				int result = 0;
				String[] boardArr = request.getParameterValues("boardArr");

				result = service.updateBoardStatus(boardArr);

				if (result >= boardArr.length) {
					result = 1;
				} else {
					result = 0;
				}

				response.getWriter().print(result);

			}

			// 내 댓글 삭제 Controller ******************************************
			else if (command.equals("/deleteMyReply.do")) {
				errorMsg = "게시글 삭제 과정에서 오류가 발생했습니다.";
				int result = 0;
				String[] replyArr = request.getParameterValues("replyArr");

				result = service.updateReplyStatus(replyArr);

				if (result >= replyArr.length) {
					result = 1;
				} else {
					result = 0;
				}

				response.getWriter().print(result);

			}

			// 회원탈퇴 폼 Controller ******************************************
			else if (command.equals("/secessionForm.do")) {
				path = "/WEB-INF/views/member/secession.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request, response);
			}

			// 회원탈퇴 Controller ******************************************
			else if (command.equals("/updateStatus.do")) {
				errorMsg = "회원 탈퇴 과정에서 오류가 발생했습니다.";

				String memberPwd = request.getParameter("currentPwd");
				HttpSession session = request.getSession();
				Member loginMember = (Member) session.getAttribute("loginMember");
				loginMember.setMemberPwd(memberPwd);

				int result = new MemberService().updateStatus(loginMember);

				String url = null;

				if (result > 0) {
					swalIcon = "success";
					swalTitle = "회원 탈퇴가 완료되었습니다.";
					swalText = "메인페이지로 이동합니다.";

					session.invalidate();
					session = request.getSession();
					url = contextPath;

				} else if (result == 0) {
					swalIcon = "error";
					swalTitle = "회원탈퇴 실패";
					url = "secessionForm.do";
				} else {
					swalIcon = "warning";
					swalTitle = "현재 비밀번호가 일치하지 않습니다.";
					url = "secessionForm.do";
				}
				session.setAttribute("swalIcon", swalIcon);
				session.setAttribute("swalTitle", swalTitle);
				session.setAttribute("swalText", swalText);

				response.sendRedirect(url);

			}

			// 좋아요 화면 전환 Controller ******************************************
			else if (command.equals("/myFavorite.do")) {

				Member loginMember = (Member) (request.getSession()).getAttribute("loginMember");

				PageInfo2 boardPInfo = service.getFavoritePageInfo(null, loginMember.getMemberNo());

				request.setAttribute("boardPInfo", boardPInfo);

				path = "/WEB-INF/views/member/myFavorite.jsp";
				view = request.getRequestDispatcher(path);
				view.forward(request, response);

			}

			// 좋아하는 글 조회 Controller ******************************************
			else if (command.equals("/myFavoriteList.do")) {

				errorMsg = "내 게시물 조회 과정에서 오류가 발생했습니다.";

				HttpSession session = request.getSession();
				Member loginMember = (Member) session.getAttribute("loginMember");
				String cp = request.getParameter("boardCp");

				PageInfo2 pInfo = service.getFavoritePageInfo(cp, loginMember.getMemberNo());
				List<VBoard> bList = service.selectFavoriteList(pInfo, loginMember.getMemberNo());

				response.setContentType("text/html; charset=UTF-8");

				Map<String, Object> boardMap = new HashMap<>();
				boardMap.put("bList", bList);
				boardMap.put("pInfo", pInfo);
				Gson gson = new GsonBuilder().setDateFormat("yy-MM-dd").create();
				gson.toJson(boardMap, response.getWriter());

			}

			// 좋아요 취소 Controller ******************************************
			else if (command.equals("/updateLikeFl.do")) {
				errorMsg = "좋아요 취소 과정에서 오류가 발생했습니다.";
				int result = 0;
				String id = request.getParameter("id");

				HttpSession session = request.getSession();
				Member loginMember = (Member) session.getAttribute("loginMember");

				result = service.updateLikeFl(loginMember.getMemberNo(), id);

				response.getWriter().print(result);

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
